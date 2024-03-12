## US State Energy Consumption Protocol
Whenever you develop a server application, you need to specify some
application-level protocol that clients can use to interact with the server.

In this example, we'll create a "US State Energy Consumption Protocol" to show how to implement a server.
The table below shows the protocol format:

| Client Request          | Server Response                   | Description                                        |
|-------------------------|-----------------------------------|----------------------------------------------------|
| AMOUNT state energy     | state, energy, and the amount     | Get the amount for a given energy type for a state |
| INCREASE state energy n | state, energy, and the new amount | Increase amount n for an energy for a state        |
| DECREASE state energy n | state, energy, and the new amount | Decrease amount n for an energy for a state        |
| QUIT                    | Quit the connection               |                                                    |

Sources:
Tien Han (wrote the protocol)
https://corgis-edu.github.io/corgis/json/energy/ (2019 Data/energy consumption for commercial usage)

### Running this Protocol
To run this project, run the program in "StateStatisticsServer" first.
Once it's running, run the program in "StatesClient" to test out the protocol.