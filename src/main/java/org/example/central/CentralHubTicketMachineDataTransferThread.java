package org.example.central;

import org.example.data.EventContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class CentralHubTicketMachineDataTransferThread implements Runnable {
    private ServerSocket serverSocket;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private CentralHubGui gui;
    private EventContainer eventContainer;

    public CentralHubTicketMachineDataTransferThread(Socket socket, EventContainer eventContainer, CentralHubGui gui) throws IOException {
        this.socket = socket;
        this.eventContainer = eventContainer;
        this.gui = gui;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (gui.label.getText().equals("not updated")) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < eventContainer.eventArrayList.size(); i++) {
                    if (i != 0) {
                        stringBuilder.append(";" + eventContainer.eventArrayList.get(i).getEventName() + "#" +
                                eventContainer.eventArrayList.get(i).getTotalTickets());
                    } else {
                        stringBuilder.append(eventContainer.eventArrayList.get(i).getEventName() + "#" +
                                eventContainer.eventArrayList.get(i).getTotalTickets());
                    }
                }
                out.println(stringBuilder.toString());
                gui.label.setText("updated");
                gui.revalidate();
                gui.repaint();
            }
            try {
                if (in.ready()) {
                    String inputLine = in.readLine();
                    System.err.println(inputLine);
                    String[] data = inputLine.split(";");
                    for (int i = 0; i < eventContainer.eventArrayList.size(); i++) {
                        if (eventContainer.eventArrayList.get(i).getEventName().equals(data[0])) {
                            System.out.println("i work");
                            eventContainer.eventArrayList.get(i).setTotalTickets(eventContainer.eventArrayList.get(i).getTotalTickets() - 1);
                            eventContainer.eventArrayList.get(i).getParticipantsArrayList().add(data[1]);
                            gui.label.setText("not updated");
                            gui.revalidate();
                            gui.repaint();
                            gui.refreshEvents(eventContainer);
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
