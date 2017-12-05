package com.barium.rafacalderon.arduinobluetooth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Acerca_De extends AppCompatActivity {

    CRUDIntegrante crudIntegrante = new CRUDIntegrante();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca__de);
        listView = (ListView) findViewById(R.id.integrantes);
        listView.setAdapter(new MiAdaptador(this, crudIntegrante.list));
    }
}
