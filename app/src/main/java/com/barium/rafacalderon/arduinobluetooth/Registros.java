package com.barium.rafacalderon.arduinobluetooth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

public class Registros extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);
        listView = (ListView) findViewById(R.id.datosGuardados);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cargaInformacionProyecto();
    }

    public void cargaInformacionProyecto() {
        AsyncHttpClient client = new AsyncHttpClient();

        String url = "http://appmovil.todojava.net/index.php/temperaturasAmbiente90";
        client.post(url, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {
                if(i==200){
                    String result = new String(bytes);
                    Log.i("INFO",result);
                    cargaListView(result);
                }
            }

            @Override
            public void onFailure(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    public void cargaListView(String result){
        try {
            JSONArray json = new JSONArray(result);
            String s;
            ArrayList<String> lista= new ArrayList<>();
            for (int i=0; i< json.length();i++){
                String[] fecha_hora = json.getJSONObject(i).getString("hora_fecha").split(" ");
                s = "Temperatura: "+json.getJSONObject(i).getString("temperatura_ambiente")+"°C";
                s = s+"\nHumedad: "+ json.getJSONObject(i).getString("humedad_ambiente")+"%";
                s = s+"\nFecha: "+ fecha_hora[0];
                s = s+"\nHora: "+fecha_hora[1];
                lista.add(s);
            }
            listView.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    lista));
        }catch (Exception e){

        }

    }
}
