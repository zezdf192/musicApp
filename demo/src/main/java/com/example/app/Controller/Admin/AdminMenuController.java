package com.example.app.Controller.Admin;

import com.example.app.Models.Model;
import com.example.app.Views.AdminMenuOptions;
import com.example.app.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button manage_user;
    public Button manage_song;
    public Button library_page_btn;
    public Button check_song;
    public Button abum;
    public Button playlist;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manage_user.setOnAction(event -> manageUserView());
        manage_song.setOnAction(event -> manageSongView());
    }

    private void manageUserView() {
        Model.getInstance().getViewAdminFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.MANGAGE_USER);
    }

    private void manageSongView() {
        Model.getInstance().getViewAdminFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.MANAGE_SONG);
    }
}
