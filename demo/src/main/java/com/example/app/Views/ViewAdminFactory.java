package com.example.app.Views;

import com.example.app.Controller.Admin.AdminController;
import com.example.app.Controller.Client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewAdminFactory {
    Scene scene = null;
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane manageUserView;

    private AnchorPane manageUserSong;

    public ViewAdminFactory() {
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public AnchorPane getManageUserView() {
        if (manageUserView == null) {
            try {
                manageUserView = new FXMLLoader(getClass().getResource("/Fxml/Admin/ManageUser/ManageUser.fxml")).load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return manageUserView;
    }

    public AnchorPane getManageUserSong() {
        if (manageUserSong == null) {
            try {
                manageUserSong = new FXMLLoader(getClass().getResource("/Fxml/Admin/ManageSong/ManageSong.fxml")).load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return manageUserSong;
    }

    public void showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        createStage(loader);
    }

    public void createStage(FXMLLoader loader) {
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("");
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }

}
