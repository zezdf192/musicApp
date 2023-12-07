package com.example.app.Views;
import com.example.app.Controller.Client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.util.Duration;
import javafx.animation.PauseTransition;


public class ViewFactory {
    Scene scene = null;
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
    private BorderPane homeView;
    private AnchorPane searchView;
    private AnchorPane collectionView;
    private AnchorPane myMusicView;
    private AnchorPane playlistView;
    private AnchorPane bottomView;
    private AnchorPane divView;

    private final Duration loadingDuration = Duration.seconds(2);

    public ViewFactory() {
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
    }

    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);
    }

    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    public BorderPane getHomeView() {
        if (homeView == null) {
            try {
                homeView = new FXMLLoader(getClass().getResource("/Fxml/Client/HomeView/Home.fxml")).load();
                simulateLoading();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return homeView;
    }

    public AnchorPane getSearchView() {
        if (searchView == null) {
            try {
                searchView = new FXMLLoader(getClass().getResource("/Fxml/Client/Search.fxml")).load();
                simulateLoading();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return searchView;
    }
    public AnchorPane getCollectionView() {
        if (collectionView == null) {
            try {
                collectionView = new FXMLLoader(getClass().getResource("/Fxml/Client/Collection.fxml")).load();
                simulateLoading();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return collectionView;
    }
    public AnchorPane getMyMusicView() {
        if (myMusicView == null) {
            try {
                myMusicView = new FXMLLoader(getClass().getResource("/Fxml/Client/MyMusic.fxml")).load();
                simulateLoading();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return myMusicView;
    }
    public AnchorPane getPlaylistView() {
        if (playlistView == null) {
            try {
                playlistView = new FXMLLoader(getClass().getResource("/Fxml/Client/PlayList.fxml")).load();
                simulateLoading();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return playlistView;
    }
    public AnchorPane getBottomView() {
        if (bottomView == null) {
            try {
                bottomView = new FXMLLoader(getClass().getResource("/Fxml/Client/BottomClient.fxml")).load();
                simulateLoading();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bottomView;
    }
    public AnchorPane getDivView() {
        if (divView == null) {
            try {
                divView = new FXMLLoader(getClass().getResource("/Fxml/Client/DivBottom.fxml")).load();
                simulateLoading();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return divView;
    }

    public void showClientWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
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

    private void simulateLoading() {
        PauseTransition delay = new PauseTransition(loadingDuration);
        delay.setOnFinished(event -> {
            // Các công việc sau khi dữ liệu đã tải xong
            System.out.println("Data loaded!");
        });
        delay.play();
    }

    public Scene loadScene() {
        if (scene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
                scene = new Scene(loader.load());
                simulateLoading();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return scene;
    }

    public void showSignupWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Signup.fxml"));
        createStage(loader);
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}