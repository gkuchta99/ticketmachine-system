package org.example.ticketmachine;

import java.io.IOException;

public class TicketMachineMain {
    public static void main(String[] args) throws IOException {
        if (args.length == 2)
            new TicketMachine(args[0], Integer.parseInt(args[1]));
    }
}
