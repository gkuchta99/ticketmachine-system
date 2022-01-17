package org.example.ticketmachine;

import org.example.data.EventTicketMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class TicketMachineThread implements Runnable {
    private PrintWriter out;
    private BufferedReader in;
    private TicketMachineGui ticketMachineGui;
    private final Socket socket;

    public TicketMachineThread(Socket socket, TicketMachineGui ticketMachineGui) throws IOException {
        this.ticketMachineGui = ticketMachineGui;
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        String inputLine = "";
        while (true) {
            try {
                inputLine = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!inputLine.equals("")) {
                receiveInfo(inputLine);
            }
        }
    }

    private synchronized void receiveInfo(String inputLine) {
        String[] eventsRawStringArr = inputLine.split(";");
        ArrayList<EventTicketMachine> ticketMachineArrayList = new ArrayList<>();
        for (String s : eventsRawStringArr) {
            String[] temp = s.split("#");
            ticketMachineArrayList.add(new EventTicketMachine(temp[0], Integer.parseInt(temp[1])));
        }
        ticketMachineGui.eventArrayList = ticketMachineArrayList;
        ticketMachineGui.refreshEvents();
    }

}
