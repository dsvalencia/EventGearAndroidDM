package com.example.raque.eventgear;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

public class EventManager extends AppCompatActivity {

    private Button acept;
    private Button cancel;
    private DataBase db;
    private DatePicker datePicker;
    private TextView textName;
    Intent data;
    private TextView textDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_manager);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.event_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner spinner = (Spinner) this.findViewById( R.id.event_type_spinner );
        Log.d( "EventGear.EventManager", "Spinner: " + spinner );
        spinner.setAdapter(adapter);
        data= this.getIntent();
        acept= (Button) findViewById(R.id.button2);
        cancel= (Button) findViewById(R.id.button);
        datePicker= (DatePicker) findViewById(R.id.datePicker);
        db= ((App) this.getApplication()).getDb();
         textName = (TextView) findViewById(R.id.event_name_edText);
        textDescription = (TextView) findViewById(R.id.event_description_edText);
        acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addElemento(db,new Evento(new Date(datePicker.getYear(), datePicker.getMonth(),datePicker.getDayOfMonth()), textName.getText().toString(), spinner.getSelectedItem().toString(), textDescription.getText().toString()));
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }
}
