package com.example.app.Controller.Client;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.example.app.Models.Model;
import com.example.app.Views.ClientMenuOptions;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class ClientMenuController implements Initializable {
    public Button home_btn;
    public Button search_btn;
    public Button collection_btn;
    public Button mymusic_btn;
    public Button playlist_btn;
    public Button library_page_btn;
    public Button add_playlist_page_btn;
    public Button logout_btn;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListener();
    }

    private void addListener() {
        home_btn.setOnAction(this::onHome);
        search_btn.setOnAction(this::onSearch);
        collection_btn.setOnAction(this::onCollection);
        mymusic_btn.setOnAction(this::onMyMusic);
        playlist_btn.setOnAction(this::onPlaylist);
    }

    private void onHome(ActionEvent event) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.HOME);
//        loadView(event, "/Fxml/Client/HomeView/Home.fxml");
    }


    private void onSearch(ActionEvent event) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.SEARCH);
//        loadView(event, "/Fxml/Client/Search.fxml");
    }

    private void onCollection(ActionEvent event) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.COLLECTION);
//        loadView(event, "/Fxml/Client/Collection.fxml");
    }

    private void onPlaylist(ActionEvent event) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.PLAY_LIST);
//        loadView(event, "/Fxml/Client/PlayList.fxml");
    }

    private void onMyMusic(ActionEvent event) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.MY_MUSIC);
//        loadView(event, "/Fxml/Client/MyMusic.fxml");
    }

    private void loadView(ActionEvent event, String resourcePath) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BorderPane borderPane = (BorderPane) stage.getScene().getRoot();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
            Parent viewBottomClient = loader.load();
            borderPane.setCenter(viewBottomClient);
        } catch (IOException e) {
            // Handle the exception gracefully, e.g., log it or display an alert
            e.printStackTrace();
        }
    }
}