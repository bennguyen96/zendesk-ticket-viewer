package com.bennguyen96;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class TicketAPI {
    public static final int TICKETS_PER_REQUEST = 25;
    private final String ACC = "bennguyen96@gmail.com:throaway";
    private final String ENCODED = Base64.getEncoder().encodeToString(ACC.getBytes());
    private final String URL = String.format("https://bennguyen96.zendesk.com/api/v2/tickets.json?page[size]=%d",
            TICKETS_PER_REQUEST);
    private HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();
    private final JsonParser jsonParser = new JsonParser();


    public APIResponse getAllTickets() throws Throwable {
        HttpRequest request = HttpRequest.newBuilder(URI.create(URL)).
                header("Authorization", "Basic " + ENCODED).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 401) {
            throw new InvalidAuthorizationException("Could not authenticate, please check credentials");
        }
        else if (response.statusCode() != 200) {
            throw new Throwable("Something went wrong, restart and try again");
        }
        // convert response string into json
        JsonObject jsonResponse = jsonParser.parse(response.body()).getAsJsonObject();
        // map to APIResponse object
        return gson.fromJson(jsonResponse, APIResponse.class);
    }

    public Ticket getTicket(int id) throws Throwable {
        HttpRequest authorization = HttpRequest.newBuilder(URI.create(String.format("https://bennguyen96.zendesk.com/api/v2/tickets/%d.json?", id))).
                header("Authorization", "Basic " + ENCODED).GET().build();
        HttpResponse<String> response = client.send(authorization, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 404 || response.statusCode() == 400) {
            throw new InvalidTicketException(String.format("Ticket ID %d does not exist", id));
        } else if (response.statusCode() == 401) {
            throw new InvalidAuthorizationException("Could not authenticate, please check credentials");
        } else if (response.statusCode() != 200) {
            throw new Throwable("Something went wrong, restart and try again");
        }
        // convert response string into json
        JsonObject jsonResponse = jsonParser.parse(response.body()).getAsJsonObject();
        // value of ticket field
        JsonElement ticketJson = jsonResponse.get("ticket");
        return gson.fromJson(ticketJson, Ticket.class);
    }

    public APIResponse getAllTickets(String URL) throws Throwable {
        HttpRequest request = HttpRequest.newBuilder(URI.create(URL)).
                header("Authorization", "Basic " + ENCODED).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new Throwable("Something went wrong, restart and try again");
        }
        // convert response string into json
        JsonObject jsonResponse = jsonParser.parse(response.body()).getAsJsonObject();
        // map to APIResponse object
        return gson.fromJson(jsonResponse, APIResponse.class);
    }
}
