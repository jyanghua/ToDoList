/*
 * ICS 45J - Fall 2019
 * Lab 5 - Android Studio
 * Group name: JJJ
 * Members: Lillian Won, Linda Le, Jack Yang Huang
 */

package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditItemActivity extends AppCompatActivity {

    private EditText title, desc, date;
    private Button btnCancel, btnSave;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        title = findViewById(R.id.newTitle);
        desc = findViewById(R.id.newDescription);
        date = findViewById(R.id.newDate);

        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        title.setText((getIntent().getStringExtra("itemTitle")));
        desc.setText((getIntent().getStringExtra("itemDescription")));
        date.setText((getIntent().getStringExtra("itemDate")));
        final String key = getIntent().getStringExtra("itemKey");

        // Save button that will update the changes inputted by the user to the database
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ref = FirebaseDatabase.getInstance().getReference().child("ToDoList").child("ToDo" +
                        key);

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Stores the data into the database
                        dataSnapshot.getRef().child("itemTitle").setValue(title.getText().toString());
                        dataSnapshot.getRef().child("itemDescription").setValue(desc.getText().toString());
                        dataSnapshot.getRef().child("itemDate").setValue(date.getText().toString());
                        dataSnapshot.getRef().child("itemKey").setValue(key);

                        // Switches back to the main activity window
                        Intent intent = new Intent( EditItemActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Listener for the cancel button. Finishes current activity
        btnCancel.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }
}
