package com.example.app.Controller.Client;

import com.example.app.Controller.Data;
import com.example.app.Controller.Song.ListSongPlaying;
import com.example.app.Models.Song;
import com.example.app.Models.SongItemHome;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.EventHandler;

import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javafx.util.Duration;

import java.net.URI;
import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class BottomClientController implements Initializable {

    public static BottomClientController instance;
    public Label nameSong;
    public ProgressBar progress_song;
    public Label time_end;
    public Text time_start;
    public AnchorPane nameSongContainer;
    public ProgressBar volume_song;
    public Label nameUser;
    public FontAwesomeIconView likeSong;
    public FontAwesomeIconView play_btn;

    private boolean playing = true;
    private boolean isRepeat = false;
    private Song song;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        song = Data.getDataGLobal.dataGlobal.getSong();
        playSong();
        volume_song.setProgress(Data.getDataGLobal.dataGlobal.getVolumeValue());
        addListener();
        likeSong.addEventHandler(MouseEvent.MOUSE_CLICKED, this::handleLikePlaylistClicked);
    }

    protected void addListener() {
        play_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(playing) {
                    mediaPlayer.pause();
                    playing = false;
                }else {
                    playing = true;
                    mediaPlayer.play();
                }

            }
        });
        // Xử lý Porgress
        progress_song.setOnMousePressed(event -> {
            //Xử lý khi ấn chuột
            updateProgressBarValue(event);

        });

        progress_song.setOnMouseDragged(event -> {
            // Xử lý khi chuột được kéo (ví dụ: cập nhật giá trị ProgressBar dựa trên vị trí chuột)
            updateProgressBarValue(event);
        });

        //Xử lý volume
        volume_song.setOnMouseDragged(event -> {
            updateVolume(event);
        });

        volume_song.setOnMousePressed(event -> {
            updateVolume(event);
        });
    }

    private void updateVolume(MouseEvent event) {
        double mouseX = event.getX();
        double progressVolumeWidth = volume_song.getWidth();
        double progressVolumeValue = (mouseX / progressVolumeWidth);

        // Ensure the progress value is between 0.0 and 1.0

        progressVolumeValue = Math.min(1.0, Math.max(0.0, progressVolumeValue));
        Data.getDataGLobal.dataGlobal.setVolumeValue(progressVolumeValue);
        volume_song.setProgress(progressVolumeValue);
        mediaPlayer.setVolume(progressVolumeValue);
    }

    private void updateProgressBarValue(MouseEvent event) {
        double mouseX = event.getX();
        double progressBarWidth = progress_song.getWidth();
        double progressBarValue = (mouseX / progressBarWidth);

        // Ensure the progress value is between 0.0 and 1.0
        progressBarValue = Math.min(1.0, Math.max(0.0, progressBarValue));

        // Set the progress to the ProgressBar
        progress_song.setProgress(progressBarValue);

        // Seek the media player to the corresponding time
        Duration totalTime = mediaPlayer.getTotalDuration();
        Duration seekTime = new Duration((long) (progressBarValue * totalTime.toMillis()));
        mediaPlayer.seek(seekTime);
    }

    public Media media;
    public static MediaPlayer mediaPlayer;

    //private ArrayList<Song> listMusic  = new ArrayList<Song>();

    public void playSong() {
        try {
            if (!song.getPathSong().isEmpty()) {
                stopCurrentSong(); // Stop the current song
                playMp3FromUrl(song.getPathSong());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void stopCurrentSong() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            } catch (Exception e) {
                // Handle exceptions if necessary
            }
        }
    }



//    private static SongItemHome findSongById(ArrayList<Song> listMusic, String pathSong) {
//        for (SongItemHome song : ListSongPlaying.SongListGlobal.songList.getAllSong()) {
//            if (song.getPathSong().equals(pathSong)) {
//                return song; // Trả về đối tượng nếu tìm thấy
//            }
//        }
//        return null; // Trả về null nếu không tìm thấy
//    }

    public void playMp3FromUrl(String file) throws Exception {

        //SongItemHome song = findSongById(listMusic, file);
        URI uri = URI.create(file);
        media = new Media(uri.toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(Data.getDataGLobal.dataGlobal.getVolumeValue());
        mediaPlayer.setOnEndOfMedia(() -> {
            System.out.println("Playback finished");
            mediaPlayer.stop();
            nextSong();
        });

        mediaPlayer.play();
        nameSong.setText(song.getNameSong());
        nameUser.setText(song.getNameAuthor());
        updateLabels();
    }

    public void nextSong() {
        time_start.setText("0:00");

        //playMp3FromUr
        int stt = ListSongPlaying.SongListGlobal.songList.getSttPlaySong();

        if(stt == ListSongPlaying.SongListGlobal.songList.getCount() - 1) {
            ListSongPlaying.SongListGlobal.songList.setSttPlaySong(0);
            song = ListSongPlaying.SongListGlobal.songList.getSong(0);
        }else {
            ListSongPlaying.SongListGlobal.songList.setSttPlaySong(stt + 1);
            song = ListSongPlaying.SongListGlobal.songList.getSong(stt + 1);
        }

        playSong();

    }

    private void updateLabels() {
        if (mediaPlayer != null) {
            mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                Duration currentTime = mediaPlayer.getCurrentTime();
                Duration totalTime = mediaPlayer.getTotalDuration();

                String currentTimeStr = formatDuration(currentTime);
                String totalTimeStr = formatDuration(totalTime);

                double progress = currentTime.toMillis() / totalTime.toMillis() ;
                progress_song.setProgress(progress);
                time_start.setText(currentTimeStr);
                time_end.setText(totalTimeStr);

            });
        }
    }

    private String formatDuration(Duration duration) {
        int minutes = (int) duration.toMinutes();
        int seconds = (int) (duration.toSeconds() % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void handleLikePlaylistClicked(MouseEvent event) {
        // Xử lý sự kiện khi biểu tượng trái tim được bấm
        Color newColor = Color.web("#7230e4");
        if (likeSong.getFill().equals(Color.WHITE)) {
            // Nếu trạng thái hiện tại là trắng, đổi màu sang xanh
            likeSong.setFill(newColor);
        } else {
            // Nếu trạng thái hiện tại không phải là trắng, đổi về trắng
            likeSong.setFill(Color.WHITE);
        }

        // Thêm các hành động khác sau khi nhấn vào biểu tượng trái tim (nếu cần)
    }
}
