package com.example.raque.eventgear;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static android.R.attr.id;

/**
 * Created by Sanchez on 12/12/16.
 */
public class Evento {
    private final Date fecha;
    private final int id;
    private String name;
    private String descripcion;
    private String categoria;

    public Evento(Date date, String name, String categoria, String descripcion) {
        this.fecha= date;
        this.name = name;
        this.id = 2132411341;
        this.descripcion= descripcion;
        this.categoria=categoria;
    }

    @Override
    public String toString() {
        return "Date: "+ this.fecha.toString()+"\n"+name;

    }

    public int getID() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        return dateFormat.format(fecha);
    }
}
