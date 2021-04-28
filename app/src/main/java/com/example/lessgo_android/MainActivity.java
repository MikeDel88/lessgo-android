package com.example.lessgo_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lessgo_android.model.UserBean;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // Test appel getPositions()
            Thread thread = new Thread() {
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
            thread.start();
    }
}