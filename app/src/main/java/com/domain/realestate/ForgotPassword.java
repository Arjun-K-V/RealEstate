package com.domain.realestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    EditText etForgotEmail;
    Button btnForgotSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etForgotEmail = findViewById(R.id.et_forgot_emailid);
        btnForgotSend = findViewById(R.id.btn_forgot_send);

        btnForgotSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String forgot_email = etForgotEmail.getText().toString();
                Toast.makeText(ForgotPassword.this, "Instructions sent to:\n "+forgot_email , Toast.LENGTH_LONG).show();
            }
        });


    }
}