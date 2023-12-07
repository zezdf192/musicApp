package com.example.app.Controller;

import com.example.app.Models.Song.Song;

public class Data {
    private String nameUser;

    private double volumeValue;

    private Song currentSong;


    public Data() {
        volumeValue = 0.5;
        currentSong = new Song();

    }



    //Remove

    public void setVolumeValue(double volumeValue) {
        this.volumeValue = volumeValue;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }

    public double getVolumeValue() {
        return volumeValue;
    }

    public Song getSong() {
        return currentSong;
    }

    public class getDataGLobal {
       public static Data dataGlobal = new Data();
    }

}
