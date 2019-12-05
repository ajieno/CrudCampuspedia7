package com.example.crudcampuspedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class add extends AppCompatActivity {
    EditText nama,nohp,email;
    Button add;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        nama = findViewById(R.id.nama_editxt);
        nohp = findViewById(R.id.nohp_editxt);
        email = findViewById(R.id.email_editxt);
        add = findViewById(R.id.add_btn);

        dbHelper = new DataHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nama.getText().toString().equals("")||nohp.getText().toString().equals("")||email.getText().toString().equals("")){
                    Toast.makeText(add.this, "Silahkan isi bagian yang kosong", Toast.LENGTH_SHORT).show();
                }else{add();}
            }
        });

    }

    private  void add(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("insert into biodata(nama, nohp, email) values('"+
                nama.getText().toString() + "','" +
                nohp.getText().toString() + "','" +
                email.getText().toString() + "')");
        Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
        finish();
        Intent intent = new Intent(add.this, MainActivity.class);
    }
}
