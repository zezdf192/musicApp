package com.example.app.Models;

public class Song {
    String nameSong;
    String nameAuthor;
    int authorId;

    String dateCreated;
    String totalLike;
    String pathSong;
    String pathImg;

    String kindOfSong;


    public Song () {

    }

    public Song(String nameSong, String nameAuthor, String dateCreated, String totalLike, String pathSong, String pathImg, String kindOfSong) {
        this.nameSong = nameSong;
        this.nameAuthor = nameAuthor;

        this.dateCreated = dateCreated;
        this.totalLike = totalLike;
        this.pathSong = pathSong;
        this.pathImg = pathImg;
        this.kindOfSong = kindOfSong;

    }

    public String getNameSong() {
        return nameSong;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getTotalLike() {
        return totalLike;
    }

    public String getPathSong() {
        return pathSong;
    }

    public String getPathImg() {
        return pathImg;
    }



    public String getKindOfSong() {
        return kindOfSong;
    }


}
