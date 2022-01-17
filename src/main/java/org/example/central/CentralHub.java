package org.example.central;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CentralHub {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private CentralHubGui gui;
    String ip;
    int port;
    Boolean updateTicketMachines = false;

    public CentralHub(int port) throws IOException {
        gui = new CentralHubGui();
        serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String option = in.readLine();
            if (option.equals("admin")) {
                CentralHubAdminPanelDataTransferThread centralHub = new CentralHubAdminPanelDataTransferThread(socket, gui.eventContainer, gui);
                Thread thread = new Thread(centralHub);
                thread.start();
            } else if (option.equals("ticket")) {
                System.out.println("started thread for ticket machine");
                CentralHubTicketMachineDataTransferThread centralHubTicketMachineDataTransferThread = new CentralHubTicketMachineDataTransferThread(socket, gui.eventContainer, gui);
                Thread thread = new Thread(centralHubTicketMachineDataTransferThread);
                thread.start();
            }
        }
    }

}
