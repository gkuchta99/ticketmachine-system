package org.example.ticketmachine;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TicketMachine {
    private ServerSocket serverSocket;
    private Socket receivingSocket;
    private Socket sendingSocket;
    private PrintWriter out;
    private BufferedReader in;
    private TicketMachineGui ticketMachineGui;

    public TicketMachine() throws IOException {
        ticketMachineGui = new TicketMachineGui();
        sendingSocket = new Socket("127.0.0.1", 4444);
        out = new PrintWriter(sendingSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(sendingSocket.getInputStream()));
        out.println("ticket");
        TicketMachineThread ticketMachineThread = new TicketMachineThread(sendingSocket, ticketMachineGui);
        Thread thread = new Thread(ticketMachineThread);
        thread.setDaemon(true);
        thread.start();
        ticketMachineGui.buyTicketButton.addActionListener(e -> addEvent());
    }

    public void addEvent() {
        EventQueue.invokeLater(() -> {
            if (!ticketMachineGui.jcomp3.getText().equals("")) {
                if (!ticketMachineGui.eventList.isSelectionEmpty()) {
                    int eventIndex = ticketMachineGui.eventList.getSelectedIndex();
                    String eventName = ticketMachineGui.eventArrayList.get(eventIndex).getEventName();
                    ticketMachineGui.getTicket(eventIndex);
                    out.println(eventName + ";" + ticketMachineGui.jcomp3.getText());
                    ticketMachineGui.jcomp3.setText("");
                }
            }
        });
    }
}
