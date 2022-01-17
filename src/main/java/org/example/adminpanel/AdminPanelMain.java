package org.example.adminpanel;

import java.io.IOException;

public class AdminPanelMain {
    public static void main(String[] args) throws IOException {
        if (args.length == 2)
            new AdminPanel(args[0], Integer.parseInt(args[1]));
    }
}
