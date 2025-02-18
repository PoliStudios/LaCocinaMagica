package com.polistudios.lacocinamagica.models;

public class VideoItem {
    public String id, videoURL, videoDescription, userId, profileURL;

    public VideoItem(String id, String videoURL, String videoDescription, String userId, String profileURL) {
        this.id = id;
        this.videoURL = videoURL;
        this.videoDescription = videoDescription;
        this.userId = userId;
        this.profileURL = profileURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }
}
