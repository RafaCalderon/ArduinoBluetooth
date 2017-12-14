package com.barium.rafacalderon.arduinobluetooth;


import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;
import java.io.OutputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dispositivos_2 extends Fragment {


    public Dispositivos_2() {
        // Required empty public constructor
    }

    private BluetoothSocket bluetoothSocket = Dispositivos.bluetoothSocket;
    private OutputStream outputStream;
    private Button btLed1, btLed2;
    private int flagLed1 = 0;
    private int flagLed2 = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dispositivos_2, container, false);
        btLed1 = (Button) view.findViewById(R.id.btLed1);
        btLed2 = (Button) view.findViewById(R.id.btLed2);
        try {
            outputStream = bluetoothSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        btLed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flagLed1 == 0){
                    sendBT('2');
                    btLed1.setText("Apagar");
                    flagLed1 = 1;
                }else{
                    sendBT('3');
                    btLed1.setText("Encender");
                    flagLed1 = 0;
                }
            }
        });
        btLed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flagLed2 == 0){
                    sendBT('4');
                    btLed2.setText("Apagar");
                    flagLed2 = 1;
                }else{
                    sendBT('5');
                    btLed2.setText("Encender");
                    flagLed2 = 0;
                }
            }
        });
        return view;
    }

    public void sendBT(char dato) {
        try {
            outputStream.write(dato);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
