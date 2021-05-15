import java.util.Scanner;

class TicketViewer {

    public static final String MENU = "menu";
    public static final String ALL_TICKETS = "1";
    public static final String ONE_TICKET = "2";
    public static final String QUIT = "quit";

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Printer printer = new Printer();
        TicketAPI api = new TicketAPI();

        printer.printWelcome();

        while (in.hasNext()) {

            printer.printOptions();

            String selection = in.nextLine();
            if (selection.equals(ALL_TICKETS)) {
                try {
                    APIResponse data = api.getAllTickets();
                    printer.printTickets(data.getTickets());
                    // if theres a next page, allow the user to input to retrieve it
                        // if user selects next page, api call and display it
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    continue;
                }
            } else if (selection.equals(ONE_TICKET)) {
                printer.promptForTicket();
                int ticketID = in.nextInt();
                try {
                    Ticket ticket = api.getTicket(ticketID);
                    printer.printTicket(ticket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    continue;
                }
            } else if (selection.equals(QUIT)) {
                System.out.println("Thanks for using the Zendesk Ticket Viewer. Have a nice day!");
                System.exit(0);
            } else {
                printer.printUsage();
            }
        }
    }

}