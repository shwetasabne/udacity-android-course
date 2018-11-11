package com.example.shwetasabne.khoj;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class SinglePersonDetails extends AppCompatActivity {

    TextView mSinglePersonName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_person_details);
        ActionBar actionBar = this.getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mSinglePersonName = (TextView) findViewById(R.id.tv_single_person_name);

        Intent mainItemsListIntent = getIntent();

        if(mainItemsListIntent.hasExtra(Intent.EXTRA_TEXT)) {
            String currentPersonName = mainItemsListIntent.getStringExtra(Intent.EXTRA_TEXT);
            actionBar.setTitle(currentPersonName);
            mSinglePersonName.setText(currentPersonName);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.single_person, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareForecastIntent());
        return true;
    }


    private Intent createShareForecastIntent() {
        String textToShare = "Hello there";

        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(textToShare)
                .getIntent();
        return shareIntent;
    }
}
