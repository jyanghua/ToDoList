/*
 * ICS 45J - Fall 2019
 * Lab 5 - Android Studio
 * Group name: JJJ
 * Members: Lillian Won, Linda Le, Jack Yang Huang
 */

package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private ImageButton btnSearchWords;
    private EditText searchText;
    private DatabaseReference reference;

    private RecyclerView mySearchResults;
    private ArrayList<MyItem> itemList;
    private ItemAdapter itemAdapter;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btnSearchWords = findViewById(R.id.btnSearchWords);
        searchText = findViewById(R.id.searchText);

        //Fetch data from Firebase
        reference = FirebaseDatabase.getInstance().getReference().child("ToDoList");

        btnSearchWords.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                firebaseSearch(searchText.getText().toString());
            }
        });
    }

    private void firebaseSearch(String keywords){
        Toast.makeText(SearchActivity.this, "Started search", Toast.LENGTH_SHORT).show();

        //Database setup
        mySearchResults = findViewById(R.id.mySearchResults);
        mySearchResults.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<MyItem>();

        /*
        // Only use for a simple query
        Query firebaseQuery = reference.orderByChild("itemTitle").startAt(keywords).endAt(keywords + "\uf8ff");
        */

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String words = searchText.getText().toString();
                for(DataSnapshot dataSS: dataSnapshot.getChildren()){
                    MyItem i = dataSS.getValue(MyItem.class);
                    if(i.getItemDate().toLowerCase().contains(words.toLowerCase()) ||
                            i.getItemDescription().toLowerCase().contains(words.toLowerCase()) ||
                            i.getItemTitle().toLowerCase().contains(words.toLowerCase())){
                        itemList.add(i);
                    }
                }
                itemAdapter = new ItemAdapter(SearchActivity.this, itemList);
                mySearchResults.setAdapter(itemAdapter);
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Shows database error message
                Toast.makeText(getApplicationContext(), "Could not find data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
