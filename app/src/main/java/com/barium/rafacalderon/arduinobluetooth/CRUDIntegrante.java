package com.barium.rafacalderon.arduinobluetooth;

import java.util.ArrayList;
import java.util.List;

public class CRUDIntegrante {
    public static List<Integrante> list;

    public CRUDIntegrante() {
        list = new ArrayList<>();
        list.add(new Integrante("Rafael Calderón", "rafacalderon1297@gmail.com",  R.drawable.rfa));
        list.add(new Integrante("Felipe Sepúlveda", "xdanblackx@gmail.com", R.drawable.flp));
        list.add(new Integrante("Marcos Díaz", "marcosdiaz@gmail.com",  R.drawable.mcs));
    }
}
