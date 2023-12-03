package com.example.app.Controller.Client;

import com.cloudinary.Cloudinary;
import com.example.app.ConnectDB.ConnectDB;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class MyMusicController implements Initializable {
    public Button upload_song;
    public Button upload_thumnail;
    public TextField nameSong;
    public ComboBox<String> genreComboBox;
    public Button upload;

    private String urlSong;
    private String urlThumbnail;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        upload_song.setOnAction(actionEvent -> urlSong = uploadSongs(upload_song));
        upload_thumnail.setOnAction(actionEvent -> urlThumbnail = uploadThumbnail(upload_thumnail));

        ObservableList<String> genres = FXCollections.observableArrayList(
                "Pop", "Rock", "Hip Hop", "R&B", "Country", "Jazz", "Classical"
        );

        // Thêm danh sách vào ComboBox
        genreComboBox.setItems(genres);

        upload.setOnAction(actionEvent -> create_Song());

    }

    private String uploadSongs(Button buttonUpdate) {
        return uploadFile("Select a Song File", buttonUpdate);
    }

    private String uploadThumbnail(Button buttonUpdate) {
        return uploadFile("Select a Thumbnail File", buttonUpdate);
    }

    private String uploadFile(String title, Button buttonUpdate) {
        // Configuring Cloudinary
        String CLOUDINARY_URL = "cloudinary://462886354475568:7IyZ1fMXe--ZoDhLd9hr4zlkGOQ@djfpcyyfe";
        Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
        cloudinary.config.secure = true;

        // Creating a FileChooser to select the file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        File file = new File(selectedFile.toString());
        String fileName = file.getName();
        fileName = fileName.length() > 15 ? fileName.substring(0, 15) : fileName;
        fileName = fileName + "...";

        if (selectedFile != null) {
            try {
                // Uploading the file to Cloudinary
                Map<String, Object> uploadParams = new HashMap<>();
                uploadParams.put("resource_type", "auto");

                Map<?, ?> uploadResult = cloudinary.uploader().upload(selectedFile, uploadParams);

                // Accessing the public URL of the uploaded file
                String publicUrl = (String) uploadResult.get("secure_url");

                buttonUpdate.setText(fileName);

                // You can now use 'publicUrl' for further processing or display

                System.out.println("File uploaded successfully. Public URL: " + publicUrl);
                return publicUrl;
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error uploading the file.");
            }
        }
        return null;
    }

    private void create_Song() {
        try (Connection connection = ConnectDB.getConnection()) {
            String songName = nameSong.getText();
            String pathSong = urlSong;
            String thumbnail = urlThumbnail;
            String ownerArtist = "Giang"; // You might want to get this dynamically based on user input or authentication
            String kindOfSong = genreComboBox.getValue();

            if (songName.isEmpty() || pathSong.isEmpty() || thumbnail.isEmpty() || kindOfSong.isEmpty()) {
                showAlert("Lỗi", "Thông tin bài nhạc bị khiếm khuyết!", Alert.AlertType.ERROR);
            } else {
                String sql = "INSERT INTO song (nameSong, authorId, abumId, dateCreated, pathSong, kindOfSong, playListId, totalLike, pathImg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, songName);
                    preparedStatement.setInt(2,10);
                    preparedStatement.setInt(3, 1);
                    preparedStatement.setString(4, "1/1/1111");
                    preparedStatement.setString(5, pathSong);
                    preparedStatement.setString(6, kindOfSong);
                    preparedStatement.setInt(7,1);
                    preparedStatement.setInt(8, 0);
                    preparedStatement.setString(9, thumbnail);



                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        showAlert("Thành công", "Bài hát đã được thêm thành công!", Alert.AlertType.INFORMATION);
                        // You can add further actions or UI updates here after successful insertion
                    } else {
                        showAlert("Lỗi", "Không thể thêm bài hát!", Alert.AlertType.ERROR);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Lỗi", "Lỗi không xác định!", Alert.AlertType.ERROR);
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
