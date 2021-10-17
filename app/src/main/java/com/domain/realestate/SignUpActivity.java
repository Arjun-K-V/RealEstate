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

public class SignUpActivity extends AppCompatActivity {

    private EditText etusername,etemail,etpassword,etrepassword;
    Button btnRegister;
    DBHelper myDB;
    SharedPreferences sp;
    TextView tvBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sp = getSharedPreferences("Registered",MODE_PRIVATE);
        if (sp.getBoolean("registered",false)){
            goToMain();
        }

        myDB = new DBHelper(this);

        etusername = findViewById(R.id.et_registerusername);
        etemail = findViewById(R.id.et_registerEmail);
        etpassword = findViewById(R.id.et_registerPassword);
        etrepassword = findViewById(R.id.et_registerRepassword);
        btnRegister = findViewById(R.id.btn_register);

        tvBackToLogin = findViewById(R.id.tv_signup1);

        //CLICK REDIRECT TO LOGIN ACTIVITY
        tvBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etusername.getText().toString();
                String email = etemail.getText().toString();
                String password = etpassword.getText().toString();
                String repassword = etrepassword.getText().toString();

                if(user.equals("")|| email.equals("") || password.equals("")||repassword.equals("")){
                    Toast.makeText(SignUpActivity.this, "Fill all the Fields", Toast.LENGTH_LONG).show();
                }else{
                    if(password.equals(repassword)) {
                        Boolean userCheckResult = myDB.checkusername(user);
                        if (userCheckResult == false) {
                            Boolean regResult = myDB.insertData(user, email, password);
                            if (regResult == true) {
                                Toast.makeText(SignUpActivity.this, "Registration Success", Toast.LENGTH_LONG).show();
                                sp.edit().putBoolean("registered", true).apply();
                                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(SignUpActivity.this, "User already exists Please LOGIN", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SignUpActivity.this, "Passwords Do not Match", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void goToMain() {
        startActivity(new Intent(SignUpActivity.this,DemoLanding.class));
        finish();
    }
}