package com.example.app.Controller.Client;

import com.example.app.Models.Model;
import com.example.app.Views.ClientMenuOptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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

    private void addListener () {
        home_btn.setOnAction(event -> onHome(event));
        search_btn.setOnAction(event -> onSearch(event));
        collection_btn.setOnAction(event -> onCollection(event));
        mymusic_btn.setOnAction(event -> onMyMusic(event));
        playlist_btn.setOnAction(event -> onPlaylist(event));

    }

    private void onHome (ActionEvent event) {
        //Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.SEARCH);

//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        BorderPane borderPane = (BorderPane) stage.getScene().getRoot();
//
//        // Load a new instance of BottomClient
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/Fxml/Client/HomeView/Home.fxml"));
//        Parent viewBottomClient;
//        try {
//            viewBottomClient = loader.load();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        borderPane.setCenter(viewBottomClient);

        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.HOME);
    }

    private void onSearch(ActionEvent event) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.SEARCH);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BorderPane borderPane = (BorderPane) stage.getScene().getRoot();

        // Load a new instance of BottomClient
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Fxml/Client/Search.fxml"));
        Parent viewBottomClient;
        try {
            viewBottomClient = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        borderPane.setCenter(viewBottomClient);
//        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.SEARCH);
    }

    private void onCollection(ActionEvent event) {
        //Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.COLLECTION);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BorderPane borderPane = (BorderPane) stage.getScene().getRoot();

        // Load a new instance of BottomClient
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Fxml/Client/Collection.fxml"));
        Parent viewBottomClient;
        try {
            viewBottomClient = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        borderPane.setCenter(viewBottomClient);
    }

    private void onPlaylist(ActionEvent event) {
        //Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.PLAY_LIST);
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
    }

    private void onMyMusic(ActionEvent event) {
        //Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.MY_MUSIC);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        BorderPane borderPane = (BorderPane) stage.getScene().getRoot();

        // Load a new instance of BottomClient
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Fxml/Client/MyMusic.fxml"));
        Parent viewBottomClient;
        try {
            viewBottomClient = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        borderPane.setCenter(viewBottomClient);
    }
}
