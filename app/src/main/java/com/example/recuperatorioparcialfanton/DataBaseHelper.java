package com.example.recuperatorioparcialfanton;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper (Context context) {
        super(context,"Mi base de datos",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE datos(value varchar(512));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*db.execSQL("DROP TABLE IF EXISTS datos;");
        this.onCreate(db);*/
    }

    public void addDato(String data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("value",data);
        db.insert("datos",null,contentValues);
        db.close();
    }

    public List<String> getDatos(){
        SQLiteDatabase db = getReadableDatabase();
        String[] colums = {"value"};
        Cursor cursor = db.query("datos",colums,null,null,null,null,null);
        int cantidadDatos = cursor.getCount();
        List<String> listaDatos = new ArrayList<>();
        for (int i=0 ; i < cantidadDatos ; i++){
            cursor.moveToPosition(i);
            listaDatos.add(cursor.getString(0));
        }

        return listaDatos;
    }
}
