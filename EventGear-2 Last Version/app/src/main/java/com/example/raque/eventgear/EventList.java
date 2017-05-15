package com.example.raque.eventgear;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventList extends AppCompatActivity {


    DataBase db;

    private ListView listView;
    private ArrayList list ;
    private ArrayAdapter adapter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_list);
        Intent data= this.getIntent();
        String category= (String) data.getExtras().get("Category");
        db= ((App) this.getApplication()).getDb();
        listView= (ListView) findViewById(R.id.listaEventos);
        textView=  (TextView) findViewById(R.id.list_textView);
        textView.setText("Eventos de categoría " + category);
        list = new ArrayList<String>();

        Cursor eventosFechaActual = db.consultaCategoria(db, category);
        eventosFechaActual.moveToFirst();
        for(int i=0 ; i<eventosFechaActual.getCount(); i++){
            StringBuilder builder= new StringBuilder();
            builder.append(i +".- ");
            for(int j=1; j<5;j++)
                builder.append(eventosFechaActual.getString(j)+"\n");
            list.add( builder.toString());
        }
        adapter= new ArrayAdapter<Evento>(this,R.layout.list_item,R.id.list_item_name, list);

        listView.setAdapter(adapter);

    }
}


