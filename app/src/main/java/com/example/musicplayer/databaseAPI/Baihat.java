package com.example.musicplayer.databaseAPI;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.musicplayer.database.Songs;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Baihat implements Parcelable {

    @SerializedName("IdSong")
    @Expose
    private String idSong;
    @SerializedName("NameSong")
    @Expose
    private String nameSong;
    @SerializedName("Imagesong")
    @Expose
    private String imagesong;
    @SerializedName("Singer")
    @Expose
    private String singer;
    @SerializedName("LinkSong")
    @Expose
    private String linkSong;
    @SerializedName("Like")
    @Expose
    private String like;

    protected Baihat(Parcel in) {
        idSong = in.readString();
        nameSong = in.readString();
        imagesong = in.readString();
        singer = in.readString();
        linkSong = in.readString();
        like = in.readString();
    }

    public Songs getSong() {
        return new Songs(0,linkSong, nameSong, "", singer,0,0,0,0,0);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idSong);
        dest.writeString(nameSong);
        dest.writeString(imagesong);
        dest.writeString(singer);
        dest.writeString(linkSong);
        dest.writeString(like);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Baihat> CREATOR = new Creator<Baihat>() {
        @Override
        public Baihat createFromParcel(Parcel in) {
            return new Baihat(in);
        }

        @Override
        public Baihat[] newArray(int size) {
            return new Baihat[size];
        }
    };

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

    public String getImagesong() {
        return imagesong;
    }

    public void setImagesong(String imagesong) {
        this.imagesong = imagesong;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getLinkSong() {
        return linkSong;
    }

    public void setLinkSong(String linkSong) {
        this.linkSong = linkSong;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

}