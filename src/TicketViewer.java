import java.util.Scanner;

class TicketViewer {

    public static final String MENU = "menu";
    public static final String ALL_TICKETS = "1";
    public static final String ONE_TICKET = "2";
    public static final String QUIT = "quit";

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Printer printer = new Printer();

        while (in.hasNext()) {
            String selection = in.nextLine();
            if (selection.equals(ALL_TICKETS)) {
                System.out.println(ALL_TICKETS);
            } else if (selection.equals(ONE_TICKET)) {
                System.out.println(ONE_TICKET);
            } else if (selection.equals(QUIT)) {
                System.out.println(QUIT);
            } else {
                System.out.println("Usage:\n" +
                        "Select 1 to view all tickets\n" +
                        "Select 2 to view a ticket\n" +
                        "Type 'quit' to exit");
            }
        }
        System.exit(0);
    }

    public static void help() {
        System.out.println("Welcome to the Zendesk Ticket Viewer\n" +
                "Select 1 to view all tickets\n" +
                "Select 2 to view a ticket\n" +
                "Type 'quit' to exit");
    }
}