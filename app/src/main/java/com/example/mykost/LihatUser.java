package com.example.mykost;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LihatUser extends AppCompatActivity {

    // Deklarasi Variable sesuai dengan Layout Lihat User
    TextView Alamat1;
    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton2;
    TextView text1, text2, text3, text4, text5,text6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_user);


        Alamat1 = (TextView) findViewById(R.id.textViewliat4);



        //inisialisasi pengguanaan database untuk menampilkan data
        //khususunya menampilkan data nama
        dbHelper = new DataHelper(this);
        text1 = (TextView) findViewById(R.id.textViewliat1);
        text2 = (TextView) findViewById(R.id.textViewliat2);
        text3 = (TextView) findViewById(R.id.textViewliat3);
        text4 = (TextView) findViewById(R.id.textViewliat4);
        text5 = (TextView) findViewById(R.id.textViewliat5);
        text6 = (TextView) findViewById(R.id.textViewliat6);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        //row data nama
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
            text6.setText(cursor.getString(5).toString());
        }
        ton2 = (Button) findViewById(R.id.button1);
        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }

}