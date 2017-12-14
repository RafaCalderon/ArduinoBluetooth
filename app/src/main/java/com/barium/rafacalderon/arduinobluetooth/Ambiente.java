package com.barium.rafacalderon.arduinobluetooth;


import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ambiente extends Fragment {

    private BluetoothSocket bluetoothSocket = Dispositivos.bluetoothSocket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private TextView txtTemp;
    private TextView txtHum;
    private Button comenzar;
    private FloatingActionButton guardarDatos, guardarDatosSQLite;
    private int flag = 0;
    temperatura temperatura;


    public Ambiente() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ambiente, container, false);
        txtTemp = (TextView) view.findViewById(R.id.txtTempAmbiente);
        txtHum = (TextView) view.findViewById(R.id.txtHumAmbiente);
        comenzar = (Button) view.findViewById(R.id.btComenzarAmbiente);
        guardarDatos = (FloatingActionButton) view.findViewById(R.id.guardarDatosAmbiente);
        guardarDatosSQLite = (FloatingActionButton) view.findViewById(R.id.guardarDatosAmbienteSQLite);
        guardarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temperatura = txtTemp.getText().toString().replace("Temperatura: ", "").replace("°C","");
                String humedad = txtHum.getText().toString().replace("Humedad: ", "").replace("%","");
                if(!temperatura.equals("0") && !humedad.equals("0")){
                    guardarDatos(temperatura, humedad);
                }else{
                    Toast.makeText(getContext(), "Presione el botón Iniciar Captura", Toast.LENGTH_SHORT).show();
                }
            }
        });
        guardarDatosSQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String temperatura = txtTemp.getText().toString().replace("Temperatura: ", "").replace("°C","");
                String humedad = txtHum.getText().toString().replace("Humedad: ", "").replace("%","");
                if(!temperatura.equals("0") && !humedad.equals("0")) {
                    guardarDatosSQLite(temperatura, humedad);
                }else{
                    Toast.makeText(getContext(), "Presione el botón Iniciar Captura", Toast.LENGTH_SHORT).show();
                }
            }
        });
        try {
            outputStream = bluetoothSocket.getOutputStream();
            inputStream = bluetoothSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        comenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 0) {
                    sendBT('1');
                    comenzar.setText("Detener captura de datos");
                    temperatura = new temperatura();
                    temperatura.execute();
                    flag = 1;
                } else {
                    sendBT('0');
                    comenzar.setText("Iniciar captura de datos");
                    temperatura.cancel(true);
                    flag = 0;
                }
            }
        });
        return view;
    }

    public void guardarDatos(String temperatura, String humedad){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "http://appmovil.todojava.net/index.php/insertTemAmbiente90";
        RequestParams params = new RequestParams();
        Date d = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        int diaInt = c.get(Calendar.DAY_OF_MONTH);
        int mesInt = c.get(Calendar.MONTH)+1;
        int horaInt = c.get(Calendar.HOUR_OF_DAY);
        int minutoInt = c.get(Calendar.MINUTE);
        int segundoInt = c.get(Calendar.SECOND);
        String dia, mes, hora, minuto, segundo;
        if(diaInt < 10){dia = "0"+diaInt;}else{dia=String.valueOf(diaInt);}
        if(mesInt < 10){mes = "0"+mesInt;}else{mes=String.valueOf(mesInt);}
        if(horaInt < 10){hora = "0"+horaInt;}else{hora=String.valueOf(horaInt);}
        if(minutoInt < 10){minuto = "0"+minutoInt;}else{minuto=String.valueOf(minutoInt);}
        if(segundoInt< 10){segundo= "0"+segundoInt;}else{segundo=String.valueOf(segundoInt);}
        String año = String.valueOf(c.get(Calendar.YEAR));
        String fecha = dia+"/"+mes+"/"+año+" "+hora+":"+minuto+":"+segundo;
        params.put("fecha", fecha);
        params.put("temperatura", temperatura);
        params.put("humedad", humedad);
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                if(i==200){
                    String result = new String(bytes);
                    Toast.makeText(getContext(), "Datos guardados exitosamente con PHP", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                String result = new String(bytes);
            }
        });
    }

    public void guardarDatosSQLite(String temperatura, String humedad){
        CRUDInfoAmbiente crud = new CRUDInfoAmbiente(getContext());
        Date d = new Date();
        Calendar c = new GregorianCalendar();
        c.setTime(d);
        int diaInt = c.get(Calendar.DAY_OF_MONTH);
        int mesInt = c.get(Calendar.MONTH)+1;
        int horaInt = c.get(Calendar.HOUR_OF_DAY);
        int minutoInt = c.get(Calendar.MINUTE);
        int segundoInt = c.get(Calendar.SECOND);
        String dia, mes, hora, minuto, segundo;
        if(diaInt < 10){dia = "0"+diaInt;}else{dia=String.valueOf(diaInt);}
        if(mesInt < 10){mes = "0"+mesInt;}else{mes=String.valueOf(mesInt);}
        if(horaInt < 10){hora = "0"+horaInt;}else{hora=String.valueOf(horaInt);}
        if(minutoInt < 10){minuto = "0"+minutoInt;}else{minuto=String.valueOf(minutoInt);}
        if(segundoInt< 10){segundo= "0"+segundoInt;}else{segundo=String.valueOf(segundoInt);}
        String año = String.valueOf(c.get(Calendar.YEAR));
        String fecha = dia+"/"+mes+"/"+año+" "+hora+":"+minuto+":"+segundo;
        InfoAmbiente i = new InfoAmbiente(temperatura, humedad,fecha);
        crud.insertar(i);
        Toast.makeText(getContext(), "Datos guardados exitosamente con SQLite", Toast.LENGTH_SHORT).show();
    }

    public void sendBT(char dato) {
        try {
            outputStream.write(dato);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class temperatura extends AsyncTask<Void, String, Void> {
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            String[] valores = values[0].split("_");
            if (valores.length > 1) {
                String temp = valores[0];
                String hum = valores[1];
                txtTemp.setText("Temperatura: " + temp + "°C");
                txtHum.setText("Humedad: " + hum + "%");
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            byte[] buffer = new byte[1024];
            int bytes;
            while (true) {
                try {
                    bytes = inputStream.read(buffer);
                    String d = new String(buffer, 0, bytes);
                    Thread.sleep(1000);
                    publishProgress(d);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (isCancelled()) break;
            }
            return null;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.isVisible()) {
            if (!isVisibleToUser) {
                //Invisible
                sendBT('0');
                comenzar.setText("Iniciar captura de datos");
                if(temperatura != null){
                    temperatura.cancel(true);
                }
                Log.d("fragmento", "Esta invisible");
                flag = 0;
            } else {
                Log.d("fragmento", " esta visible, inicia el servicio.");
            }
        }
    }

}
