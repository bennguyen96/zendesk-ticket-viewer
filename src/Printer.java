public class Printer {

    public void printWelcome(){
        System.out.println("Welcome to the Zendesk Ticket Viewer\n");
    }
    public void printTickets(Ticket[] tickets){
        for (Ticket ticket : tickets) {
            printTicket(ticket);
        }
    }

    public void printTicket(Ticket ticket) {
        System.out.format("#%d created %s by %s. Status: %s \n",
                ticket.id, ticket.created_at, ticket.requester_id, ticket.status);
    }

    public void promptForTicket() {
        System.out.print("Enter Ticket ID: ");
    }

    public void printUsage() {
        System.out.println("Usage:\n");
        printOptions();
    }

    public void printOptions() {
        System.out.println("Select [1] to view all tickets\n" +
                "Select [2] to view a ticket\n" +
                "Type [quit] to exit");
    }
}
