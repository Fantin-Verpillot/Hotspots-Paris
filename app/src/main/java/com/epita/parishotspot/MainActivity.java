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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.epita.parishotspot.Models.Hotspot;
import com.epita.parishotspot.Models.Record;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;
    private Hotspot hotspots;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
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
                Toast.makeText(getApplicationContext(), String.format("Cannot retrieve hotspot list"), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showList() {

        String[] values = new String[hotspots.getRecords().size()];

        for (int i = 0; i < hotspots.getRecords().size(); ++i) {
            values[i] = hotspots.getRecords().get(i).getFields().getNomSite() == null ? "" : hotspots.getRecords().get(i).getFields().getNomSite();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);

        listView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

        Intent intent = new Intent(this, SwapActivity.class);

//      Getting the requested record from the list
        Record record = hotspots.getRecords().get(pos);

        //Adding info about hotspot
        intent.putExtra("record", record);

        //Starting another activity to show book details
        startActivity(intent);

    }
}
