public class Ticket {
    private final int id;
    private final String created_at;
    private final String requester_id;
    private final String status;

    public Ticket(int id, String requester_id, String status, String created_at) {
        this.id = id;
        this.requester_id = requester_id;
        this.status = status;
        this.created_at = created_at;
    }
    @Override
    public String toString() {
        return "Ticket " + id + " "+ created_at + " by " + requester_id + ". Status: " + status;
    }
}
