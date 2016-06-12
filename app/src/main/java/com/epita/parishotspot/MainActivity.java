package com.epita.parishotspot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.epita.parishotspot.Models.Hotspot;
import com.epita.parishotspot.Models.Record;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;
    private static Hotspot hotspots;
    private Toolbar toolbar;

    private ArrayList<Map<String, String>> values;
    private SimpleAdapter adapter;
    private int curPos;
    private boolean flagLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        curPos = 0;
        values = new ArrayList<Map<String, String>>();
        adapter = new SimpleAdapter(this,
                values,
                android.R.layout.simple_list_item_2,
                new String[] {"Nom site", "Adresse"},
                new int[] {android.R.id.text1, android.R.id.text2});

        listView.setAdapter(adapter);
        flagLoading = false;

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

                if (hotspots != null && values.size() < hotspots.getRecords().size()) {
                    if (i + i1 == i2 && i2 != 0) {
                        if (!flagLoading) {
                            flagLoading = true;
                            showList();
                        }
                    }
                }

            }
        });

        getHotspots();

        //Setting onItemClickListener to listview
        listView.setOnItemClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar); // Attaching the layout to the toolbar object
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            getHotspots();
            return true;
        }

        if (id == R.id.action_search) {
            return true;
        }

        if (id == R.id.action_info) {
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    private void getHotspots() {
        final ProgressDialog loading = ProgressDialog.show(this, "Fetching Data", "Please wait...", false, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HotspotService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HotspotService service = retrofit.create(HotspotService.class);
        Call<Hotspot> call = service.getHotspots();

        call.enqueue(new Callback<Hotspot>() {
            @Override
            public void onResponse(Call<Hotspot> call, Response<Hotspot> response) {
                loading.dismiss();
                hotspots = response.body();
                showList();
            }

            @Override
            public void onFailure(Call<Hotspot> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(getApplicationContext(), String.format("Cannot retrieve hotspot list"), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /* Show 10 elements */
    private void showList() {
        flagLoading = false;
        for (int i = 0; i < 10 && curPos < hotspots.getRecords().size(); ++i, ++curPos) {
            Map<String, String> elt = new HashMap<String, String>(2);
            elt.put("Nom site", (hotspots.getRecords().get(i).getFields().getNomSite()));
            elt.put("Adresse", hotspots.getRecords().get(i).getFields().getAdresse());
            values.add(elt);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

        Intent intent = new Intent(this, SwapActivity.class);

//      Getting the requested record from the list
        Record record = hotspots.getRecords().get(pos);

        //Adding info about hotspot
        intent.putExtra("record", record);
        intent.putExtra("recordPos", pos);

        //Starting another activity to show book details
        startActivity(intent);
    }

    public static Record getRecord(int pos) {
        if (pos < 0 || pos > hotspots.getRecords().size() - 1)
            return null;

        return hotspots.getRecords().get(pos);
    }

    public static int countRecords() {
        return hotspots.getRecords().size();
    }
}
