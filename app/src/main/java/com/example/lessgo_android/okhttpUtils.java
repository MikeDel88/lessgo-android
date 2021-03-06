package com.example.lessgo_android;

import com.example.lessgo_android.model.ErrorBean;

import okhttp3.*;

public class okhttpUtils {

    public static String sendGetOkHttpRequest(String url) throws Exception {
        System.out.println("Url : " + url);
        OkHttpClient client = new OkHttpClient();
        //Création de la requete
        Request request = new Request.Builder().url(url).build(); //Execution de la requête
        Response response = client.newCall(request).execute(); //Analyse du code retour
        if (response.code() < 200 || response.code() > 299) {
            String res = response.body().string();
            if(res.contains("\"message\"")){
                ErrorBean error = WSUtils.gson.fromJson(res, ErrorBean.class);
                throw new Exception(error.message);
            }
            throw new Exception("Réponse du serveur incorrect : " + response.code());
        } else {
            //Résultat de la requete.
            return response.body().string();
        }
    }

    public static String sendPostOkHttpRequest(String url, String paramJson) throws Exception {
        System.out.println("Url : " + url); OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        //Corps de la requête
        RequestBody body = RequestBody.create(JSON, paramJson);
        //Création de la requete
        Request request = new Request.Builder().url(url).post(body).build(); //Execution de la requête
        Response response = client.newCall(request).execute(); //Analyse du code retour
        if (response.code() < 200 || response.code() > 299) {
            String res = response.body().string();
            if(res.contains("\"message\"")){
                ErrorBean error = WSUtils.gson.fromJson(res, ErrorBean.class);
                throw new Exception(error.message);
            }
            throw new Exception("Réponse du serveur incorrect : " + response.code());
        }
        else {
            //Résultat de la requete.
            return response.body().string();
        }
    }
}
