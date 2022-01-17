# Ticket machine system

Ticket machine system is tcp/socket based application, which consists 3 .jar files. Project works with Java 11.

## CentralHub
We run one instantion of CentralHub. CentralHub cooperates with other instances of app (AdminPanel, TicketMachine). CentralHub handles requests from AdminPanels to add more tickets for event or create new event and handle requests from ticket machines for buying tickets. To run instantion of CentralHub use code below.
```bash
java -jar centralHub.jar <port_number>
```

## Installation

Use the package manager [pip](https://pip.pypa.io/en/stable/) to install foobar.

```bash
pip install foobar
```

## Usage

```python
import foobar

# returns 'words'
foobar.pluralize('word')

# returns 'geese'
foobar.pluralize('goose')

# returns 'phenomenon'
foobar.singularize('phenomena')
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
