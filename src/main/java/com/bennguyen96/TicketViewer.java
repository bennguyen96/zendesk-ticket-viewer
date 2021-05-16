package com.bennguyen96;

import java.util.Scanner;

public class TicketViewer {

    public static final String ALL_TICKETS = "1";
    public static final String ONE_TICKET = "2";
    public static final String QUIT = "quit";
    public static final String NEXT = "next";
    public static final String PREV = "prev";

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Printer printer = new Printer();
        TicketAPI api = new TicketAPI();

        printer.printWelcome();

        while (in.hasNext()) {
            String selection = in.nextLine();
            if (selection.equals(ALL_TICKETS)) {
                try {
                    // call api and get first page of tickets
                    APIResponse data = api.getAllTickets();
                    printer.printPage(data);
                    while (data.getMeta().has_more.equals("true")) {
                        printer.printPaginationUsage();
                        selection = in.nextLine();
                        if (selection.equals(NEXT)) {
                            data = api.getAllTickets(data.getLinks().next);
                            printer.printPage(data);
                        } else if (selection.equals(PREV)) {
                            data = api.getAllTickets(data.getLinks().prev);
                            printer.printPage(data);
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    printer.printOptions();
                    continue;
                }
            } else if (selection.equals(ONE_TICKET)) {
                printer.promptForTicket();
                int ticketID = Integer.parseInt(in.nextLine());
                try {
                    Ticket ticket = api.getTicket(ticketID);
                    printer.printTicket(ticket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    printer.printOptions();
                    continue;
                }
            } else if (selection.equals(QUIT)) {
                printer.printExit();
                System.exit(0);
            } else {
                printer.printUsage();
            }
        }
        System.exit(0);
    }
}