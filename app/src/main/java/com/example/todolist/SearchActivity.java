package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // get intent, verify action and get query
        Intent intent = getIntent();
        if ( Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            // query is achieved and passsed to domysearch
            // doMySearch is the method where actual search operation is done
            //doMySearch();
        }

        // Need to look up how to get data stored in database..
    }
}
