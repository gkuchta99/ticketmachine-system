package org.example.central;

import org.example.data.EventContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CentralHubAdminPanelDataTransferThread implements Runnable {
    private ServerSocket serverSocket;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private CentralHubGui gui;
    private EventContainer eventContainer;
    private Boolean updateTicketMachines;

    public CentralHubAdminPanelDataTransferThread(Socket socket, EventContainer eventContainer, CentralHubGui gui) throws IOException {
        this.eventContainer = eventContainer;
        this.socket = socket;
        this.gui = gui;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        String inputLine = "";
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                inputLine = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!inputLine.equals("")) {
                System.out.println("centralhub thread received something");
                try {
                    receive(inputLine);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void receive(String inputLine) throws InterruptedException {
        if (inputLine.charAt(0) == '#') {
            StringBuilder dataRow = new StringBuilder(inputLine);
            dataRow.deleteCharAt(0);
            String[] data = dataRow.toString().split(";");
            String eventName = data[0];
            int tickets = Integer.parseInt(data[1]);
            eventContainer.addEvent(eventName, tickets);
        } else {
            System.err.println("to sie nie powinno pojawic");
        }

        gui.refreshEvents(eventContainer);
        gui.label.setText("not updated");
        gui.revalidate();
        gui.repaint();
    }
}
