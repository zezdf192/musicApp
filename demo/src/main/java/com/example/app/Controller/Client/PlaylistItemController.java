package com.example.app.Controller.Client;

import com.example.app.Models.Playlist.PlaylistItem;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PlaylistItemController implements Initializable {
    //public Image url_Img;
    public Label namePlaylist;
    public Text des_Playlist;

    public void setData (PlaylistItem playlistItem) {

        namePlaylist.setText(playlistItem.getNamePlaylist());
        des_Playlist.setText(playlistItem.getDesPlaylist());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

