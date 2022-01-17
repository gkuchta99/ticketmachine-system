package org.example.adminpanel;

import java.awt.*;
import javax.swing.*;

public class AdminPanelGui extends JFrame {
    protected JLabel eventNameLabel;
    protected JTextField eventNameTextField;
    protected JLabel eventTicketQuantityLabel;
    protected JTextField ticketQuantityTextField;
    protected JButton connectWithHubButton;
    protected JButton addEventButton;

    public AdminPanelGui() {
        guiInit();
    }

    private void guiInit() {
        //construct components
        eventNameLabel = new JLabel("event name");
        eventNameTextField = new JTextField(5);
        eventTicketQuantityLabel = new JLabel("ticket quantity");
        ticketQuantityTextField = new JTextField(5);
        connectWithHubButton = new JButton("connect");
        addEventButton = new JButton("add event");
        addEventButton.setEnabled(false);

        //adjust size and set layout
        GridLayout layout = new GridLayout(3, 2, 0, 0);
        setLayout(layout);
        setResizable(false);
        setSize(250, 150);
        setName("admin panel");

        //add components
        add(eventNameLabel);
        add(eventNameTextField);
        add(eventTicketQuantityLabel);
        add(ticketQuantityTextField);
        add(connectWithHubButton);
        add(addEventButton);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
