package org.example.adminpanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class AdminPanel extends AdminPanelGui {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public AdminPanel() {
        addEventButton.addActionListener(e -> addEvent());
        connectWithHubButton.addActionListener(e -> {
            try {
                connect();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    private void addEvent() {
        String eventName = eventNameTextField.getText();
        String eventTicketQuantity = ticketQuantityTextField.getText();
        int eventTicketQuantityInteger = Integer.parseInt(eventTicketQuantity);
        if (!eventName.equals("") || eventTicketQuantityInteger > 0) {
            out.println("#" + eventName + ";" + eventTicketQuantityInteger);
            eventNameTextField.setText("");
            ticketQuantityTextField.setText("");
            repaint();
        }
    }

    private void connect() throws IOException {
        clientSocket = new Socket("127.0.0.1", 4444);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        addEventButton.setEnabled(true);
        connectWithHubButton.setEnabled(false);
        out.println("admin");
    }
}
