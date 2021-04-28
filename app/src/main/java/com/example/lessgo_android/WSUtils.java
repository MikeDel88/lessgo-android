package com.example.lessgo_android;

import com.example.lessgo_android.model.UserBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
        okhttpUtils.sendPostOkHttpRequest(URL_REQUETE + "/loginSubmit", gson.toJson(userBean));
    }

    // Post l'inscription d'un utilisateur
    public static void registerSubmit(UserBean userBean) throws Exception {
        System.out.println("/registerSubmit");
        okhttpUtils.sendPostOkHttpRequest(URL_REQUETE + "/registerSubmit", gson.toJson(userBean));
    }

    // Mets à jour les informations de l'utilisateur
    public static void updateUser(UserBean userBean) throws Exception {
        System.out.println("/updateUser");
        okhttpUtils.sendPostOkHttpRequest(URL_REQUETE + "/updateUser", gson.toJson(userBean));

    }
}
