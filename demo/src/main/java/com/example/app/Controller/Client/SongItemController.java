package com.example.app.Controller.Client;


import com.example.app.Controller.Data;
import com.example.app.Controller.Song.ListSongPlaying;
import com.example.app.Models.Model;
import com.example.app.Models.Song;
import com.example.app.Models.SongItemHome;
import com.example.app.Views.ClientMenuOptions;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SongItemController extends BottomClientController  implements Initializable {

    public Label nameSong;
    public Label nameAuthor;
    public Label abum;
    public Label dateCreated;
    public Label totalTime;
    public HBox songContainer;
    public AnchorPane box;
    public BorderPane client_parent;
    private boolean bool = false;


    private Text time_start;


  // private BottomClientController bottomClientController = BottomClientController.getInstance();
    public String pathSong;


    public void setData(Song song) {

        nameSong.setText(song.getNameSong());
        nameAuthor.setText(song.getNameAuthor());
        abum.setText(song.getKindOfSong());
        dateCreated.setText(song.getDateCreated());
        totalTime.setText(song.getTotalLike());
        pathSong = song.getPathSong();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseMusic();
    }


    public void chooseMusic() {

        songContainer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                //SongItemHome song = new SongItemHome(nameSong.getText(), nameAuthor.getText(), abum.getText(), dateCreated.getText(), totalTime.getText(), pathSong);

                Song song = new Song(
                       nameSong.getText(),nameAuthor.getText(),dateCreated.getText(),
                        totalTime.getText(),
                        pathSong,"",""
                     );
                Data.getDataGLobal.dataGlobal.setCurrentSong(song);

                if(mediaPlayer != null) {
                    mediaPlayer.stop();
                }



                //ListSongPlaying.SongListGlobal.songList.addSong(song);
                //stopCurrentSong();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                BorderPane borderPane = (BorderPane) stage.getScene().getRoot();

                // Remove the existing BottomClient
//                borderPane.setBottom(null);


                // Load a new instance of BottomClient
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Fxml/Client/BottomClient.fxml"));

                Parent viewBottomClient;
                try {
                    viewBottomClient = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                int count = ListSongPlaying.SongListGlobal.songList.getCount();

//                for (int i = 0; i < count ; i++) {
//                    if(ListSongPlaying.SongListGlobal.songList.getSong(i).getPathSong().equals(Data.getDataGLobal.dataGlobal.getSong().getPathSong())) {
//                        ListSongPlaying.SongListGlobal.songList.setSttPlaySong(i);
//                        break;
//                    }
//                }

                // Set the new BottomClient as the bottom of the BorderPane
                borderPane.setBottom(viewBottomClient);
            }
        });

    }

    public Label getNameSong() {
        return nameSong;
    }
}
