package com.example.app.Controller.Client;

import com.example.app.ConnectDB.ConnectDB;
import com.example.app.Controller.Data;
import com.example.app.Controller.LoginController;
import com.example.app.Controller.SignupController;
import com.example.app.Models.Model;
import com.example.app.Models.Playlist.ListPlayList;
import com.example.app.Models.Playlist.PlaylistItem;
import com.example.app.Models.Song.Song;
import com.example.app.Models.User.User;
import com.example.app.Views.ClientMenuOptions;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class PlayListController implements Initializable {
    public Button create_playlist;
    public TextField name_playlist;
    public HBox hbox_playlist;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        User user = LoginController.getUser();
//        if (user == null) {
//            user = SignupController.getUser();
//        }
//        Integer userId = user.getUserId();
        User user = Data.getDataGLobal.dataGlobal.getCurrentUser();
        Integer userId = user.getUserId();

        getDataPlaylist(userId, new ActionEvent());

        create_playlist.setOnAction(actionEvent -> {
            try {

                createPlaylist(userId, actionEvent);
                getDataPlaylist(userId, actionEvent);
            } catch (SQLException e) {
                e.printStackTrace(); // Xử lý ngoại lệ hoặc thêm throw new RuntimeException(e);
            }
        });

    }

    public void createPlaylist(Integer userId, ActionEvent event) throws SQLException {


//        List<PlaylistItem> listPlaylist = ListPlayList.ListPlaylistCurrentUser.songList.getListPlaylist_Item();
//        for (int x = 0; x<listPlaylist.size(); x++) {
//            ListPlayList.ListPlaylistCurrentUser.songList.getListPlaylist_Item().remove(x);
//        }
//        ListPlayList.ListPlaylistCurrentUser.songList.getListPlaylist_Item().clear();

        Connection connection = ConnectDB.getConnection();
        String sql = "INSERT INTO playlist (thumbnailPlaylist, category, namePlaylist, authorId, quantitySong, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "https://res.cloudinary.com/djfpcyyfe/image/upload/v1701951012/mvjnijmcsnvheco0bhzw.png");
            preparedStatement.setString(2, "");
            preparedStatement.setString(3, name_playlist.getText());
            preparedStatement.setInt(4, userId);
            preparedStatement.setInt(5, 0);
            preparedStatement.setString(6, "Playlist của bạn!");

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Playlist created successfully.");
                showAlert("Thành công", "Đã thêm playlist vào danh sách!", Alert.AlertType.INFORMATION);
                name_playlist.setText("");
            } else {
                System.out.println("Failed to create playlist.");
            }
        }


    }

    public void getDataPlaylist (Integer userId, ActionEvent event) {
        ListPlayList.ListPlaylistCurrentUser.songList.getListPlaylist_Item().clear();
        try{
            Connection connection = ConnectDB.getConnection();
            String query = "select * from playlist where authorId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userId);
                ResultSet resultSet = preparedStatement.executeQuery();


                while (resultSet.next()) {
                    int playlistId = resultSet.getInt("playlistId");
                    String thumbnailPlaylist = resultSet.getString("thumbnailPlaylist");
                    String category =  resultSet.getString("category");
                    String namePlaylist =  resultSet.getString("namePlaylist");
                    int authorId =  resultSet.getInt("authorId");
                    int quantitySong =  resultSet.getInt("quantitySong");
                    String description =  resultSet.getString("description");

                    PlaylistItem playlistItem = new PlaylistItem(playlistId, thumbnailPlaylist, category,
                            namePlaylist, authorId, quantitySong, description);
                    ListPlayList.ListPlaylistCurrentUser.songList.addListPlayList(playlistItem);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            List<PlaylistItem> listPlaylist = ListPlayList.ListPlaylistCurrentUser.songList.getListPlaylist_Item();


            for (int i = 0; i < listPlaylist.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Fxml/Client/PlaylistView/PlaylistItem.fxml"));

                try {
                    AnchorPane hBox = fxmlLoader.load();
                    PlaylistItemController cic = fxmlLoader.getController();
                    cic.setData(listPlaylist.get(i));
                    hbox_playlist.getChildren().add(hBox);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.PLAY_LIST);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            BorderPane borderPane = (BorderPane) stage.getScene().getRoot();

            // Load a new instance of BottomClient
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Fxml/Client/PlayList.fxml"));
            Parent viewBottomClient;
            try {
                viewBottomClient = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            borderPane.setCenter(viewBottomClient);


        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}