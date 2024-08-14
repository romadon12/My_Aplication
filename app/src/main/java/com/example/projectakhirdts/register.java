package com.example.projectakhirdts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    Button btnregister;
    EditText eduser, edpass;
    DatabaseHelper dblogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnregister = findViewById(R.id.buttonReg);
        eduser = findViewById(R.id.register1);
        edpass = findViewById(R.id.register2);
        dblogin = new DatabaseHelper(this);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = eduser.getText().toString();
                String password = edpass.getText().toString();
                Boolean checkUser = dblogin.checkUser(user);
                if (checkUser == false){
                    Boolean insert = dblogin.insertUser(user,password);
                    if (insert == true){
                        Toast.makeText(register.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(register.this, "Register Failed", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
                else {
                    Toast.makeText(register.this, "user Already Exists", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}