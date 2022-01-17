package org.example.central;

import org.example.data.EventContainer;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class CentralHubGui extends JFrame {
    public JList jcomp1;
    public JButton checkTicketsButton;
    public JTextField ticketCheckTextField;
    public JScrollPane ticketScrollPane;
    public EventContainer eventContainer;
    public JLabel label;

    public CentralHubGui() {
        eventContainer = new EventContainer();
        guiInit();
    }

    private void guiInit() {
        //construct preComponents
        String[] jcomp1Items = {};

        //construct components
        jcomp1 = new JList(jcomp1Items);
        checkTicketsButton = new JButton("check");
        ticketCheckTextField = new JTextField(5);
        ticketScrollPane = new JScrollPane(jcomp1);
        label = new JLabel("updated");

        //set components properties
        ticketCheckTextField.setEnabled(false);
        checkTicketsButton.addActionListener(e -> checkTickets());
        //adjust size and set layout
        setSize(309, 190);
        setLayout(null);
        setResizable(false);

        //add components
        add(ticketScrollPane);
        add(label);
        add(checkTicketsButton);
        add(ticketCheckTextField);

        //set component bounds (only needed by Absolute Positioning)
        ticketScrollPane.setBounds(0, 0, 100, 155);
        checkTicketsButton.setBounds(160, 30, 100, 25);
        ticketCheckTextField.setBounds(190, 70, 40, 20);
        label.setBounds(160, 0, 100, 25);

        setName("Central Hub");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void refreshEvents(EventContainer eventContainer) {
        String[] data = {};
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < eventContainer.eventArrayList.size(); i++) {
            temp.add(eventContainer.eventArrayList.get(i).getEventName());
        }
        data = temp.toArray(data);
        remove(ticketScrollPane);
        jcomp1 = new JList(data);
        ticketScrollPane = new JScrollPane(jcomp1);
        ticketScrollPane.setBounds(0, 0, 100, 155);
        add(ticketScrollPane);
        revalidate();
        repaint();
    }

    private void checkTickets() {
        EventQueue.invokeLater(() -> {
            if (!jcomp1.isSelectionEmpty()) {
                ticketCheckTextField.
                        setText(String.valueOf(eventContainer.eventArrayList
                                .get(jcomp1.getSelectedIndex())
                                .getTotalTickets()));
            }
        });
    }
}

