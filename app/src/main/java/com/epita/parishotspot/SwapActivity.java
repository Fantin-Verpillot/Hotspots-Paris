package com.epita.parishotspot;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.epita.parishotspot.Models.Record;

public class SwapActivity extends AppCompatActivity {

    private SwapAdapter swapAdapter;
    private static String listType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);

        // Set "main" or "search" depending on the previous activity
        listType = (String) getIntent().getSerializableExtra("activity");

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Create the adapter that will return a fragment for each of the three primary sections of the activity.
        swapAdapter = new SwapAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(swapAdapter);
        viewPager.setCurrentItem((Integer) getIntent().getSerializableExtra("recordPos"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_swap, menu);
        return true;
    }

    public static String getListType() {
        return listType;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            DetailFragment fragment = (DetailFragment) swapAdapter.getCurrentFragment();
            sendIntent.putExtra(Intent.EXTRA_TEXT, fragment.getShareDescription());
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share_with_label)));
            return true;
        }

        if (id == R.id.action_info) {
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
            return true;
        }

        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
