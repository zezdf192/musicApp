package com.example.app.Controller;

import com.example.app.ConnectDB.ConnectDB;
import com.example.app.Models.Playlist.ListPlayList;
import com.example.app.Models.Song.ListSongPlaying;
import com.example.app.Models.Model;
import com.example.app.Models.Playlist.PlaylistItem;
import com.example.app.Models.Song.Song;
import com.example.app.Models.User.User;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    public Button change_signup;
    public Button login_btn;
    public PasswordField password_field;
    public TextField email_field;

    private static User user; // Đặt làm biến toàn cục

    public static User getUser() {
        return user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_btn.setOnAction(actionEvent -> {
            try {
                onLogin();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        change_signup.setOnAction(actionEvent -> change_signup());

//        addSongByDatabase();

    }

    private void onLogin() throws SQLException {

        String email = email_field.getText();
        String password = password_field.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Lỗi", "Vui lòng nhập email và mật khẩu!", Alert.AlertType.ERROR);
            return;
        }
        Connection connection = ConnectDB.getConnection();

        try (connection) {
            String sql = "SELECT * FROM user where email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String userName = resultSet.getString("nameUser");
                        Integer userId = resultSet.getInt("userId");
                        String userEmail = resultSet.getString("email");
                        String userGender = resultSet.getString("gender");

//                        User user = new User(userName, userId, userEmail,userGender);
                        user = new User();
                        user.setUserName(userName);
                        user.setUserEmail(userEmail);
                        user.setUserGender(userGender);
                        user.setUserId(userId);

//                        Data.getDataGLobal.dataGlobal.setCurrentUser(user);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try  {

            //validateLogin(email, password, connection
            if (true) {
                // Successful login
                showAlert("Thông báo", "Đăng nhập thành công!", Alert.AlertType.INFORMATION);
                Stage stage = (Stage) login_btn.getScene().getWindow();
                Model.getInstance().getViewFactory().closeStage(stage);

                Model.getInstance().getViewFactory().showClientWindow();
            } else {
                showAlert("Lỗi", "Email hoặc mật khẩu không đúng!", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Lỗi", "Lỗi không xác định!", Alert.AlertType.ERROR);
        }


    }

    private  void change_signup () {
        Stage stage = (Stage) change_signup.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showSignupWindow();
    }

    private boolean validateLogin(String email, String password, Connection connection) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // If there is at least one row, login is successful
        }
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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