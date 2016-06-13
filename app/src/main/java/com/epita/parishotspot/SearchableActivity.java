package com.epita.parishotspot;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.epita.parishotspot.Models.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchableActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;
    private ArrayList<Map<String, String>> values;
    private SimpleAdapter adapter;
    private Toolbar toolbar;
    private static List<Record> records; //records of the search list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        listView = (ListView) findViewById(R.id.list_search);
        values = new ArrayList<Map<String, String>>();
        adapter = new SimpleAdapter(this,
                values,
                android.R.layout.simple_list_item_2,
                new String[] {"Nom site", "Adresse"},
                new int[] {android.R.id.text1, android.R.id.text2});
        records = new ArrayList<Record>();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


        toolbar = (Toolbar) findViewById(R.id.search_toolbar); // Attaching the layout to the toolbar object
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

        Intent intent = new Intent(this, SwapActivity.class);

//      Getting the requested record from the list
        Record record = records.get(pos);

        //Adding info about hotspot
        intent.putExtra("record", record);
        intent.putExtra("recordPos", pos);
        intent.putExtra("activity", "search");

        //Starting another activity to show book details
        startActivity(intent);
    }

    private void doMySearch(String query) {

        Log.e("Query info", query);
        for (Record record : MainActivity.getRecords()) {

            if ((record.getFields().getAdresse().toLowerCase()).contains(query.toLowerCase())
                    || (record.getFields().getNomSite().toLowerCase()).contains(query.toLowerCase())) {

                Map<String, String> elt = new HashMap<String, String>(2);
                elt.put("Nom site", (record.getFields().getNomSite()));
                elt.put("Adresse", (record.getFields().getAdresse()));
                values.add(elt);
                records.add(record);
            }
            adapter.notifyDataSetChanged();
        }

        toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        toolbar.setTitle("Résultats (" + values.size() + ")");
        setSupportActionBar(toolbar);
    }

    public static Record getRecord(int pos) {
        if (pos < 0 || pos > records.size() - 1)
            return null;
        return records.get(pos);
    }

    public static int countRecords() {
        return records.size();
    }

}
