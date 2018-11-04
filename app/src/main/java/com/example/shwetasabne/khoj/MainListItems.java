package com.example.shwetasabne.khoj;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import com.example.shwetasabne.khoj.dataAdapters.*;

public class MainListItems extends AppCompatActivity {

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

        mPersonListAdapter = new PersonListAdapter();
        mRecyclerView.setAdapter(mPersonListAdapter);


        mloadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        loadPersonData();
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

}
