package pl.pwr.ite.client.gluchowski.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Communication {
    private String urlFindAll = "";
    private String urlGetAllStanowiska = "https://api.gios.gov.pl/pjp-api/rest/station/sensors/";
    private Integer stacjId;
    public Communication() {
    }

    public String getResponse(String paramUrl) {
        try {

            URL url = new URL(paramUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() != 200) {
                throw new Exception("Failed : HTTP error code : " + connection.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));

            String output;
            StringBuilder sb = new StringBuilder();
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            connection.disconnect();

            return sb.toString();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public void setIdStanowiska(Integer id) {
        this.stacjId = id;
    }

}
