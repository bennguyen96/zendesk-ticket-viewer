import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;

public class TicketAPI {
    public static final int TICKETS_PER_REQUEST = 25;
    private final String ACC = "bennguyen96@gmail.com:@CP6DWv8xX";
    private final String ENCODED = Base64.getEncoder().encodeToString(ACC.getBytes());
    private final String URL = String.format("https://bennguyen96.zendesk.com/api/v2/tickets.json?page[size]=%d",
            TICKETS_PER_REQUEST);
    private HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();
    private final JsonParser jsonParser = new JsonParser();


    public APIResponse getAllTickets() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(URL)).
                header("Authorization", "Basic " + ENCODED).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // convert response string into json
        JsonObject jsonResponse = jsonParser.parse(response.body()).getAsJsonObject();
        // map to APIResponse object
        APIResponse data = gson.fromJson(jsonResponse, APIResponse.class);
        return data;
    }

    public Ticket getTicket(int id) throws IOException, InterruptedException {
        HttpRequest authorization = HttpRequest.newBuilder(URI.create(String.format("https://bennguyen96.zendesk.com/api/v2/tickets/%d.json?", id))).
                header("Authorization", "Basic " + ENCODED).GET().build();
        HttpResponse<String> response = client.send(authorization, HttpResponse.BodyHandlers.ofString());
        // convert response string into json
        JsonObject jsonResponse = jsonParser.parse(response.body()).getAsJsonObject();
        // value of ticket field
        JsonElement ticketJson = jsonResponse.get("ticket");
        Ticket ticket = gson.fromJson(ticketJson, Ticket.class);
        return ticket;
    }

    public APIResponse getAllTickets(String URL) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(URI.create(URL)).
                header("Authorization", "Basic " + ENCODED).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // convert response string into json
        JsonObject jsonResponse = jsonParser.parse(response.body()).getAsJsonObject();
        // map to APIResponse object
        APIResponse data = gson.fromJson(jsonResponse, APIResponse.class);
        return data;
    }
}
