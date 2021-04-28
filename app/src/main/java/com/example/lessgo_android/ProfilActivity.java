package com.example.lessgo_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lessgo_android.R;

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUpdatePseudo;
    private Button btUpdatePseudo;
    private RadioGroup rgStatuts;
    private RadioButton rdOnline;
    private RadioButton rdBusy;
    private RadioButton rdOffline;
    private CheckBox cbOtherTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        etUpdatePseudo.findViewById("etUpdatePseudo");
        btUpdatePseudo.findViewById("btUpdatePseudo");
        rgStatuts.findViewById("rgStatuts");
        rdOnline.findViewById("rdOnline");
        rdBusy.findViewById("rdBusy");
        rdOffline.findViewById("rdOffline");
        cbOtherTeam.findViewById("cbOtherTeam");

        btUpdatePseudo.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0,1,0, "Maps");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}