import java.util.ArrayList;

class Meta {
    public String has_more;
    }
class Links {
    public String prev;
    public String next;
}
public class APIResponse {
    private ArrayList<Ticket> tickets;
    private Meta meta;
    private Links links;
    private int count;

    public APIResponse(ArrayList<Ticket> tickets, Meta meta, Links links, int count) {
        this.tickets = tickets;
        this.meta = meta;
        this.links = links;
        this.count = count;
    }

    public Meta getMeta() {
        return meta;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
