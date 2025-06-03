package dev.ruka.FIPETableConsult.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ConsumoAPI {
    
    public static String fetch(String url) throws IOException, InterruptedException{
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(url))
            .build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        return response.body();
    }
}
