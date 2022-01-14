package com.example.musicplayer.databaseAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Quangcao implements Serializable {

    @SerializedName("Id_ads")
    @Expose
    private String idAds;
    @SerializedName("Image_ads")
    @Expose
    private String imageAds;
    @SerializedName("Content_ads")
    @Expose
    private String contentAds;
    @SerializedName("Id_song")
    @Expose
    private String idSong;
    @SerializedName("Name_song")
    @Expose
    private String nameSong;
    @SerializedName("Image_song")
    @Expose
    private String imageSong;


    public String getIdAds() {
        return idAds;
    }

    public void setIdAds(String idAds) {
        this.idAds = idAds;
    }

    public String getImageAds() {
        return imageAds;
    }

    public void setImageAds(String imageAds) {
        this.imageAds = imageAds;
    }

    public String getContentAds() {
        return contentAds;
    }

    public void setContentAds(String contentAds) {
        this.contentAds = contentAds;
    }

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getImageSong() {
        return imageSong;
    }

    public void setImageSong(String imageSong) {
        this.imageSong = imageSong;
    }
}