package com.example.musicplayer.databaseAPI;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {

    @SerializedName("IdPlaylist")
    @Expose
    private String idPlaylist;
    @SerializedName("NamePlaylist")
    @Expose
    private String namePlaylist;
    @SerializedName("ImagePlaylist")
    @Expose
    private String imagePlaylist;
    @SerializedName("IconPlaylist")
    @Expose
    private String iconPlaylist;

    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }

    public String getImagePlaylist() {
        return imagePlaylist;
    }

    public void setImagePlaylist(String imagePlaylist) {
        this.imagePlaylist = imagePlaylist;
    }

    public String getIconPlaylist() {
        return iconPlaylist;
    }

    public void setIconPlaylist(String iconPlaylist) {
        this.iconPlaylist = iconPlaylist;
    }

}
