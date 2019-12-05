package com.example.crudcampuspedia;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class show extends AppCompatActivity {

    TextView nama,nohp,email;
    DataHelper dbHelper;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        dbHelper = new DataHelper(this);

        nama = findViewById(R.id.nama1);
        nohp = findViewById((R.id.nohp1));
        email = findViewById(R.id.email1);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            nama.setText(cursor.getString(1).toString());
            nohp.setText(cursor.getString(2).toString());
            email.setText(cursor.getString(3).toString());
        }

    }
}
