package com.example.crudcampuspedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit extends AppCompatActivity {
    EditText nohp,email,nama2;
    String nama,id;
    Button update;
    DataHelper dbHelper;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        dbHelper = new DataHelper(this);
        nama2 = findViewById(R.id.nama_editxt);
        nohp = findViewById(R.id.nohp_editxt);
        email = findViewById(R.id.email_editxt);
        update = findViewById(R.id.update_btn);
        nama = getIntent().getStringExtra("nama");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            nama2.setText(cursor.getString(1).toString());
            nohp.setText(cursor.getString(2).toString());
            email.setText(cursor.getString(3).toString());
        }
        // daftarkan even onClick pada btnSimpan
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update biodata set nama='"+
                        nama2.getText().toString() +"',nohp='"+
                        nohp.getText().toString() +"',email='" +
                        email.getText().toString()+"'where id='" +
                        cursor.getString(0)+"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}
