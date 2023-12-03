package com.example.app.Controller.Client;

import com.example.app.ConnectDB.ConnectDB;
import com.example.app.Controller.Data;
import com.example.app.Controller.Song.ListSongPlaying;
import com.example.app.Models.PlaylistItem;
import com.example.app.Models.Song;
import com.example.app.Models.SongItemHome;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {


    public AnchorPane list_box;

    public HBox playlist_item_layout;

    private List<PlaylistItem> listPlaylist_Item = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //addSongByDatabase();
        list_box.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                //Đặt lại Playlist

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                BorderPane borderPane = (BorderPane) stage.getScene().getRoot();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Fxml/Client/DetailPlaylist.fxml"));

                Parent viewBottomClient;
                try {
                    viewBottomClient = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // Set the new BottomClient as the bottom of the BorderPane
                borderPane.setCenter(viewBottomClient);
            }
        });

        playList_Item_Layout();

        for (int i = 0; i < listPlaylist_Item.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Fxml/Client/PlaylistView/PlaylistItem.fxml"));
            try {
                AnchorPane hBox = fxmlLoader.load();
                PlaylistItemController cic = fxmlLoader.getController();
                cic.setData(listPlaylist_Item.get(i));
                playlist_item_layout.getChildren().add(hBox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void playList_Item_Layout() {
        PlaylistItem PlaylistItem1 = new PlaylistItem("Thttps://res.cloudinary.com/djfpcyyfe/image/upload/v1701314435/duowpfnj15mfzcbesxsc.jpg", "Thiên Nhiên", "Những thanh âm giai điệu thiên nhiên...");
        PlaylistItem PlaylistItem2 = new PlaylistItem("Thttps://res.cloudinary.com/djfpcyyfe/image/upload/v1701314435/duowpfnj15mfzcbesxsc.jpg", "Thiên Nhiên", "Những thanh âm giai điệu thiên nhiên...");
        // ... (create other PlaylistItems)

        listPlaylist_Item.add(PlaylistItem1);
        listPlaylist_Item.add(PlaylistItem2);
        // ... (add other PlaylistItems)
    }

    public void addSongByDatabase() {
        ListSongPlaying.SongListGlobal.songList.removeListSong();

        try{
            Connection connection = ConnectDB.getConnection();
            String query = "select * from song join user on song.authorId = user.userId";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                 String nameSong = resultSet.getString("nameSong");
                 String nameAuthor = resultSet.getString("nameUser");
                 String dateCreated =  resultSet.getString("dateCreated");
                 String totalLike =  resultSet.getString("totalLike");
                 String pathSong =  resultSet.getString("pathSong");
                 String pathImg =  resultSet.getString("pathImg");
                 String kindOfSong =  resultSet.getString("kindOfSong");
                 Song song = new Song(nameSong, nameAuthor, dateCreated, totalLike, pathSong, pathImg, kindOfSong);
                 ListSongPlaying.SongListGlobal.songList.addSong(song);
            }


        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
