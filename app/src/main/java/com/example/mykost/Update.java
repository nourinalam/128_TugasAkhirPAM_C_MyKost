package com.example.mykost;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Update extends AppCompatActivity {

    // Dekalrasi Vartiable Sesuai dengan layout Update
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3, text4, text5,text6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // inisialisasi variable dengan Id sesuai dengan Layout Update
        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.editTextup1);
        text2 = (EditText) findViewById(R.id.editTextup2);
        text3 = (EditText) findViewById(R.id.editTextup3);
        text4 = (EditText) findViewById(R.id.editTextup4);
        text5 = (EditText) findViewById(R.id.editTextup5);
        text6 = (EditText) findViewById(R.id.editTextup6);

        // Penggunaan SqlLite
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // Query Untuk menampilkan data dan mendapatkan data
        cursor = db.rawQuery("SELECT * FROM data WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        // Mengambil data yang ada di dalam table data
        if (cursor.getCount()>0)
        {
            // Memanggil data dan menampilkan data ke sesuai dengan Edit Text
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
            text6.setText(cursor.getString(5).toString());
        }
        ton1 = (Button) findViewById(R.id.button1);
        ton2 = (Button) findViewById(R.id.button2);
        // daftarkan even onClick pada btnSimpan

        // Efek Button Update ketika sukses mengupdate data
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db =
                        dbHelper.getWritableDatabase();
                db.execSQL("update data set nama='"+ text2.getText().toString() +"', fasilitas='" + text3.getText().toString()+"', alamat='"+ text4.getText().toString() +"', kontak='" + text5.getText().toString() + "', harga='"+ text6.getText().toString() +"' where no='" + text1.getText().toString()+"'");
                Toast.makeText(getApplicationContext(),
                        "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}