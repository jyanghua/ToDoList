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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView titlePage, subtitlePage;
    private Button btnCreate, btnSearch;

    private DatabaseReference reference;
    private RecyclerView myList;
    private ArrayList<MyItem> itemList;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlePage = findViewById(R.id.titlePage);
        subtitlePage = findViewById(R.id.subtitlePage);

        btnCreate = findViewById(R.id.btnCreate);
        btnSearch = findViewById(R.id.btnSearch);

        // Listener that changes activity to allow the user to create a new item
        btnCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, NewItemActivity.class);
                startActivity(intent);
            }
        });

        // Listener that changes activity to allow the user search for items
        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        // Lists that will save locally the information that is queried from the database
        myList = findViewById(R.id.myList);
        myList.setLayoutManager(new LinearLayoutManager(this));
        itemList = new ArrayList<MyItem>();

        // Fetch data from Firebase
        reference = FirebaseDatabase.getInstance().getReference().child("ToDoList");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSS: dataSnapshot.getChildren()){
                    MyItem i = dataSS.getValue(MyItem.class);
                    itemList.add(i);
                }
                itemAdapter = new ItemAdapter(MainActivity.this, itemList);
                myList.setAdapter(itemAdapter);
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
