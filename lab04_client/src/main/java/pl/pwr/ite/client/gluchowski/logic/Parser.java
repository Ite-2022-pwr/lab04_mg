package pl.pwr.ite.client.gluchowski.logic;

import java.net.http.HttpClient;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Parser {
    private String base_url = "https://api.gios.gov.pl/pjp-api/rest";
    private HttpClient client = HttpClient.newHttpClient();
    private ObjectMapper objectMapper;

    public Parser() {
        this.objectMapper = new ObjectMapper();
    }

    public <R> R get (String path, Class<R> responseType) {
        var request = buildRequest(path);
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(response.body(), responseType);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }

    private HttpRequest buildRequest(String path) {
        return HttpRequest.newBuilder().uri(URI.create(base_url.concat(path))).GET().build();
    }
}
