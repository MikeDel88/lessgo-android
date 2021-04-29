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

import com.example.lessgo_android.model.UserBean;

import java.security.SecureRandom;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPseudo;
    private EditText etPassword;
    private Button btConnexion;
    private Button btLienRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPseudo = findViewById(R.id.etPseudo);
        etPassword = findViewById(R.id.etPassword);
        btConnexion = findViewById(R.id.btConnexion);
        btLienRegister = findViewById(R.id.btLienRegister);

        btConnexion.setOnClickListener(this);
        btLienRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(v == btConnexion){
            System.out.println("BOUTON LOGIN OK");
            String pseudo = etPseudo.getText().toString();
            String password = etPassword.getText().toString();
            try {
                if(!pseudo.equals("") && !password.equals("")){
                    Thread t = new Thread() {
                        public void run() {
                            UserBean user = new UserBean(pseudo, password);
                            try {
                                WSUtils.loginSubmit(user);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("CONNEXION OK");

                            // Changer de page
                            // 1er passage user en paramètre
                            Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
                            intent.putExtra("User", user);
                            startActivity(intent);
                        }
                    };
                    t.start();
                }else{
                    Toast.makeText(this," champs manquants", Toast.LENGTH_LONG).show();
                    throw new Exception("Champs manquants");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(v == btLienRegister){
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}