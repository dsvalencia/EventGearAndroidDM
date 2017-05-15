package com.example.raque.eventgear;

import android.app.Application;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

  //  DataBase db;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;
    private ListView listView;
    private CalendarView calendarView;
    private ArrayList list ;
    ArrayAdapter adapter;
    private Intent intent ;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView= (CalendarView) findViewById(R.id.calendarView);
         db= ((App) this.getApplication()).getDb();
        listView= (ListView) findViewById(R.id.listaEventos);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       // client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        list = new ArrayList<String>();
        Cursor eventosFechaActual = db.consultaTodos(db,calendarView.getDate());
        eventosFechaActual.moveToFirst();
        for(int i=0 ; i<eventosFechaActual.getCount(); i++){
           StringBuilder builder= new StringBuilder();
            builder.append(i +".- ");
            for(int j=1; j<5;j++)
                builder.append(eventosFechaActual.getString(j)+"/n");
            list.add( builder.toString());
        }
        adapter= new ArrayAdapter<Evento>(this,R.layout.list_item,R.id.list_item_name, list);
        listView.setAdapter(adapter);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                //Date date= new Date(i,i1,i2);
                Cursor eventosFechaActual = db.consultaTodos(db,calendarView.getDate());
                eventosFechaActual.moveToFirst();
                list.clear();
               // list.add("Esto fuca");
                for(int count=0 ; i<eventosFechaActual.getCount(); count++){
                    StringBuilder builder= new StringBuilder();
                    builder.append(count +".- ");
                    for(int j=1; j<5;j++)
                        builder.append(eventosFechaActual.getString(j)+"/n");
                    list.add( builder.toString());
                }
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        this.getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_addEvent:
                intent = new Intent(this, EventManager.class);
                this.startActivity(intent);
                return true;
            case R.id.context_ListEventsCat:
                intent = new Intent(this, EventList.class);
                intent.putExtra("Category", "Sports");
                this.startActivity(intent);
                return true;
            case R.id.context_modifyEvent:
                intent = new Intent(this, EventManager.class);
                this.startActivity(intent);
                return true;
            case R.id.context_deleteEvent:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("¿ Desea realmente eliminar éste elemento ?");
                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Evento event= (Evento)listView.getSelectedItem();
                        db.deleteElemento(db, event.getName());
                    }
                }); //AQUI SE REFERENCIARA A LA FUNCION DE ELIMINAR
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                });

                return true;
            case R.id.context_prefs:
                this.startActivity(new Intent(this, EventList.class));
                //OPCIONAL
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        // client.connect();
        //AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       // AppIndex.AppIndexApi.end(client, getIndexApiAction());
       // client.disconnect();
    }
}



