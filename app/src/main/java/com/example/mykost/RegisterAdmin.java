package com.example.mykost;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterAdmin extends AppCompatActivity {

    // Deklarasi variable
    EditText TxUsername, TxPassword, TxConPassword;
    Button BtnRegister;
    DBAdmin dbAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);


        dbAdmin = new DBAdmin(this);

        TxUsername = (EditText) findViewById(R.id.txUsernameReg);
        TxPassword = (EditText) findViewById(R.id.txPasswordReg);
        TxConPassword = (EditText) findViewById(R.id.txConPassword);
        BtnRegister = (Button) findViewById(R.id.btnRegister);

        TextView tvRegister = (TextView) findViewById(R.id.tvRegister);

        tvRegister.setText(fromHtml ("Kembali Ke " + "</font><font color='#3b5998'>Register</font>"));

        // pindah ke halamanLogin Admin
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterAdmin.this, LoginAdmin.class));
            }
        });

        // Efek klik button ketika sukses menambahkan data
        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = TxUsername.getText().toString().trim();
                String password = TxPassword.getText().toString().trim();
                String conPassword = TxConPassword.getText().toString().trim();

                ContentValues values = new ContentValues();

                if (!password.equals(conPassword)) {
                    Toast.makeText(RegisterAdmin.this, "Password Tidak Sama", Toast.LENGTH_SHORT).show();
                }
                else if (password.equals("") || username.equals("")) {
                    Toast.makeText(RegisterAdmin.this, "Username atau Password Tidak Boleh Sama", Toast.LENGTH_SHORT).show();

                }
                else {
                    values.put(DBAdmin.row_username, username);
                    values.put(DBAdmin.row_password, password);
                    dbAdmin.insertData(values);

                    Toast.makeText(RegisterAdmin.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public static Spanned fromHtml(String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }
        else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}
