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

    @Test
    public void happyPathAllTicketsTest() throws Throwable {
        ByteArrayInputStream in = new ByteArrayInputStream("1\nnext\nquit".getBytes());
        System.setIn(in);
        TicketViewer.main(null);
    }

    @Test
    public void happyPathOneTicketTest() throws Throwable {
        ByteArrayInputStream in = new ByteArrayInputStream("2\n1\nquit".getBytes());
        System.setIn(in);
        TicketViewer.main(null);
    }
}