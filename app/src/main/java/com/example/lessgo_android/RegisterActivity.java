package com.example.lessgo_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lessgo_android.model.ErrorBean;
import com.example.lessgo_android.model.UserBean;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etRegisterPseudo;
    private EditText etRegisterPassword;
    private EditText etPasswordConfirm;
    private Button btRegister;
    private TextView tvError;
    private UserBean user;
    private ErrorBean error = new ErrorBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegisterPseudo = findViewById(R.id.etRegisterPseudo);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);
        etPasswordConfirm = findViewById(R.id.etPasswordConfirm);
        tvError = findViewById(R.id.tvError);
        btRegister = findViewById(R.id.btRegister);

        showTextError(false);

        btRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btRegister){
            String pseudo = etRegisterPseudo.getText().toString();
            String password = etRegisterPassword.getText().toString();
            String passwordConfirm = etPasswordConfirm.getText().toString();
            error.message = "";

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
                                showTextError(true);
                                tvError.setText(e.getMessage());
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
                    error.message = "Les mots de passe ne correspondent pas";
                    System.out.println("PASSWORD KO");
                }
            }else{
                error.message = "Champs vides";
                System.out.println("CHAMPS KO");
            }
            showTextError(true);
            tvError.setText(error.message);
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
