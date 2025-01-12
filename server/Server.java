import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Server {
    public static int bol = 0;

    public static void main(String[] args) {
        try {
            ServerSocket serverS = new ServerSocket(3555);
            System.out.println("Server is running");

            while (true) {
                Socket s = serverS.accept();
                System.out.println("New client");

                new ClientHandler(s).start();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }



    public static String readFile(String filePath){
        String data1 = "";
        try{
        data1 = new String(Files.readAllBytes(Paths.get(filePath)));
        }
        catch(IOException e){
            System.out.println("File was not read");
        }
        //System.out.println(data1);
        return data1;

    }


    public static void writeFile(String filePath, String text, boolean append) {
        try {
            FileWriter textWrite = new FileWriter(filePath, append);
            textWrite.write(text);
            textWrite.close();
        } catch (IOException e) {
            System.out.println("Couldn't open the file");
        }
    }

    public static String[] isLoginUserBoughtTicket(String[] boughtTickets, String loginname) {
        List<String> userTickets = new ArrayList<>();
    
        for (int i = 0; i < boughtTickets.length - 1; i++) {
            if (boughtTickets[i].trim().isEmpty()) {
                continue;
            }    
            String user =   boughtTickets[i].trim();
            String ticketDetails = boughtTickets[i+1].trim();
            if (user.equals(loginname)) {
                userTickets.add(ticketDetails);
            }
        }
    
        return userTickets.toArray(new String[0]);
    }

    private static void updateFreeTickets() {
        List<String> allTickets = new ArrayList<>(Arrays.asList(readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\tickets.txt").split("\n")));
        List<String> boughtTickets = new ArrayList<>(Arrays.asList(readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt").split("::")));

        List<String> boughtTicketRoutes = new ArrayList<>();
        for (String ticket : boughtTickets) {
            String cleanTicket = ticket.replaceAll("^[^:]+::", "").replaceAll("::$", "");

            String[] ticketDetails = cleanTicket.split(",");
            if (ticketDetails.length > 1) {
                String routeDetails = ticketDetails[0] + "," + ticketDetails[1];
                boughtTicketRoutes.add(routeDetails);
            }
        }
        // Remove the bought tickets from the allTickets list based on the route information
        for (String boughtRoute : boughtTicketRoutes) {
            allTickets.removeIf(ticket -> ticket.contains(boughtRoute));  
        }
        String updatedFreeTickets = String.join("\n", allTickets);
        writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\freeTickets.txt", updatedFreeTickets, false);
    }

    public static boolean isAlreadyBoughtTicket(String[] boughtTickets, String selectedTicket){
        for (int i = 0; i < boughtTickets.length; i++) {
            if (selectedTicket.equals(boughtTickets[i].trim())) {
                return true;
            }
        }
        return  false;
    }
    
    public void send_file(){}
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            for(int i = 0; i < 10;  i++){
            
                /* 
            String input = (String) ois.readObject();
            System.out.println(input);
            String[] login = input.split("::");
            System.out.println(login[0]);
            System.out.println(login[1]);
            int bol = 0; 

            if (login[0].equals("admin") && login[1].equals("admin")) {
                bol = 1;
                oos.writeObject(true);
            } else {
                oos.writeObject("0");
            }
            System.out.println(bol);

            String[] boughtTickets = readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt").split("::");
            oos.writeObject(boughtTickets);
            System.out.println("file sent");*/









            while (true) { 
                String inp = (String) ois.readObject();
                String[] switc = inp.split("::");
                System.out.println(switc[0] + switc[1]);
                if(switc[0].equals("false")){
                switch (switc[1]) {
                    case "login":
                        if (switc[2].equals("admin") && switc[3].equals("admin")) {
                            oos.writeObject(true);
                        } else {
                            oos.writeObject(false);
                        }
                        break;
                    case "getallfreeticket":
                        String[] tickets = readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\freeTickets.txt").split("\n");
                        oos.writeObject(tickets);
                        break;
                    case "buyticket":
                        String record = switc[2] + "::" + switc[3] + "::" + "\n";
                        writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", record, true);
                        break;
                    case "getboughttickets":
                        String[] boughtTickets = readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt").split("::");
                        String[] myTickets = isLoginUserBoughtTicket(boughtTickets, switc[2]);
                        oos.writeObject(myTickets);
                        break;
                    case "update":
                        updateFreeTickets();
                        break;
                    case "isalredaybought":
                        String[] ticketsB = readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt").split("::");
                        boolean res = isAlreadyBoughtTicket(ticketsB, switc[2]);
                        oos.writeObject(res);
                        break;
                        

                }}
                else if(switc[0].equals("true")){
                switch (switc[1]) {

                    case "addticket":
                        writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\tickets.txt", switc[2], true);
                        break;
                    case "removeticket":
                        writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\tickets.txt", switc[2], false);
                        break;
                    case "getallfreeticket":
                        String[] tickets = readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\freeTickets.txt").split("\n");
                        oos.writeObject(tickets);
                        break;
                    case "update":
                        updateFreeTickets();
                        break;

                }
                }


            }
        }

            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
