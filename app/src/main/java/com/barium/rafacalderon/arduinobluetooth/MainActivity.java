package com.barium.rafacalderon.arduinobluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int BLUETOOTH_HABILITADO = 1;
    private Button btnConectar;
    public static BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConectar = (Button) findViewById(R.id.btnConectar);
        btnConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cB c = new cB();
                c.execute();
            }
        });
    }

    private class cB extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter == null) {
                Toast.makeText(getApplicationContext(), "Adaptador de bluetooth no encontrado", Toast.LENGTH_LONG).show();
            } else if (!bluetoothAdapter.isEnabled()) {
                Intent activarBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(activarBluetoothIntent, BLUETOOTH_HABILITADO);
            } else {
                Intent seleccionDispositivoIntent = new Intent(MainActivity.this, Dispositivos.class);
                startActivity(seleccionDispositivoIntent);
                this.cancel(true);
            }
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BLUETOOTH_HABILITADO) {
            if (resultCode == RESULT_OK) {
                Intent seleccionDispositivoIntent = new Intent(this, Dispositivos.class);
                startActivity(seleccionDispositivoIntent);
            } else {
                Toast.makeText(this, "Debes activar el Bluetooth para utilizar la aplicación", Toast.LENGTH_LONG).show();
            }
        }
    }
}
