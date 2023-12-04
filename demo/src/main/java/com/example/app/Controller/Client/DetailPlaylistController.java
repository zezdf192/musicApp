package com.example.app.Controller.Client;

import com.example.app.Controller.Data;
import com.example.app.Controller.Song.ListSongPlaying;
import com.example.app.Models.Song;
import com.example.app.Models.SongItemHome;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DetailPlaylistController implements Initializable {
    public VBox vbox;

    public ScrollPane scroll_songitem;
    public VBox vbox_layout;
    public FontAwesomeIconView like_Playlist;
    public Label des_likePlaylist;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Song> listsongs = ListSongPlaying.SongListGlobal.songList.getListSongs();

        for (int i = 0; i< listsongs.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/Fxml/Client/SongView/SongItem.fxml"));
            try {

                AnchorPane hBox = fxmlLoader.load();

                SongItemController cic = fxmlLoader.getController();

                cic.setData(listsongs.get(i));
                vbox_layout.getChildren().add(hBox);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        like_Playlist.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleLikePlaylistClicked);
    }

    private void handleLikePlaylistClicked(MouseEvent event) {
        // Xử lý sự kiện khi biểu tượng trái tim được bấm
        Color newColor = Color.web("#7230e4");
        if (like_Playlist.getFill().equals(Color.WHITE)) {
            // Nếu trạng thái hiện tại là trắng, đổi màu sang xanh
            like_Playlist.setFill(newColor);
            des_likePlaylist.setText("Đã thêm vào bộ sưu tập!");
        } else {
            // Nếu trạng thái hiện tại không phải là trắng, đổi về trắng
            like_Playlist.setFill(Color.WHITE);
            des_likePlaylist.setText("Bạn có thích Playlist này?");
        }

        // Thêm các hành động khác sau khi nhấn vào biểu tượng trái tim (nếu cần)
    }

}
