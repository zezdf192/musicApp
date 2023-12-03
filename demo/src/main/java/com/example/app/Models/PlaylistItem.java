package com.example.app.Models;

public class PlaylistItem {
    //String urlImage;
    String namePlaylist;
    String desPlaylist;

    public PlaylistItem() {
        // Default constructor
    }

    public PlaylistItem( String namePlaylist, String desPlaylist) {
       // this.urlImage = urlImage;
        this.namePlaylist = namePlaylist;
        this.desPlaylist = desPlaylist;
    }



    public String getNamePlaylist() {
        return namePlaylist;
    }

    public String getDesPlaylist() {
        return desPlaylist;
    }
}
