package com.example.apitest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ListView listPerson;
    private PersonInterface service;
    private List<Person> persons;

    private int UPDATE_DELETE = 1;
    private int POST_ACTIVITY = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listPerson = findViewById(R.id.listPerson);

        /* API */
        service = ServiceBuilder.buildService(PersonInterface.class);
        Call<List<Person>> apiPersons = service.getPersons();
        
        apiPersons.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                persons = response.body();
                if (persons.isEmpty()) {
                    Toast.makeText(MainActivity.this, "SUCC", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("size", Integer.toString(persons.size()));
                    PersonAdapter personAdapter = new PersonAdapter(persons, MainActivity.this);
                    listPerson.setAdapter(personAdapter);
//                    Toast.makeText(MainActivity.this, Integer.toString(persons.size()), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void personListOnClick(Person p) {
        Intent intent = new Intent(this, DeleteUpdateActivity.class);
        intent.putExtra("person", p);

        startActivityForResult(intent, UPDATE_DELETE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void fabOnClick(View view) {
        Intent intent = new Intent(this, PostActivity.class);
        startActivityForResult(intent, POST_ACTIVITY);
    }
}