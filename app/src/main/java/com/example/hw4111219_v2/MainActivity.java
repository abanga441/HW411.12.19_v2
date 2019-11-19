//Written by Angad Banga, Hatu Kanu, and Amy Orr. LFG

package com.example.hw4111219_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //declare objects
    EditText editTextBirdName, editTextZipCode, editTextUserName;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect to UI
        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextZipCode = findViewById(R.id.editTextZipCode);
        editTextUserName = findViewById(R.id.editTextUserName);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        //make sure the buttons are working
        buttonSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //put in that firebase doeeee

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("hw4lfg");


        if(view == buttonSubmit) {
            //get text from EditText
            String createbirdname = editTextBirdName.getText().toString();
            String createbirdsighter = editTextUserName.getText().toString();
            Integer createzipcode = Integer.parseInt(editTextZipCode.getText().toString());

            //create a new entry of class BirdSightings into the database

            BirdSightings myBirdSighting = new BirdSightings(createbirdname, createbirdsighter, createzipcode);

            myRef.push().setValue(myBirdSighting);
        }

        if(view == buttonSubmit) {
            Toast.makeText(this, "Your record has been submitted. Thanks!", Toast.LENGTH_SHORT).show();

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater Inflater = getMenuInflater();
        Inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.itemInputForm) {
            Toast.makeText(this, "You are already here", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.itemSearch) {
            Intent SearchIntent = new Intent(this, SearchActivity.class);
            startActivity(SearchIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
