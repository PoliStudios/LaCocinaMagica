package com.polistudios.saborescompartidos.models;

public class VideoItem {
    public String idUser, videoTitle, videoDescription, idRecipe, shares, likes, videoURL;

    public VideoItem(String idUser, String videoTitle, String videoDescription, String idRecipe, String shares, String likes, String videoURL) {
        this.idUser = idUser;
        this.videoTitle = videoTitle;
        this.videoDescription = videoDescription;
        this.idRecipe = idRecipe;
        this.shares = shares;
        this.likes = likes;
        this.videoURL = videoURL;
    }

    @Override
    public String toString() {
        return "VideoItem{" +
                "idUser='" + idUser + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoDescription='" + videoDescription + '\'' +
                ", idRecipe='" + idRecipe + '\'' +
                ", shares='" + shares + '\'' +
                ", likes='" + likes + '\'' +
                ", videoURL='" + videoURL + '\'' +
                '}';
    }
}
