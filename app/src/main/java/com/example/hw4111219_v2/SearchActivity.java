package com.example.hw4111219_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    //declare objects
    EditText editTextZipCodeSearch;
    Button buttonSearch;
    TextView textViewBirdName, textViewZipCode, textViewUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //connect to the UI
        editTextZipCodeSearch = findViewById(R.id.editTextZipCodeSearch);
        buttonSearch = findViewById(R.id.buttonSearch);
        textViewBirdName = findViewById(R.id.textViewBirdName);
        textViewZipCode = findViewById(R.id.textViewZipCode);
        textViewUserName = findViewById(R.id.textViewUserName);

        buttonSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("hw4lfg");

        if(view == buttonSearch){


        Integer findZipCode = Integer.parseInt(editTextZipCodeSearch.getText().toString());

        myRef.orderByChild("zipcode").equalTo(findZipCode).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                BirdSightings foundBird = dataSnapshot.getValue(BirdSightings.class);

                //grab the bird name, the zip code, and the user who put it in
                String findBirdName = foundBird.birdname;
                Integer findZipCode = foundBird.zipcode;
                String findUser = foundBird.birdsighter;

                //diplay the info
                textViewBirdName.setText(findBirdName);
                textViewZipCode.setText(findZipCode.toString());
                textViewUserName.setText(findUser);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater Inflater = getMenuInflater();
        Inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.itemInputForm) {
            Intent InputIntent = new Intent(this, MainActivity.class);
            startActivity(InputIntent);
        } else if(item.getItemId() == R.id.itemSearch) {
            Toast.makeText(this, "You are already here", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
