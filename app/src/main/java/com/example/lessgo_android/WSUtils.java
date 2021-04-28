package com.example.lessgo_android;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WSUtils<listUsers> {

    private static String URL_REQUETE = "http://86.250.106.223:8080/";
    private static Gson gson = new Gson();
    private static ArrayList<UserBean> listUsers = new ArrayList<UserBean>();

    // Récupère les positions des utilisateurs et renvoi la liste
    public ArrayList<UserBean> getPositions() throws Exception {
        System.out.println("/getPositions");

        // Envoi de la requête
        String response = okhttpUtils.sendGetOkHttpRequest(URL_REQUETE + "/getPositions");
        return listUsers.add(gson.fromJson(response, UserBean.class));
    }

    // Post la connexion d'un utilisateur
    public void loginSubmit(UserBean userBean){
        System.out.println("/loginSubmit");
        okhttpUtils.sendPostOkHttpRequest(URL_REQUETE + "/loginSubmit", gson.toJson(userBean));
    }

    // Post l'inscription d'un utilisateur
    public void registerSubmit(UserBean userBean){
        System.out.println("/registerSubmit");
        okhttpUtils.sendPostOkHttpRequest(URL_REQUETE + "/registerSubmit", gson.toJson(userBean));
    }

    // Mets à jour les informations de l'utilisateur
    public void updateUser(UserBean userBean){
        System.out.println("/updateUser");
        okhttpUtils.sendPostOkHttpRequest(URL_REQUETE + "/updateUser", gson.toJson(userBean));

    }
}
