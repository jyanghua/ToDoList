package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewItemActivity extends AppCompatActivity {

    TextView title, addTitle, desc, date;
    EditText title2, desc2, date2;
    Button addNewTaskBtn, cancelBtn;
    DatabaseReference ref;

    Integer randNum = new Random().nextInt();
    String keyItem = Integer.toString((randNum));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        title = findViewById(R.id.titlePage);

        addTitle = findViewById(R.id.addItemTitleText);
        desc = findViewById(R.id.addItemDescriptionText);
        date = findViewById(R.id.addItemDateText);

        title2 = findViewById(R.id.newItemTitle);
        desc2 = findViewById(R.id.newItemDescription);
        date2 = findViewById(R.id.newItemDate);

        addNewTaskBtn = findViewById(R.id.btnAdd);
        cancelBtn = findViewById(R.id.btnCancel);


        // save task to Firebase
        addNewTaskBtn.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // put info to firebase
               // creates a new directory in firebase database
               ref = FirebaseDatabase.getInstance().getReference().child("ToDoList").child("ToDo" +
                       randNum);

               ref.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       // store data into the database..
                       dataSnapshot.getRef().child("itemTitle").setValue(title2.getText().toString());
                       dataSnapshot.getRef().child("itemDescription").setValue(desc2.getText().toString());
                       dataSnapshot.getRef().child("itemDate").setValue(date2.getText().toString());
                       dataSnapshot.getRef().child("itemKey").setValue(keyItem);

                       Intent intent = new Intent( NewItemActivity.this, MainActivity.class);
                       startActivity(intent);
                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {
                       Toast.makeText(getApplicationContext(), "NoData", Toast.LENGTH_SHORT).show();
                   }
               });

           }
        });

        // cancel button
       cancelBtn.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
       });
    }
}
