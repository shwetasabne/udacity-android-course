package com.example.shwetasabne.khoj;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import com.example.shwetasabne.khoj.dataAdapters.*;
import com.example.shwetasabne.khoj.PersonListAdapter.PersonListAdapterClickHandler;

public class MainListItems extends AppCompatActivity implements PersonListAdapterClickHandler {

    private static final String TAG = "MainListItems";

    private RecyclerView mRecyclerView;
    private TextView mErrorMessageDisplay;
    private ProgressBar mloadingIndicator;

    private PersonListAdapter mPersonListAdapter;

    private MainListItemsDataGenerator mainListItemsDataGenerator = new MainListItemsDataGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate method called");

        setContentView(R.layout.activity_main_list_items);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_personList);

        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);

        mPersonListAdapter = new PersonListAdapter(this);
        mRecyclerView.setAdapter(mPersonListAdapter);


        mloadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadPersonData();

        /**
         * Temporary this will come from network call, set the username of logged in user
         */
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("pref_username", "shwetasabne");
        editor.putString("pref_email", "shwetasabne@gmail.com");
        editor.apply();
    }

    private void loadPersonData() {
        Log.i(TAG, "loadPersonData called");
        String[] data = mainListItemsDataGenerator.getMainListItems();
        mPersonListAdapter.setPersonListData(data);
    }

    private void showPersonDataView() {
        Log.i(TAG, "showPersonDataView called");
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(String currentPerson) {
        Context context = this;

        Class destinationActivity = SinglePersonDetails.class;

        Intent singlePersonIntent = new Intent(context, destinationActivity);

        singlePersonIntent.putExtra(Intent.EXTRA_TEXT, currentPerson);

        startActivity(singlePersonIntent);

        //Toast.makeText(context, currentPerson, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedMenuItem = item.getItemId();
        if (selectedMenuItem == R.id.action_search) {
            Context context = MainListItems.this;
            String toastMsg = "Search selected";
            Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show();
        } else if(selectedMenuItem == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        return true;
    }

}
