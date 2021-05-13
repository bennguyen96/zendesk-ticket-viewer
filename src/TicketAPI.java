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

    public TicketAPI() {

    }

    public void connect() throws IOException, InterruptedException {
        String accPW = "bennguyen96@gmail.com:@CP6DWv8xX";
        String encoded = Base64.getEncoder().encodeToString(accPW.getBytes());
        HttpRequest authorization = HttpRequest.newBuilder(URI.create("https://bennguyen96.zendesk.com/api/v2/tickets.json")).
                header("Authorization", "Basic " + encoded).GET().build();


        HttpResponse<String> response = client.send(authorization, HttpResponse.BodyHandlers.ofString());
        String jsonString = response.body();
        System.out.println(jsonString);
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();
        JsonElement tickets = json.get("tickets");
        Ticket[] ticketArray = gson.fromJson(tickets, Ticket[].class);
        for (Ticket ticket: ticketArray) {
            System.out.println(ticket);
        }

    }

}
