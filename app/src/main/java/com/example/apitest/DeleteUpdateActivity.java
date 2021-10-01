package com.example.apitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteUpdateActivity extends AppCompatActivity {
    private int personID;

    EditText edtName, edtJob, edtDob;
    RadioGroup rdgLicense, rdgSex;
    Spinner spnRelationship;
    Button btnDelete, btnBack, btnUpdate;
    /* Options in Spinner */
    String[] spnArray = {"SINGLE", "RELATIONSHIP", "MARRIED"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_update);

        /* GET PERSON OBJECT */
        Intent intent = getIntent();
        Person person = (Person) intent.getSerializableExtra("person");
        personID = person.getId();
//        Person person = new Gson().fromJson(personJson, Person.class);



        /* EditText */
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, new Intent());
                finish();
            }
        });

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete # WORKS
                PersonInterface service = ServiceBuilder.buildService(PersonInterface.class);
                service.deletePerson(personID).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(DeleteUpdateActivity.this, "DELETED", Toast.LENGTH_SHORT).show();
                        Intent newIntent = new Intent();
                        setResult(RESULT_OK, newIntent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(DeleteUpdateActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonInterface service = ServiceBuilder.buildService(PersonInterface.class);
                Person p = generatePerson();

                service.updatePerson(p).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
//                        Toast.makeText(DeleteUpdateActivity.this, p.getDob(), Toast.LENGTH_SHORT).show();
                        Intent newIntent = new Intent();
                        setResult(RESULT_OK, newIntent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(DeleteUpdateActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        /* EditText */
        edtName = findViewById(R.id.edtName);
        edtJob = findViewById(R.id.edtJob);
        edtDob = findViewById(R.id.edtDob);
        /* RadioGroups */
        rdgLicense = findViewById(R.id.rdgLicense);
        rdgSex = findViewById(R.id.rdgSex);
        /* Spinner */
        spnRelationship = findViewById(R.id.spnRelationship);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_object,
                spnArray
        );

        /* SETTING VALUES */
        spnRelationship.setAdapter(adapter);
        Log.d("name", "name");
        edtName.setText(person.getName());
        Log.d("name", "job");
        edtJob.setText(person.getJob());
        edtDob.setText(Integer.toString(person.getDob()));
        if (person.getSex() == 'M') { rdgSex.check(R.id.rdbMale); }
        else { rdgSex.check(R.id.rdbFemale); }
        if (person.getDriversLicense()) { rdgLicense.check(R.id.rdbHasLicense); }
        else { rdgLicense.check(R.id.rdbNoLicense); }
        /* UGLY AS SIN, BUT IT WORKS */
        switch (person.getRelationship().toUpperCase()) {
            case "SINGLE":
                spnRelationship.setSelection(0);
                break;
            case "RELATIONSHIP":
                spnRelationship.setSelection(1);
                break;
            case "MARRIED":
                spnRelationship.setSelection(2);
                break;
        }
    }

    protected Person generatePerson() {
        boolean license = false;
        char sex = 'M';
        int id = personID;
        String name = edtName.getText().toString();
        String job = edtJob.getText().toString();
        int dob = Integer.parseInt(edtDob.getText().toString());
        String relationship = spnRelationship.getSelectedItem().toString();

        if (rdgLicense.getCheckedRadioButtonId() == (findViewById(R.id.rdbHasLicense)).getId()) { license = true; }
        if (rdgSex.getCheckedRadioButtonId() == (findViewById(R.id.rdbFemale)).getId()) { sex = 'F'; }
        Person p = new Person(id, name, job, dob, license, sex, relationship);
        return p;
    }
}
