package com.example.mykost;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Deklarasi Variable
    String[] daftar;
    ListView ListView01;

    protected Cursor cursor;
    DataHelper dbcenter;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi Variable sesuai dengan Id MainActivity Layout
        Button btn=(Button)findViewById(R.id.button2);

        // Efek klik pindah halaman ketika klik btn akan pindah ke halaman tambah data
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(MainActivity.this,
                        TambahData.class);
                startActivity(inte);
            }
        });
        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();

    }

    void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new OnItemClickListener()
        {
            public void onItemClick(AdapterView arg0, View arg1,
                                    int arg2, long arg3) {
                final String selection = daftar[arg2];
//.getItemAtPosition(arg2).toString();
                final CharSequence[] dialogitem = {"Lihat Data Kosan",
                        "Update Data Kosan", "Hapus Data Kosan"};
                AlertDialog.Builder builder = new
                        AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int
                                    item) {
                                switch(item){
                                    // Pilihan Menampilkan Data di halaman Lihat Admin
                                    case 0 :
                                        Intent i = new
                                                Intent(getApplicationContext(), LihatAdmin.class);
                                        i.putExtra("nama", selection);
                                        startActivity(i);
                                        break;
                                    case 1 :
                                        // Penggunaan EditData ke halaman Update
                                        Intent in = new
                                                Intent(getApplicationContext(), Update.class);
                                        in.putExtra("nama", selection);
                                        startActivity(in);
                                        break;
                                    case 2 :
                                        // Pilihan untuk hapus data
                                        SQLiteDatabase db =
                                                dbcenter.getWritableDatabase();
                                        db.execSQL("delete from data where nama = '"+selection+"'");
                                        RefreshList();
                                        break;
                                }
                            }
                        });
                builder.create().show();
            }});

        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}