package com.example.hrapp_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.FirstPartyScopes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etFullName, etEmail, etPhone, etDepartment ,etAge,EtPassword;
    public Button sinUp;
    ProgressBar simpleProgressBa ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        simpleProgressBa =  findViewById(R.id.simpleProgressBar);
        etFullName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etDepartment = findViewById(R.id.et_Department);
        etAge = findViewById(R.id.et_age);
        EtPassword = findViewById(R.id.et_password);
        sinUp = findViewById(R.id.signup);
        sinUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNow();
            }
        });
    }



    public void registerNow(){
        String email = etEmail.getText().toString().trim();
        String name = etFullName.getText().toString().trim();
        String department = etDepartment.getText().toString().trim();
        String age = etAge.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = EtPassword.getText().toString().trim();

        if(name.isEmpty()) {
            etFullName.setError("Full Name is Required!");
            etFullName.requestFocus();
            return;
        }
        if(age.isEmpty()) {
            etAge.setError("Age is Required!");
            etAge.requestFocus();
            return;
        }
        if(email.isEmpty()) {
            etEmail.setError("Email is Required!");
            etEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Please Provide valid Email!");
            etEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            EtPassword.setError("Password is Required!");
            EtPassword.requestFocus();
            return;
        }

        if(password.length()<6) {
            EtPassword.setError("Min password length should be 6 characters!");
            EtPassword.requestFocus();
            return;
        }

        if(phone.isEmpty()) {
            etPhone.setError("Phone is Required!");
            etPhone.requestFocus();
            return;
        }

        if(department.isEmpty()) {
            etDepartment.setError("Department is Required!");
            etDepartment.requestFocus();
            return;
        }

        simpleProgressBa.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(name,email,department,age,phone);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this, "User has been registered successfully!", Toast.LENGTH_SHORT).show();
                                        simpleProgressBa.setVisibility(View.VISIBLE);
                                    }
                                    else {
                                        Toast.makeText(Register.this, "Failed to  register Tray Again!", Toast.LENGTH_SHORT).show();
                                        simpleProgressBa.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(Register.this, "Failed to  register Tray Again!", Toast.LENGTH_SHORT).show();
                            simpleProgressBa.setVisibility(View.GONE);
                        }
                    }
                });
        simpleProgressBa.setVisibility(View.GONE);
        simpleProgressBa.setVisibility(View.INVISIBLE);

    }

}