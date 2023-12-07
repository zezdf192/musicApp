package com.example.app.Controller.Client;

import com.example.app.ConnectDB.ConnectDB;
import com.example.app.Controller.Admin.ListSongCheck;
import com.example.app.Controller.Admin.SongCheckItemController;

import com.example.app.Models.Model;
import com.example.app.Models.Song.Song;
import com.example.app.Views.ClientMenuOptions;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class CollectionController implements Initializable {
    public VBox vbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.X);
        ListSongCheck.ListSongCheckGlobal.songList.removeListSong();
        callAPI();
        render();

    }

    public void render() {
        List<Song> listSong = ListSongCheck.ListSongCheckGlobal.songList.getListSongCheck();

        for (int i = 0; i< listSong.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Fxml/Admin/SongCheckItem.fxml"));
            try {
                AnchorPane hBox = fxmlLoader.load();
                SongCheckItemController cic = fxmlLoader.getController();

                cic.setData(listSong.get(i));
                vbox.getChildren().add(hBox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void callAPI() {

        try{
            Connection connection = ConnectDB.getConnection();
            String query = "select * from song join user on song.privacy = 'P3' and song.authorId = user.userId";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int songId = resultSet.getInt("songId");
                String nameSong = resultSet.getString("nameSong");
                String nameAuthor = resultSet.getString("nameUser");
                String dateCreated =  resultSet.getString("dateCreated");
                String totalLike =  resultSet.getString("totalLike");
                String pathSong =  resultSet.getString("pathSong");
                String pathImg =  resultSet.getString("pathImg");
                String kindOfSong =  resultSet.getString("kindOfSong");
                Song song = new Song(songId, nameSong, nameAuthor, dateCreated, totalLike, pathSong, pathImg, kindOfSong);
                ListSongCheck.ListSongCheckGlobal.songList.addSong(song);
            }


        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
