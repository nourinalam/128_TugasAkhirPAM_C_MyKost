package com.example.mykost;

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

public class LoginAdmin extends AppCompatActivity {

    // Deklarasi variable Sesuai dengan Layout LoginAdmin
    EditText TxUsername, TxPassword;
    Button BtnLogin;
    DBAdmin dbAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        // Inisialisai Id
        TxUsername = (EditText) findViewById(R.id.txUsername);
        TxPassword = (EditText) findViewById(R.id.txPassword);
        BtnLogin = (Button) findViewById(R.id.btnLogin);

        dbAdmin = new DBAdmin(this);

        TextView tvCreateAccount = (TextView) findViewById(R.id.tvCreateAccount);

        tvCreateAccount.setText(fromHtml ("Belum Punya Akun. " + "</font><font color='#3b5998'>Register</font>"));

        // Ketika klik textView create Account
        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginAdmin.this, RegisterAdmin.class));
            }
        });

        // Klik button login maka akan pindah ke halaman home
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = TxUsername.getText().toString().trim();
                String password = TxPassword.getText().toString().trim();

                Boolean res = dbAdmin.checkUser(username, password);
                if (res == true) {
                    Toast.makeText(LoginAdmin.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginAdmin.this, MainActivity.class));
                }
                else {
                    Toast.makeText(LoginAdmin.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static Spanned fromHtml(String html){
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