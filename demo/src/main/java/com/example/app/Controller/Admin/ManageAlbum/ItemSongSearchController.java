package com.example.app.Controller.Admin.ManageAlbum;

import com.example.app.ConnectDB.ConnectDB;
import com.example.app.Controller.Data;
import com.example.app.Models.Song.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class ItemSongSearchController extends DefaultAlbumController implements Initializable {
    private int albumId;
    private int songId;
    public Label nameSong;
    public Label author;
    public Button add_btn;
    public ImageView img;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_btn.setOnAction(event -> addSongToAlbum(event));
    }

    public void setData(Song song) {
        // Tải ảnh từ đường dẫn
        Image image = new Image(song.getPathImg());
        img.setImage(image);
        nameSong.setText(song.getNameSong());
        songId = song.getSongId();
        albumId = Data.getDataGLobal.dataGlobal.getCurrentEditAlbum().getId();
    }

    private void addSongToAlbum(ActionEvent event) {

        try (Connection connection = ConnectDB.getConnection()) {
                String sql = "INSERT INTO album_song_user (albumId, songId) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, albumId);
                    preparedStatement.setInt(2, songId);
                    int rowsAffected = preparedStatement.executeUpdate();

                }

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                BorderPane borderPane = (BorderPane) stage.getScene().getRoot();

                // Load a new instance of BottomClient
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Fxml/Admin/ManageAlbum/DefaultAlbum.fxml"));
                Parent viewBottomClient;
                try {
                    viewBottomClient = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                borderPane.setCenter(viewBottomClient);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
