package com.example.apitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    private boolean userInputFinished = false;


    EditText edtName, edtJob, edtDob;
    RadioGroup rdgLicense, rdgSex;
    Spinner spnRelationship;
    Button btnPost, btnBack;
    /* Options in Spinner */
    String[] spnArray = {"SINGLE", "RELATIONSHIP", "MARRIED"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        edtName = findViewById(R.id.edtNameP);
        edtJob = findViewById(R.id.edtJobP);
        edtDob = findViewById(R.id.edtDobP);
        /* RadioGroups */
        rdgLicense = findViewById(R.id.rdgLicenseP);
        rdgSex = findViewById(R.id.rdgSexP);
        /* Spinner */
        spnRelationship = findViewById(R.id.spnRelationshipP);
        /* Buttons */
        btnPost = findViewById(R.id.btnPostP);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("name", "click");
                if (isUserDone()) {
                    PersonInterface service = ServiceBuilder.buildService(PersonInterface.class);
                    Person p = generatePerson();
                    service.createPerson(p).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Toast.makeText(PostActivity.this, "SUC", Toast.LENGTH_LONG).show();
                            Intent newIntent = new Intent();
                            setResult(RESULT_OK, newIntent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(PostActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        btnBack = findViewById(R.id.btnPostBackP);
        /* Spinner Adapter*/
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_object,
                spnArray
        );
        spnRelationship.setAdapter(adapter);
    }

    protected Person generatePerson() {
        boolean license = false;
        char sex = 'M';
        String name = edtName.getText().toString();
        String job = edtJob.getText().toString();
        int dob = Integer.parseInt(edtDob.getText().toString());
        Log.d("name", String.format("%s", dob));
        String relationship = spnRelationship.getSelectedItem().toString();

        if (rdgLicense.getCheckedRadioButtonId() == (findViewById(R.id.rdbHasLicenseP)).getId()) { license = true; }
        if (rdgSex.getCheckedRadioButtonId() == (findViewById(R.id.rdbFemaleP)).getId()) { sex = 'F'; }
        Person p = new Person(
                name,
                job,
                dob,
                license,
                sex,
                relationship
        );
        Log.d("name", String.format("%s %s %s %s %s", name, job, dob, license, relationship));
        return p;
    }

    private boolean isUserDone() {
        return !edtName.getText().toString().isEmpty()
                && !edtJob.getText().toString().isEmpty()
                && !edtDob.getText().toString().isEmpty()
                && rdgLicense.getCheckedRadioButtonId() != -1
                && rdgSex.getCheckedRadioButtonId() != -1
                && spnRelationship.getSelectedItemPosition() != -1;
    }
}