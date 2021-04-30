package com.example.lessgo_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lessgo_android.model.ErrorBean;
import com.example.lessgo_android.model.UserBean;

import java.security.SecureRandom;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPseudo;
    private EditText etPassword;
    private Button btConnexion;
    private Button btLienRegister;
    private TextView tvError;
    private UserBean user;
    private ErrorBean error = new ErrorBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPseudo = findViewById(R.id.etPseudo);
        etPassword = findViewById(R.id.etPassword);
        btConnexion = findViewById(R.id.btConnexion);
        btLienRegister = findViewById(R.id.btLienRegister);
        tvError = findViewById(R.id.tvError);

        showTextError(false);

        btConnexion.setOnClickListener(this);
        btLienRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(v == btConnexion){
            System.out.println("BOUTON LOGIN OK");
            String pseudo = etPseudo.getText().toString();
            String password = etPassword.getText().toString();
            error.message = "";

                if(!pseudo.equals("") && !password.equals("")){
                    Thread t = new Thread() {
                        public void run() {
                            user = new UserBean(pseudo, password);
                            try {
                                WSUtils.loginSubmit(user);
                                System.out.println("CONNEXION OK");
                            } catch (Exception e) {
                                e.printStackTrace();
                                tvError.setText(e.getMessage());
                                showTextError(true);
                            }
                            runOnUiThread(() -> {
                                if(user.getIdSession() != null){
                                    Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                                    intent.putExtra("User", user);
                                    startActivity(intent);
                                }
                            });
                        }
                    };
                    t.start();
                }else{
                    error.message = "Champs vides";
                    System.out.println("Champs manquant");
                }
                showTextError(true);
                tvError.setText(error.message);
        }
        if(v == btLienRegister){
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    public void showTextError(boolean visibility) {
        runOnUiThread(() -> {
            if (visibility) {
                tvError.setVisibility(View.VISIBLE);
            } else {
                tvError.setVisibility(View.GONE);
            }
        });
    }
}