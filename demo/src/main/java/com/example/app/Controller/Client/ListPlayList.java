package com.example.app.Controller.Client;

import com.example.app.Controller.Song.ListSongPlaying;
import com.example.app.Models.PlaylistItem;
import com.example.app.Models.Song;

import java.util.ArrayList;
import java.util.List;

public class ListPlayList {
    private List<PlaylistItem> listPlaylist_Item;

    public ListPlayList() {

        listPlaylist_Item = new ArrayList<>();
    }

    public void addListPlayList(PlaylistItem song) {
        listPlaylist_Item.add(0, song);
    }

    public List<PlaylistItem> getListPlaylist_Item() {
        return listPlaylist_Item;
    }

    public class ListPlayListGlobal {
        public static ListPlayList songList = new ListPlayList();
    }
}
