package com.example.app.Controller;

import com.example.app.Models.Admin.ItemSong;
import com.example.app.Models.Admin.ItemUser;
import com.example.app.Models.Playlist.CurrentPlaylist;
import com.example.app.Models.Song.Song;
import com.example.app.Models.User.User;

public class Data {
//    private String nameUser;

    private double volumeValue;

    private Song currentSong;

    private CurrentPlaylist currentPlaylist;

     private ItemUser currentEditUser;

     private ItemSong currentEditSong;

    public Data() {
        volumeValue = 0.5;
        currentSong = new Song();
        currentPlaylist = new CurrentPlaylist();
        currentEditUser = new ItemUser();
    }
    //Remove
    public void setVolumeValue(double volumeValue) {
        this.volumeValue = volumeValue;
    }

//    public String getNameUser() {
//        return nameUser;
//    }

    public void setCurrentSong(Song currentSong) {
        this.currentSong = currentSong;
    }

    public void setCurrentPlaylist(CurrentPlaylist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    public double getVolumeValue() {
        return volumeValue;
    }
    public Song getSong() {
        return currentSong;
    }

    public CurrentPlaylist getCurrentPlaylist() {
        return currentPlaylist;
    }

    // User
//    public void setCurrentUser (User currentUser) {this.currentUser = currentUser;}
//    public User getCurrentUser() {return currentUser;}


    public ItemUser getCurrentEditUser() {
        return currentEditUser;
    }

    public void setCurrentEditUser(ItemUser currentEditUser) {
        this.currentEditUser = currentEditUser;
    }

    public ItemSong getCurrentEditSong() {
        return currentEditSong;
    }

    public void setCurrentEditSong(ItemSong currentEditSong) {
        this.currentEditSong = currentEditSong;
    }

    public class getDataGLobal {
        public static Data dataGlobal = new Data();
    }




}