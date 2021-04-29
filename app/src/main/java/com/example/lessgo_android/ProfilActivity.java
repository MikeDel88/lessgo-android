package com.example.lessgo_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lessgo_android.model.UserBean;

public class ProfilActivity extends AppCompatActivity {

    private UserBean user;
    private TextView etUpdatePseudo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        user = (UserBean) getIntent().getSerializableExtra("User");
        etUpdatePseudo = findViewById(R.id.etUpdatePseudo);
        etUpdatePseudo.setText(user.getPseudo());

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
}
