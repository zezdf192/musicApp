package com.example.app.Controller.Admin.ManageAlbum;

import com.example.app.ConnectDB.ConnectDB;
import com.example.app.Controller.Data;
import com.example.app.Models.Model;
import com.example.app.Models.Song.Song;
import com.example.app.Views.ClientMenuOptions;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import static com.example.app.Controller.Client.BottomClientController.mediaPlayer;

public class ItemSongSearchController  implements Initializable {
    public HBox songContainer;
    private int albumId;
    private int songId;
    public Label nameSong;
    public Label author;
    public Button add_btn;
    public ImageView img;
    private Song songSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playMusic();
        add_btn.setOnAction(event -> addSongToAlbum(event));
    }

    public void setData(Song song) {
        // Tải ảnh từ đường dẫn
        Image image = new Image(song.getPathImg());
        img.setImage(image);
        nameSong.setText(song.getNameSong());
        songId = song.getSongId();
        author.setText(song.getNameAuthor());
        albumId = Data.getDataGLobal.dataGlobal.getCurrentEditAlbum().getId();
        songSearch = song;
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

    private void playMusic() {
        songContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                //SongItemHome song = new SongItemHome(nameSong.getText(), nameAuthor.getText(), abum.getText(), dateCreated.getText(), totalTime.getText(), pathSong);
                Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.X);
                Song song = new Song(songId,
                        nameSong.getText(), songSearch.getNameAuthor() ,songSearch.getDateCreated(),
                        String.valueOf(songSearch.getTotalLike()),
                        songSearch.getPathSong(),songSearch.getPathImg(),""
                );

                Data.getDataGLobal.dataGlobal.setCurrentSong(song);
                if(mediaPlayer != null) {
                    mediaPlayer.stop();
                }

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                BorderPane borderPane = (BorderPane) stage.getScene().getRoot();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Fxml/Client/BottomClient.fxml"));

                Parent viewBottomClient;
                try {
                    viewBottomClient = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                borderPane.setBottom(viewBottomClient);
            }
        });
    }
}
