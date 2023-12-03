package com.example.app.Controller;

import com.example.app.ConnectDB.ConnectDB;
import com.example.app.Controller.Client.BottomClientController;
import com.example.app.Controller.Song.HandleSong;
import com.example.app.Models.Model;
import javafx.collections.FXCollections;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_btn.setOnAction(event -> onLogin());
        change_signup.setOnAction(actionEvent -> change_signup());
    }

    private void onLogin() {
//        try{
////            Connection connection = ConnectDB.getConnection();
////            String query = "select * from songs";
////            Statement statement = connection.createStatement();
////            ResultSet resultSet = statement.executeQuery(query);
////            while (resultSet.next()) {
////                System.out.println("songID " + resultSet.getString("songID"));
////                System.out.println("PathSOng" + resultSet.getString("pathSong"));
//            }
//
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }

        String email = email_field.getText();
        String password = password_field.getText();
//
//        if (email.isEmpty() || password.isEmpty()) {
//            showAlert("Lỗi", "Vui lòng nhập email và mật khẩu!", Alert.AlertType.ERROR);
//            return;
//        }
        //Connection connection = ConnectDB.getConnection()
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
}