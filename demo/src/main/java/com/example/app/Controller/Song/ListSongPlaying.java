package com.example.app.Controller.Song;

import com.example.app.Controller.Client.BottomClientController;

import com.example.app.Models.Song;
import com.example.app.Models.SongItemHome;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListSongPlaying  {
    private int sttPlaySong;
    private List<Song> songs;

    // Hàm khởi tạo
    public ListSongPlaying() {
        sttPlaySong = 0;
        songs = new ArrayList<>();
    }

    // Thêm bài hát vào danh sách
    public void addSong(Song song) {
        songs.add(0, song);
    }

    public int getSttPlaySong() {
        return sttPlaySong;
    }

    public void setSttPlaySong(int sttPlaySong) {
        this.sttPlaySong = sttPlaySong;
    }

    // Lấy bài hát theo chỉ mục
    public Song getSong(int index) {
        return songs.get(index);
    }

    // Lấy số lượng bài hát trong danh sách
    public int getCount() {
        return songs.size();
    }

    public List<Song> getAllSong() {
        return songs;
    }

    public void removeListSong() {
        songs.clear();
    }

    public void removeSong(String songPath) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getPathSong().equals(songPath)) {
                songs.remove(i);
                return;
            }
        }
    }

    public List<Song> getListSongs() {
        return songs;
    }

    public class SongListGlobal {
        public static ListSongPlaying songList = new ListSongPlaying();
    }
}
