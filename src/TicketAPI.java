import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class TicketAPI {

    private HttpClient client = HttpClient.newHttpClient();
    private final String ACC = "bennguyen96@gmail.com:@CP6DWv8xX";
    private final String ENCODED = Base64.getEncoder().encodeToString(ACC.getBytes());
    private final Gson gson = new Gson();
    public TicketAPI() {

    }

    public void getAllTickets() throws IOException, InterruptedException {
        HttpRequest authorization = HttpRequest.newBuilder(URI.create("https://bennguyen96.zendesk.com/api/v2/tickets.json")).
                header("Authorization", "Basic " + ENCODED).GET().build();


        HttpResponse<String> response = client.send(authorization, HttpResponse.BodyHandlers.ofString());
        String jsonString = response.body();
        System.out.println(jsonString);

        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();
        JsonElement tickets = json.get("tickets");
        Ticket[] ticketArray = gson.fromJson(tickets, Ticket[].class);
        for (Ticket ticket: ticketArray) {
            System.out.println(ticket);
        }
    }

    public void getTicket(int id) throws IOException, InterruptedException {
        HttpRequest authorization = HttpRequest.newBuilder(URI.create(String.format("https://bennguyen96.zendesk.com/api/v2/tickets/%d.json?", id))).
                header("Authorization", "Basic " + ENCODED).GET().build();
        HttpResponse<String> response = client.send(authorization, HttpResponse.BodyHandlers.ofString());
        String jsonString = response.body();
        System.out.println(jsonString);
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();
        JsonElement result = json.get("ticket");
        Ticket ticket = gson.fromJson(result, Ticket.class);
        System.out.println(ticket);
    }

}
