package com.example.app.Controller.Client;

import com.example.app.Controller.Song.HandleSong;
import com.example.app.Controller.Song.ListSongPlaying;
import com.example.app.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    public BorderPane client_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener(((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case SEARCH ->     client_parent.setCenter(Model.getInstance().getViewFactory().getSearchView());
                case COLLECTION ->     client_parent.setCenter(Model.getInstance().getViewFactory().getCollectionView());
                case MY_MUSIC ->     client_parent.setCenter(Model.getInstance().getViewFactory().getMyMusicView());
                case PLAY_LIST ->     client_parent.setCenter(Model.getInstance().getViewFactory().getPlaylistView());
                case BOTTOM_VIEW -> client_parent.setBottom(Model.getInstance().getViewFactory().getBottomView());
                case DIV -> client_parent.setBottom(Model.getInstance().getViewFactory().getDivView());
                default ->     client_parent.setCenter(Model.getInstance().getViewFactory().getHomeView());

            }
        }));
    }
}
