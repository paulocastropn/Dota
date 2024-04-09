package dota.dota.integration;

import com.google.gson.GsonBuilder;
import dota.dota.integration.dto.DotaPlayerDTO;
import dota.dota.integration.dto.PlayerDotaWinLossDTO;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class DotaIntegration {

    private static final String URL_API = "https://api.opendota.com/api/players/${player}";
    private static final String URL_API_WINS_LOSS = "https://api.opendota.com/api/players/${player}/wl";

    public DotaPlayerDTO getInfoDotaPlayer(String playerCode) throws IOException {
        DotaPlayerDTO dotaPlayer = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        final String url = URL_API.replace("${player}", playerCode);
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = httpClient.execute(httpGet);

        try {
            final String responseBody = EntityUtils.toString(response.getEntity());

            dotaPlayer = new GsonBuilder().create().fromJson(responseBody, DotaPlayerDTO.class);
        } finally {
            response.close();
        }

        httpClient.close();

        return dotaPlayer;
    }

    public PlayerDotaWinLossDTO getWinsLossPlayer(String playerCode) throws IOException {
        PlayerDotaWinLossDTO playerWL = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        final String url = URL_API_WINS_LOSS.replace("${player}", playerCode);
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = httpClient.execute(httpGet);

        try {
            final String responseBody = EntityUtils.toString(response.getEntity());

            playerWL = new GsonBuilder().create().fromJson(responseBody, PlayerDotaWinLossDTO.class);
        } finally {
            response.close();
        }

        httpClient.close();

        return playerWL;
    }

}
