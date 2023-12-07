package com.example.app.Controller.Client;

import com.example.app.ConnectDB.ConnectDB;
import com.example.app.Controller.LoginController;
import com.example.app.Controller.SignupController;
import com.example.app.Models.User.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PlayListController implements Initializable {
    public Button create_playlist;
    public TextField name_playlist;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = LoginController.getUser();
        if (user == null) {
            user = SignupController.getUser();
        }
        Integer userId = user.getUserId();
        create_playlist.setOnAction(actionEvent -> {
            try {
                createPlaylist(userId);
            } catch (SQLException e) {
                e.printStackTrace(); // Xử lý ngoại lệ hoặc thêm throw new RuntimeException(e);
            }
        });
    }

    public void createPlaylist(Integer userId) throws SQLException {
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

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}