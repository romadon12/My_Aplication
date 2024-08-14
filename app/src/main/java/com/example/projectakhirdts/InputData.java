package com.example.projectakhirdts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputData extends AppCompatActivity {
    Button btninputedit;
    EditText ednama,edumur,edmotto;
    DatabaseHelper dbmaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        btninputedit =findViewById(R.id.buttonsimpan);
        ednama = findViewById(R.id.editnama);
        edumur = findViewById(R.id.editumur);
        edmotto = findViewById(R.id.editmotto);
        dbmaster = new DatabaseHelper(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");

        if (id != null) {
            Cursor data = dbmaster.getAllData();
            if (data.moveToFirst()) {
                do {
                    if (data.getString(0).equals(id)) {
                        ednama.setText(data.getString(1));
                        edumur.setText(data.getString(2));
                        edmotto.setText(data.getString(3));
                        btninputedit.setText("Update Data");
                        break;
                    }
                } while (data.moveToNext());
            }

            btninputedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated = dbmaster.updateData(id, ednama.getText().toString(), Integer.parseInt(edumur.getText().toString()),edmotto.getText().toString());
                    if (isUpdated){
                        Toast.makeText(InputData.this, "Data Updated", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(InputData.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(InputData.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            btninputedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = dbmaster.insertData(ednama.getText().toString(), Integer.parseInt(edumur.getText().toString()),edmotto.getText().toString());
                    if (isInserted){
                        Toast.makeText(InputData.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(InputData.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(InputData.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                }
            });
        }


    }
}