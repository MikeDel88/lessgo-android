package com.example.lessgo_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lessgo_android.model.UserBean;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // Test appel getPositions()
            /*Thread thread = new Thread() {
                @Override
                public void run() {

                    UserBean user = new UserBean();
                    user.setUsersId((long) 1);
                    try {
                        ArrayList<UserBean> list = WSUtils.getPositions();
                        if(list.isEmpty()){
                            System.out.println("AUCUNE DONNEES");
                        }
                        for (UserBean i : list){
                            System.out.println(i.getPseudo());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            };
            thread.start();*/
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
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}