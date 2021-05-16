package com.bennguyen96;

import java.util.ArrayList;

public class APIResponse {

    private ArrayList<Ticket> tickets;
    private Meta meta;
    private Links links;

    public APIResponse(ArrayList<Ticket> tickets, Meta meta, Links links) {
        this.tickets = tickets;
        this.meta = meta;
        this.links = links;
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
}

class Meta {
    public String has_more;
    public String before_cursor;
    public String after_cursor;
}
class Links {
    public String prev;
    public String next;
}