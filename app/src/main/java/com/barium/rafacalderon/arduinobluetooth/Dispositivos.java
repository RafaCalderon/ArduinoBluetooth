package com.barium.rafacalderon.arduinobluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.UUID;

public class Dispositivos extends AppCompatActivity {

    public static BluetoothSocket bluetoothSocket;
    public static String deviceName;
    private LinearLayout dispositivos;
    private BluetoothAdapter bluetoothAdapter = MainActivity.bluetoothAdapter;
    private LinearLayout lyCargando;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos);
        dispositivos = (LinearLayout) findViewById(R.id.dispositivos);
        lyCargando = (LinearLayout) findViewById(R.id.lyCargando);
        Set<BluetoothDevice> dispositivosPareados = bluetoothAdapter.getBondedDevices();
        if (dispositivosPareados.size() > 0) {
            for (final BluetoothDevice bluetoothDevice : dispositivosPareados) {
                Button dispositivo = new Button(this);
                dispositivo.setText(bluetoothDevice.getName());
                dispositivo.setBackground(getDrawable(R.drawable.boton_dispositivo));
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 10, 0, 10);
                dispositivo.setLayoutParams(lp);
                dispositivo.setTextColor(Color.WHITE);
                dispositivo.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_devices_black_24dp, 0, 0, 0);
                dispositivo.setPadding(30, 0, 0, 0);
                dispositivo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (bluetoothDevice.getName().equals("HC-05")) {
                            conectarDispositivo c = new conectarDispositivo();
                            c.execute(bluetoothDevice);
                        } else {
                            Toast.makeText(Dispositivos.this, "Solo se aceptan dispositivos HC-05", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dispositivos.addView(dispositivo);
            }
        } else {
            TextView info = new TextView(this);
            info.setText("Dispositivos pareados no encontrados");
            dispositivos.addView(info);
        }
    }

    private class conectarDispositivo extends AsyncTask<BluetoothDevice, Integer, Void> {
        @Override
        protected Void doInBackground(BluetoothDevice... bluetoothDevices) {
            publishProgress(1);
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard //SerialPortService ID
            try {
                bluetoothSocket = bluetoothDevices[0].createRfcommSocketToServiceRecord(uuid);
                bluetoothSocket = (BluetoothSocket) bluetoothDevices[0].getClass().getMethod("createRfcommSocket", new Class[]{int.class}).invoke(bluetoothDevices[0], 1);
                deviceName = bluetoothDevices[0].getName();
                bluetoothSocket.connect();
                this.cancel(true);
                Intent goPrincipal = new Intent(Dispositivos.this, Principal.class);
                startActivity(goPrincipal);
                finish();

            } catch (NoSuchMethodException e) {
                publishProgress(2);
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                publishProgress(2);
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                publishProgress(2);
                e.printStackTrace();
            } catch (IOException e) {
                publishProgress(2);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (values[0] == 1) {
                lyCargando.setVisibility(View.VISIBLE);
            } else {
                lyCargando.setVisibility(View.GONE);
                toast("Imposible conectar");
            }
        }
    }


    public void toast(String t) {
        Toast.makeText(this, t, Toast.LENGTH_SHORT).show();
    }
}
