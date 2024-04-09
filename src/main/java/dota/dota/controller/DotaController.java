package dota.dota.controller;

import dota.dota.integration.DotaIntegration;
import dota.dota.integration.dto.DotaPlayerDTO;
import dota.dota.integration.dto.PlayerDotaWinLossDTO;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DotaController implements Initializable {
    private final static String START_PLAYER_CODE = "321580662";
    public TextField playerIdField;
    public Label nameLabel;
    public Label winsLabel;
    public Label lossesLabel;
    public Label winRatioLabel;
    public BarChart<String, Number> towerChart;
    public ImageView avatarImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DotaIntegration integration = new DotaIntegration();
        try {
            DotaPlayerDTO playerInfo =  integration.getInfoDotaPlayer(START_PLAYER_CODE);
            nameLabel.setText(playerInfo.getProfile().getName());
            Image image = new Image(playerInfo.getProfile().getAvatarfull());
            avatarImage.setImage(image);
            createWLStatus(START_PLAYER_CODE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createWLStatus(final String playerCode) throws IOException {
        DotaIntegration integration = new DotaIntegration();
        PlayerDotaWinLossDTO playerInfoWl =  integration.getWinsLossPlayer(playerCode);
        winsLabel.setText(playerInfoWl.getWin());
        lossesLabel.setText(playerInfoWl.getLose());
        int wins = Integer.parseInt(playerInfoWl.getWin());
        int loss = Integer.parseInt(playerInfoWl.getLose());
        int totalMatches = wins + loss;
        double winRatio = (double) wins / totalMatches;
        winRatioLabel.setText(String.format("%.2f", winRatio * 100) + "%");

        // Exemplo de preenchimento do gráfico (substitua com sua lógica real):
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data<>("Wins", wins));
        series.getData().add(new XYChart.Data<>("Losses", loss));
        towerChart.getData().clear();
        towerChart.getData().add(series);
    }

    public void searchPlayerByCode(){
        final String playerCode = playerIdField.getText();
        DotaIntegration integration = new DotaIntegration();
        try {
            DotaPlayerDTO playerInfo =  integration.getInfoDotaPlayer(playerCode);
            nameLabel.setText(playerInfo.getProfile().getName());
            Image image = new Image(playerInfo.getProfile().getAvatarfull());
            avatarImage.setImage(image);
            createWLStatus(playerCode);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
