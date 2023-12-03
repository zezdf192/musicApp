package com.example.app.Controller.Client;

import com.example.app.Models.PlaylistItem;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

public class PlaylistItemController {
    public Image url_Img;
    public Label namePlaylist;
    public Text des_Playlist;

    public void setData (PlaylistItem playlistItem) {

        namePlaylist.setText(playlistItem.getNamePlaylist());
        des_Playlist.setText(playlistItem.getDesPlaylist());
    }
}

