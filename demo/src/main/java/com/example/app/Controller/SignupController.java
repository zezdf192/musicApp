package com.example.app.Controller;

import com.example.app.ConnectDB.ConnectDB;
import com.example.app.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    public TextField email_field;
    public PasswordField password_field;
    public TextField userName_field;
    public PasswordField confirmPassword_field;
    public Button submit_signup;
    public Button change_login;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        submit_signup.setOnAction(event -> onSignup());
        change_login.setOnAction(actionEvent -> change_login());
    }

    private void onSignup () {

        try (Connection connection = ConnectDB.getConnection()) {
            String email = email_field.getText();
            System.out.println(email);
            String userName = userName_field.getText();
            System.out.println((userName));
            String password = password_field.getText();
            String confirmPassword = confirmPassword_field.getText();

            String regisDate = "abc";
            String gender = "Male";
            String roleID = "client";


            if (email.isEmpty() || userName.isEmpty() || password.isEmpty()) {
                showAlert("Lỗi", "Không thể đăng ký! \nĐang có trường dữ liệu bị khuyết thiếu thông tin", Alert.AlertType.ERROR);
                return;
            }

            if (!password.equals(confirmPassword)) {
                showAlert("Lỗi", "Mật khẩu xác nhận không khớp!", Alert.AlertType.ERROR);
                return;
            }

            if (emailExists(email, connection)) {
                showAlert("Lỗi", "Email đã được sử dụng!", Alert.AlertType.ERROR);
                return;
            }

            String sql = "INSERT INTO users (userName, email, password, regisDate, gender, roleID) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, userName);
                statement.setString(2, email);
                statement.setString(3, password);
                statement.setString(4, regisDate);
                statement.setString(5, gender);
                statement.setString(6, roleID);

                // Thực hiện truy vấn
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    userName_field.setText("Tạo tài khoản thành công");
                    Stage stage = (Stage) submit_signup.getScene().getWindow();
                    Model.getInstance().getViewFactory().closeStage(stage);

                    Model.getInstance().getViewFactory().showClientWindow();
                } else {
                    showAlert("Lỗi", "Không thể tạo tài khoản", Alert.AlertType.ERROR);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Lỗi", "Lỗi không xác định!", Alert.AlertType.ERROR);
        }
    }

    private  void change_login () {
        Stage stage = (Stage) change_login.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    private boolean emailExists(String email, Connection connection) {
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
