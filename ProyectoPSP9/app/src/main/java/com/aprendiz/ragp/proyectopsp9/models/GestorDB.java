package com.aprendiz.ragp.proyectopsp9.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GestorDB  extends SQLiteOpenHelper{
    //Constructor para crear la base de datos
    public GestorDB(Context context) {
        super(context, Constants.DATABASE_NAME,null, Constants.DATABASE_VERSION);
    }

    //Método para cuando se cree la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.TABLE_PROJECT);
        db.execSQL(Constants.TABLE_TIMELOG);
        db.execSQL(Constants.TABLE_DEFECTLOG);

    }

    //Método para cuando se actualice la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
