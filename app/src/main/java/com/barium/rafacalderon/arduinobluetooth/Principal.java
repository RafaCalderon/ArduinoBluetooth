package com.barium.rafacalderon.arduinobluetooth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;

public class Principal extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private AdapterPager adapter;
    private TextView txtConectadoA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        txtConectadoA = (TextView) findViewById(R.id.txtConectadoA);
        txtConectadoA.setText("Conectado a: " + Dispositivos.deviceName);
        setSupportActionBar(toolbar);
        adapter = new AdapterPager(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.acercaDe:
                Intent i = new Intent(this, Acerca_De.class);
                startActivity(i);
                break;
            case R.id.itVerRegistros:
                Intent j = new Intent(this, Registros.class);
                startActivity(j);
                break;
            case R.id.itVerRegistrosSQLite:
                Intent k = new Intent(this, Registros_SQLite.class);
                startActivity(k);
                break;
            case R.id.desconectar:
                try {
                    Dispositivos.bluetoothSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent l = new Intent(this, MainActivity.class);
                startActivity(l);
                finish();
                break;
        }
        return true;
    }

    public class AdapterPager extends FragmentPagerAdapter {
        public AdapterPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Ambiente";
                case 1:
                    return "Superficie";
                case 2:
                    return "Dispositivos";
            }
            return null;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Ambiente a = new Ambiente();
                    return a;
                case 1:
                    Superficie s = new Superficie();
                    return s;
                case 2:
                    Dispositivos_2 d = new Dispositivos_2();
                    return d;
            }
            return null;
        }
    }
}

