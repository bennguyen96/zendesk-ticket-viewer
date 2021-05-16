package com.bennguyen96;

public class Ticket {
    public final int id;
    public final String created_at;
    public final String requester_id;
    public final String status;

    public Ticket(int id, String requester_id, String status, String created_at) {
        this.id = id;
        this.requester_id = requester_id;
        this.status = status;
        this.created_at = created_at;
    }
}
