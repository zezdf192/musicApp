package com.example.app.Controller.Client.Home;

import com.example.app.Models.Model;
import com.example.app.Views.ClientMenuOptions;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TopHomeController implements Initializable {
    public AnchorPane list_box;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        list_box.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                //Đặt lại Playlist

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                BorderPane borderPane = (BorderPane) stage.getScene().getRoot();

                FXMLLoader loader = new FXMLLoader();


                loader.setLocation(getClass().getResource("/Fxml/Client/DetailPlaylist.fxml"));

                Parent viewBottomClient;
                try {
                    viewBottomClient = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // Set the new BottomClient as the bottom of the o
                Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DETAIL);
                borderPane.setCenter(viewBottomClient);

            }
        });
    }
}
