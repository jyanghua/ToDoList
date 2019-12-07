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


public class DeleteItemActivity extends AppCompatActivity {

    private EditText title, desc, date;
    private Button btnCancel, btnDelete;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        title = findViewById(R.id.newTitle);
        desc = findViewById(R.id.newDescription);
        date = findViewById(R.id.newDate);

        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnRemove);

        title.setText((getIntent().getStringExtra("itemTitle")));
        desc.setText((getIntent().getStringExtra("itemDescription")));
        date.setText((getIntent().getStringExtra("itemDate")));
        final String key = getIntent().getStringExtra("itemKey");

        // Listener for the cancel button. Finishes current activity
        btnCancel.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        ref = FirebaseDatabase.getInstance().getReference().child("ToDoList").child("ToDo" +
                key);

        // Deletes the current item from the database
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(DeleteItemActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}


