package com.example.app.Controller.Client;

import com.example.app.Controller.Data;
import com.example.app.Controller.Song.ListSongPlaying;
import com.example.app.Models.Song;
import com.example.app.Models.SongItemHome;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DetailPlaylistController implements Initializable {
    public VBox vbox;

    public ScrollPane scroll_songitem;
    public VBox vbox_layout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Song> listsongs = ListSongPlaying.SongListGlobal.songList.getListSongs();

        for (int i = 0; i< listsongs.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Fxml/Client/SongView/SongItem.fxml"));
            try {

                AnchorPane hBox = fxmlLoader.load();

                SongItemController cic = fxmlLoader.getController();

                cic.setData(listsongs.get(i));
                vbox_layout.getChildren().add(hBox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
