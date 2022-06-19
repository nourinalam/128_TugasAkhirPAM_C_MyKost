package com.example.mykost;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Pilihan extends AppCompatActivity {
    private Button Admin;
    private Button User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan);

        Admin = (Button) findViewById(R.id.buttonA);
        User = (Button) findViewById(R.id.buttonU);

        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                admin();
            }
        });

        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user();
            }
        });
    }

    private void user() {
        Intent intent = new Intent(this, LoginUser.class);
        startActivity(intent);
    }

    private void admin() {
        Intent intent = new Intent(this, LoginAdmin.class);
        startActivity(intent);
    }
}