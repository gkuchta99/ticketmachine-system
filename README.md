# Ticket machine system

Ticket machine system is tcp/socket based application, which consists 3 .jar files. Project works with Java 11.

## CentralHub
We run one instantion of CentralHub. CentralHub cooperates with other instances of app (AdminPanel, TicketMachine). CentralHub handles requests from AdminPanels to add more tickets for event or create new event and handle requests from ticket machines for buying tickets. To run instantion of CentralHub use command below.

```bash
java -jar centralHub.jar <port_number>
```
## AdminPanel
We can run multiple instantions of AdminPanel. AdminPanel cooperates with single instantion of CentralHub. Via AdminPanel we can add tickets for existing event or add new event with free tickets. To run instantion of AdminPanel use command below.

```bash
java -jar adminPanel.jar <ip_of_centralhub> <port_of_centralhub>
```

## TicketMachine
We can run multiple instantions of TicketMachine. TicketMachine cooperates with single instantion of CentralHub. Via TicketMachine we can get ticket for existing event. To run instantion of TicketMachine use command below.

```bash
java -jar ticketMachine.jar <ip_of_centralhub> <port_of_centralhub>
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)
