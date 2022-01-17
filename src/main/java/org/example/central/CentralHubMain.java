package org.example.central;

import java.io.IOException;

public class CentralHubMain {
    public static void main(String[] args) throws IOException {
        if (args.length == 1)
            new CentralHub(Integer.parseInt(args[0]));
    }
}
