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

public class LoginUser extends AppCompatActivity {

    // Deklarasi Variable yang akan di gunakan sesuai dengan Login User
    EditText TxUsername, TxPassword;
    Button BtnLogin;
    DBUser dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        // Inisialisasi Id sesuai dengan layout yang ada Login User
        TxUsername = (EditText) findViewById(R.id.txUsername);
        TxPassword = (EditText) findViewById(R.id.txPassword);
        BtnLogin = (Button) findViewById(R.id.btnLogin);

        dbUser = new DBUser(this);

        TextView tvCreateAccount = (TextView) findViewById(R.id.tvCreateAccount);

        // Kertika belum punya akun maka akan di pindah ke halaman Register USer
        tvCreateAccount.setText(fromHtml ("Belum Punya Akun. " + "</font><font color='#3b5998'>Register</font>"));

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginUser.this, RegisterUser.class));
            }
        });

        // Ketika klik btn login maka akan pinda ke halam Home User Activity
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = TxUsername.getText().toString().trim();
                String password = TxPassword.getText().toString().trim();

                Boolean res = dbUser.checkUser(username, password);
                if (res == true) {
                    Toast.makeText(LoginUser.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginUser.this, HomeUserActivity.class));
                }
                else {
                    Toast.makeText(LoginUser.this, "Login Gagal", Toast.LENGTH_SHORT).show();
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