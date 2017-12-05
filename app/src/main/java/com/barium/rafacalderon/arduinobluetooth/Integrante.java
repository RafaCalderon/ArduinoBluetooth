package com.barium.rafacalderon.arduinobluetooth;

import android.graphics.drawable.Drawable;

public class Integrante {
    private String nombre, gmail;
    private int imagen;

    public Integrante(String nombre, String gmail, int imagen) {
        this.nombre = nombre;
        this.gmail = gmail;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
