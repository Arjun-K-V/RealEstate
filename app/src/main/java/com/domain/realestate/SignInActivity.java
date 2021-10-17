package com.domain.realestate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
    TextView tvRegister;
    TextView tvForgotPassword;
    EditText usernameLogin;
    EditText passwordLogin;
    Button btnLogin;
    SharedPreferences sp;

    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        usernameLogin = findViewById(R.id.et_username);
        passwordLogin = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        sp = getSharedPreferences("login",MODE_PRIVATE);
        if(sp.getBoolean("logged",false)){
            goToMain();
        }

        myDB = new DBHelper(this);

        tvRegister = findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this,ForgotPassword.class));
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameLogin.getText().toString();
                String password = passwordLogin.getText().toString();
                if(username.equals("")||password.equals("")){
                    Toast.makeText(SignInActivity.this, "Please enter credentials", Toast.LENGTH_LONG).show();
                }else{
                    Boolean resultLogin = myDB.checkUsernamePassword(username,password);
                    if (resultLogin==true){
                        goToMain();
                        sp.edit().putBoolean("logged",true).apply();
                        finish();
                    }else{
                        Toast.makeText(SignInActivity.this, "Invalid user", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void goToMain() {
        startActivity(new Intent(SignInActivity.this,DemoLanding.class));
        finish();
    }
}