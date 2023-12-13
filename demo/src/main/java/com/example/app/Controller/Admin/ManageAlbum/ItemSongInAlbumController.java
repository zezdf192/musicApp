package com.example.app.Controller.Admin.ManageAlbum;

import com.example.app.ConnectDB.ConnectDB;
import com.example.app.Controller.Data;
import com.example.app.Models.Admin.ItemSong;
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

public class ItemSongInAlbumController implements Initializable {
    private int songId;
    public String category;
    public String name;

    public int totalLike;
    public String privacy;
    public String update_btn;
    public Button remove_btn;
    public HBox hbox;
    public Label nameSong;
    public Label nameAuthor;
    public Label abum;
    public Label dateCreated;

    public Label remove;
    public ImageView img;
    public HBox songContainer;
    private String pathSong;
    private String pathImg;

    private int id;
    private int authorId;
    private int albumId;
    private int playlistId;
    private String desAdmin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        remove.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try (Connection connection = ConnectDB.getConnection()) {
                    String sql = "DELETE FROM album_song_user WHERE albumId = ? and songId = ?";

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
        });
    }

    public void setData(ItemSong song) {
        Image image = new Image(song.getPathImg());
        img.setImage(image);
        songId = song.getSongId();
        nameSong.setText(song.getNameSong());
        category = (song.getKindOfSong());
        dateCreated.setText(song.getDateCreated());
        totalLike =(song.getTotalLike());
        privacy = (song.getPrivacy());
        pathSong = song.getPathSong();
        pathImg = song.getPathImg();
        id = song.getSongId();
        nameAuthor.setText(song.getNameAuthor());
        authorId = song.getAuthorId();
        albumId = Data.getDataGLobal.dataGlobal.getCurrentEditAlbum().getId();
        playlistId = song.getPlaylistId();
        desAdmin = song.getDesAdmin();
        abum.setText(song.getNameSong());
    }
}
