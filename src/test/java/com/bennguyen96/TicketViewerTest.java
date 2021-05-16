package com.bennguyen96;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TicketViewerTest {

    private TicketViewer ticketViewer;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream originalOut = System.out;
    private InputStream sysInBackup;

    @Before
    public void setUp()  {
        ticketViewer = new com.bennguyen96.TicketViewer();
        System.setOut(new PrintStream(out));
        sysInBackup = System.in;
    }

    @After
    public void cleanUp() {
        System.setOut(originalOut);
        System.setIn(sysInBackup);
    }

    @Test
    public void happyPathTest() throws Throwable {
        TicketViewer.main(null);
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);

        assertEquals("Welcome to the Zendesk Ticket Viewer\n" +
                "Select '1' to view all tickets\n" +
                "Select '2' to view a ticket\n" +
                "Type 'quit' to exit", out.toString());

    }
}