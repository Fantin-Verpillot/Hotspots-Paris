package com.epita.parishotspot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.epita.parishotspot.Models.Record;

public class DetailActivity extends AppCompatActivity {


    private TextView textViewItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Init values
        textViewItemName = (TextView) findViewById(R.id.textViewItemName);

        //Getting record
        Record record = (Record) getIntent().getSerializableExtra("record");

        //Displaying values
        textViewItemName.setText(record.getFields().getNomSite());

    }
}
