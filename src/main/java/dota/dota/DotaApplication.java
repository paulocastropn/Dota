package dota.dota;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class DotaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        music();
        FXMLLoader fxmlLoader = new FXMLLoader(DotaApplication.class.getResource("dota-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 380);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/dota/dota/image/Dotaicon.png")));
        stage.getIcons().add(image);
        stage.setTitle("Dota");
        stage.setScene(scene);
        stage.show();
    }

    MediaPlayer mediaPlayer;

    public void music() {
        String s = "dj-maggot_basshunter-dota-mp3.mp3";
        Media h = new Media(Paths.get(s).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();
    }

    public static void main(String[] args) {
        launch();
    }
}
