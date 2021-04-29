package com.example.lessgo_android;

import android.content.Intent;

import com.example.lessgo_android.model.UserBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static androidx.core.content.ContextCompat.startActivity;

public class WSUtils {

    private static final String URL_REQUETE = "http://86.250.106.223:8080";
    public static final Gson gson = new Gson();


    // Récupère les positions des utilisateurs et renvoi la liste
    public static ArrayList<UserBean> getPositions() throws Exception {
        System.out.println("/getPositions");

        // Envoi de la requête
        String response = okhttpUtils.sendGetOkHttpRequest(URL_REQUETE + "/getPositions");
        return gson.fromJson(response, new TypeToken<ArrayList<UserBean>>(){}.getType());
    }

    // Post la connexion d'un utilisateur
    public static void loginSubmit(UserBean userBean) throws Exception {
        System.out.println("/loginSubmit");
        String response = okhttpUtils.sendPostOkHttpRequest(URL_REQUETE + "/loginSubmit", gson.toJson(userBean));

        UserBean userBeanRecu = gson.fromJson(response, UserBean.class);
        userBean.setIdSession(userBeanRecu.getIdSession());
    }

    // Post l'inscription d'un utilisateur
    public static void registerSubmit(UserBean userBean) throws Exception {
        System.out.println("/registerSubmit");
        String response = okhttpUtils.sendPostOkHttpRequest(URL_REQUETE + "/registerSubmit", gson.toJson(userBean));

        UserBean userBeanRecu = gson.fromJson(response, UserBean.class);
        userBean.setIdSession(userBeanRecu.getIdSession());
    }

    // Mets à jour les informations de l'utilisateur
    public static void updateUser(UserBean userBean) throws Exception {
        System.out.println("/updateUser");
        okhttpUtils.sendPostOkHttpRequest(URL_REQUETE + "/updateUser", gson.toJson(userBean));
    }
}
