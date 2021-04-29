package com.example.lessgo_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lessgo_android.model.UserBean;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etRegisterPseudo;
    private EditText etRegisterPassword;
    private EditText etPasswordConfirm;
    private Button btRegister;
    private UserBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegisterPseudo = findViewById(R.id.etRegisterPseudo);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        btRegister = findViewById(R.id.btRegidter);

        btRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btRegister){
            String pseudo = etRegisterPseudo.getText().toString();
            String password = etRegisterPassword.getText().toString();
            String passwordConfirm = etPasswordConfirm.getText().toString();

            if(!pseudo.isEmpty() && !password.isEmpty() && !passwordConfirm.isEmpty()){
                System.out.println("CHAMPS OK");
                if(password.equals(passwordConfirm)){
                    System.out.println("PASSWORD OK");
                    Thread t = new Thread() {
                        public void run() {
                            user = new UserBean(pseudo, password);
                            try {
                                WSUtils.registerSubmit(user);
                                System.out.println("INSCRIPTION OK");
                            } catch (Exception e) {
                                e.printStackTrace();
                                // TODO Affiche erreur retour base de données
                            }
                            runOnUiThread(() -> {
                                if(user.getIdSession() != null){
                                    Intent intent = new Intent(RegisterActivity.this, ProfilActivity.class);
                                    intent.putExtra("User", user);
                                    startActivity(intent);
                                }
                            });
                        }
                    };
                    t.start();
                }else{
                    // TODO Affiche Erreur les password ne correspondent pas
                    Toast.makeText(this, "Ceci est un Toast", Toast.LENGTH_LONG).show();
                    System.out.println("PASSWORD KO");
                }
            }else{
                // TODO Affiche Erreur champs vide
                Toast.makeText(this, "Ceci est un Toast", Toast.LENGTH_LONG).show();
                System.out.println("CHAMPS KO");
            }
        }
    }
}
