package com.barium.rafacalderon.arduinobluetooth;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME="mydata";
    private static final int VERSION = 1;

    public static final String TABLA = "ambiente";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEMPERATURA = "temperatura";
    public static final String COLUMN_HUMEDAD = "humedad";
    public static final String COLUMN_FECHA_HORA = "fecha_hora";

    public ConexionHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script;
        script = "CREATE TABLE "+TABLA+"("+COLUMN_ID+" integer primary key autoincrement,";
        script += COLUMN_TEMPERATURA+" text,"+COLUMN_HUMEDAD+" text,"+COLUMN_FECHA_HORA+" text)";
        db.execSQL(script);
        db.execSQL("INSERT INTO ambiente VALUES (null, '20','20','2017-11-04 22:05:52')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
