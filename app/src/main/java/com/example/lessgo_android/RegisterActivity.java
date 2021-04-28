package com.example.lessgo_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etRegisterPseudo;
    private EditText etRegisterPassword;
    private EditText etPasswordConfirm;
    private Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etRegisterPseudo.findViewById("etRegisterPseudo");
        etRegisterPassword.findViewById("etRegisterPassword");
        etPasswordConfirm.findViewById("etPasswordConfirm");
        btRegister.findViewById("btRegister");

        btRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}