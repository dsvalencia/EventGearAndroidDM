package com.example.raque.eventgear;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ContextThemeWrapper;

/**
 * Created by Sanchez on 12/12/16.
 */
public class DataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Events";
    private static final int DATABASE_VERSION = 2;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL("DELETE TABLE Events");

            db.execSQL("CREATE TABLE IF NOT EXISTS Events("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name string(255) NOT NULL,"
                    + "descripcion string(1000) NOT NULL,"
                    + "categoria string(30),"
                    + "date date NOT NULL"
                    + ")");
            db.execSQL("INSERT INTO Events (id, name, descripcion, categoria, date) Values(?,?,?,?,?)", new String[]{ "event", "dasdadasdsadas", "Sports", "1483570800000"});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w(DataBase.class.getName(), "actualizando base de datos" + i + "a la versión" + i1);
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.execSQL("DROP DATABASE IF EXISTS Events");
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
        this.onCreate(sqLiteDatabase);
    }

    public  Cursor consultaCategoria(DataBase dataB, String category) {
        SQLiteDatabase db = dataB.getReadableDatabase();

        String query = "SELECT * FROM events"; //WHERE categoria LIKE "+"%"+category+"%";
        Cursor eventCursor = db.rawQuery(query, null);
        return eventCursor;
    }

    public  Cursor consultaTodos(DataBase dataB) {
        SQLiteDatabase db = dataB.getReadableDatabase();
        String query = "SELECT * FROM events";
        Cursor eventCursor = db.rawQuery(query, null);
        return eventCursor;
    }

    public Cursor consultaTodos(DataBase dataB, long date) {
        SQLiteDatabase db = dataB.getReadableDatabase();

        String query = "SELECT * FROM events WHERE date like " + date + "";
        Cursor eventCursor = db.rawQuery(query, null);
        return eventCursor;
    }

    public void deleteElemento(DataBase dataB, String name) {
        SQLiteDatabase db = dataB.getWritableDatabase();
        db.execSQL("DELETE * FROM events WHERE name <> ?" , new String[]{name});
    }
    public void addElemento(DataBase dataB, Evento event){
        SQLiteDatabase db = dataB.getWritableDatabase();
        ContentValues values= new ContentValues();
        //values.put("id","eee");
        values.put("name","Evenr");
        values.put("descripcion","Una descripcion");
        values.put("categoria","una categoria");
        values.put("date",event.getDate());
        long events = db.insert("events", null,values);
    }
}