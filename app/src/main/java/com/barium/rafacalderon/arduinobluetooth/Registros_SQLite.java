package com.barium.rafacalderon.arduinobluetooth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Registros_SQLite extends AppCompatActivity {

    private ListView listView;
    private CRUDInfoAmbiente crud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros__sqlite);
        listView = (ListView) findViewById(R.id.datosGuardadosSQLite);
        crud = new CRUDInfoAmbiente(this);
        List<InfoAmbiente> data = crud.infoAmbienteList();
        listView.setAdapter(new ArrayAdapter<InfoAmbiente>(this,
                android.R.layout.simple_list_item_1, data));
    }
}
