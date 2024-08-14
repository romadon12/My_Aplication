package com.example.projectakhirdts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    Button btnregister, btnlogin;
    EditText eduserlogin, edpasslogin;
    DatabaseHelper dblogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi tombol dan EditText
        btnlogin = findViewById(R.id.buttonLogin); // Tombol Login
        btnregister = findViewById(R.id.buttonRegister); // Tombol Register
        eduserlogin = findViewById(R.id.editTextText); // EditText Username
        edpasslogin = findViewById(R.id.editTextTextPassword); // EditText Password

        // Inisialisasi DatabaseHelper
        dblogin = new DatabaseHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String suser = eduserlogin.getText().toString();
                String spass = edpasslogin.getText().toString();
                Boolean checkUserPassword = dblogin.checkUserPassword(suser, spass);
                if (checkUserPassword) {
                    Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
    }
}
