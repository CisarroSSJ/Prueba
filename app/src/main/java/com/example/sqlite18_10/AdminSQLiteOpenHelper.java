package com.example.sqlite18_10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    //al ejecutar se crea una base de datos
    @Override
    public void onCreate(SQLiteDatabase Produccion) {
        Produccion.execSQL("create table Usuarios (ID_Usuario int primary key, NombreUsuario text, AreaUsuario text)");


    }

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //se actualiza
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
