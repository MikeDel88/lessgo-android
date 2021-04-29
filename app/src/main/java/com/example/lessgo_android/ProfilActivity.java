package com.example.lessgo_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lessgo_android.model.ErrorBean;
import com.example.lessgo_android.model.UserBean;

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener {

    private UserBean user;
    private EditText etUpdatePseudo;
    private Button btUpdatePseudo;
    private ErrorBean error = new ErrorBean();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        user = (UserBean) getIntent().getSerializableExtra("User");
        etUpdatePseudo = findViewById(R.id.etUpdatePseudo);
        etUpdatePseudo.setText(user.getPseudo());
        btUpdatePseudo = findViewById(R.id.btUpdatePseudo);

        btUpdatePseudo.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"Maps");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == 1){
            System.out.println("Maps");
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v == btUpdatePseudo){
            String updatePseudo = etUpdatePseudo.getText().toString();
            error.message = "";
            if(!updatePseudo.isEmpty()){
                Thread t = new Thread() {
                    public void run() {
                        user.setPseudo(updatePseudo);
                        try {
                            WSUtils.updateUser(user);
                            System.out.println("UPDATE OK");
                        } catch (Exception e) {
                            e.printStackTrace();
                            // TODO Affiche erreur connexion
                            error.message = e.getMessage();
                        }
                        runOnUiThread(() -> {
                            if(user.getIdSession() != null){
                               etUpdatePseudo.setText(user.getPseudo());
                            }
                        });
                    }
                };
                t.start();
            }else{
                error.message = "Champ vide";
            }
            Toast.makeText(this, error.message, Toast.LENGTH_LONG).show();
        }
    }
}
