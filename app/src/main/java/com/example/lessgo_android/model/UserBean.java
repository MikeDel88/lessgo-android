package com.example.lessgo_android.model;

import android.text.Editable;

import java.io.Serializable;

public class UserBean implements Serializable {
    private Long usersId;
    private String idSession;
    private String pseudo;
    private String password;
    private Double lat;
    private Double lon;
    private Boolean otherTeam;
    private StatutBean statuts;

    public UserBean(){
    }

    // Constructeur pour le login
    public UserBean(String pseudo, String password){
        this.pseudo = pseudo;
        this.password = password;
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }


    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Boolean getOtherTeam() {
        return otherTeam;
    }

    public void setOtherTeam(Boolean otherTeam) {
        this.otherTeam = otherTeam;
    }

    public StatutBean getStatuts() {
        return statuts;
    }

    public void setStatuts(StatutBean statuts) {
        this.statuts = statuts;
    }
}
