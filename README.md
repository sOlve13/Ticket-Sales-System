# Ticket Sales System

Java desktop app for buying travel tickets, made for my OOP 2 course at university.
The GUI is made with Swing and it connects to a small server through sockets. All data is saved in text files.

## Features

- login (admin or normal user)
- admin can add and remove tickets
- user can view free tickets and buy them
- user can see their bought tickets
- server handles every client in a separate thread

## Structure

- `src/` - client side (GUI, socket client, tests)
- `server/` - server
- `tickets/` - text files with tickets and bought tickets
- `lib/` - JUnit 4 and Hamcrest

## How to run

Before running, change the file paths in `server/Server.java` to your `tickets` folder (they are hardcoded right now).

```
javac -d bin -cp "lib/*" src/*.java
javac -d server server/Server.java

java -cp server Server
java -cp bin Main
```

Start the server first, then the app in another terminal.
Admin login is `admin` / `admin`, any other name logs in as a user.

## Tests

Tests are in `src/Testing.java` and they need the server to be running.

```
java -cp "bin;lib/*" org.junit.runner.JUnitCore Testing
```

## TODO

- relative paths instead of hardcoded ones
- proper user accounts
- maybe a database instead of txt files
