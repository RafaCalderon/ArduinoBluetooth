package com.barium.rafacalderon.arduinobluetooth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CRUDInfoAmbiente {
    private ConexionHelper helper;
    private SQLiteDatabase db;
    private ContentValues values;

    public CRUDInfoAmbiente(Context context) {
        helper = new ConexionHelper(context);
        values = new ContentValues();
    }

    public void insertar(InfoAmbiente info){
        db = helper.getWritableDatabase();
        values.clear();
        values.put(ConexionHelper.COLUMN_TEMPERATURA, info.getTemperatura());
        values.put(ConexionHelper.COLUMN_HUMEDAD, info.getHumedad());
        values.put(ConexionHelper.COLUMN_FECHA_HORA, info.getFecha_hora());
        db.insert(ConexionHelper.TABLA, null, values);
        db.close();
    }

    public List<InfoAmbiente> infoAmbienteList(){
        List<InfoAmbiente> list = new ArrayList<>();
        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ConexionHelper.TABLA, null);
        while(c.moveToNext()){
            InfoAmbiente info = new InfoAmbiente();
            info.setId(c.getInt(0));
            info.setTemperatura(c.getString(1));
            info.setHumedad(c.getString(2));
            info.setFecha_hora(c.getString(3));
            list.add(info);
        }
        db.close();
        return list;
    }
}
