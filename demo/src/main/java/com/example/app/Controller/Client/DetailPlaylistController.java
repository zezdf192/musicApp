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
       // songItemHomes();
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

//    private void songItemHomes() {
//
//        Song song1 = new Song( "Waybachome", "Charty Puth", "1", "SongEnglish", "19/12",
//                "4:40", "https://res.cloudinary.com/djfpcyyfe/video/upload/v1694846628/qui3zo66giorp5w3taa9.mp3",
//                "");
//
//        Song song2 = new Song( "Je je je", "Son TUng", "1", "SongEnglish", "19/12",
//                "4:40", "https://res.cloudinary.com/djfpcyyfe/video/upload/v1688831501/wmmyer0h3fyvrxo4snxm.mp3",
//                "");
//
//        Song song3 = new Song( "TUng tung tun", "Raider", "1", "SongEnglish", "19/12",
//                "4:40", "https://res.cloudinary.com/djfpcyyfe/video/upload/v1688830887/vvsyumecp0d9emoqwpqn.mp3",
//                "","","","");
//
//
//        ListSongPlaying.SongListGlobal.songList.addSong(song1);
//        ListSongPlaying.SongListGlobal.songList.addSong(song2);
//        ListSongPlaying.SongListGlobal.songList.addSong(song3);
//    }

}
