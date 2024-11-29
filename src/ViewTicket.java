import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class ViewTicket {
    static boolean adminrules = Main.adminrules;

    public static void pressedView(JPanel viewTickets, JScrollPane scrollView, JPanel MainSystem, JPanel AuthorizationPanel, int CenterX, Color BgColor) {
        viewTickets.removeAll();

        viewTickets.setVisible(true);
        MainSystem.setVisible(false);
        AuthorizationPanel.setVisible(false);



        JButton goBack = new JButton("Go Back");
        goBack.setBounds(100, 50, 150, 80);
        goBack.setBackground(new Color(202, 220, 252));
        goBack.addActionListener(event -> Main.goBackB(AuthorizationPanel, MainSystem, viewTickets, false, true, false));

        if (adminrules) {
            JTextArea ticketsArea = new JTextArea(ReadFile.readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\tickets.txt"));
            ticketsArea.setEditable(true);
            ticketsArea.setForeground(Color.WHITE);
            ticketsArea.setBackground(BgColor);
            ticketsArea.setLineWrap(true);
            ticketsArea.setWrapStyleWord(true);

            JButton changeButton = new JButton("Save Changes");
            changeButton.setBounds(400, 50, 150, 80);
            changeButton.setBackground(new Color(202, 220, 252));
            changeButton.addActionListener(event -> {
                WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\tickets.txt", ticketsArea.getText(), false);
                JOptionPane.showMessageDialog(viewTickets, "Changes saved.");
            });
            viewTickets.add(changeButton);
            scrollView.setViewportView(ticketsArea);
        } else {
            String[] tickets = ReadFile.readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\tickets.txt").split("\n");
            JList<String> ticketList = new JList<>(tickets);
            ticketList.setBackground(BgColor);
            ticketList.setForeground(Color.WHITE);
            ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JButton buyTicket = new JButton("Buy Ticket");
            buyTicket.setBounds(400, 50, 150, 80);
            buyTicket.setBackground(new Color(202, 220, 252));
            buyTicket.addActionListener(event -> {
                String selectedTicket = ticketList.getSelectedValue();
                String[] boughtTickets = ReadFile.readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt").split("::");

                if (selectedTicket == null) {
                    JOptionPane.showMessageDialog(viewTickets, "Select a ticket.");
                } else {
                    if (Main.isLoginUserBoughtTicket(boughtTickets, selectedTicket)) {
                        JOptionPane.showMessageDialog(viewTickets, "You already bought this ticket");
                    } else if (Main.isAlreadyBoughtTicket(boughtTickets, selectedTicket)) {
                        JOptionPane.showMessageDialog(viewTickets, "This ticket is already bought by someone else");
                    } else {
                        selectedTicket = selectedTicket.replace("\n", "").replace("\r", "");
                        String record = Main.loginname + "::" + selectedTicket + "::" + "\n";
                        
                        String regex = "([A-Za-z]+)-([A-Za-z]+), (\\d{4}-\\d{2}-\\d{2}\\(\\d{2}:\\d{2}\\)) - (\\d{4}-\\d{2}-\\d{2}\\(\\d{2}:\\d{2}\\)), (\\w+), ([A-Z]\\d+), (\\d+)";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(selectedTicket);
                        
                        if (matcher.find()) {
                            // Extract values using matcher groups
                            String fromCity = matcher.group(1);
                            String toCity = matcher.group(2);
                            String departureDate = matcher.group(3).replace("(", " ").replace(")", "");
                            String arrivalDate = matcher.group(4).replace("(", " ").replace(")", "");
                            String classType = matcher.group(5);
                            String seat = matcher.group(6);
                            String price = matcher.group(7);
                        

                            
                


                            WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", "=========================================\n", true);
                            WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", "From: " + fromCity + "\n", true);
                            WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", "To: " + toCity + "\n", true);
                            WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", "Booking Date: " + departureDate + "\n", true);
                            WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", "Arrival: " + arrivalDate + "\n", true);
                            WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", "Seat: " + seat + "\n", true);
                            WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", "Type: " + classType + "\n", true);
                            WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", "Total price: " + price + "\n", true);
                            WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", "Bought By: " + Main.loginname + "\n", true);
                            WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", "\n", true);
                            WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", record, true);
                            WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", "=========================================\n", true);
                            JOptionPane.showMessageDialog(viewTickets, "Congrats");
                            selectedTicket = null;
                        } else {
                            JOptionPane.showMessageDialog(viewTickets, "Invalid ticket format");
                        }
                        
                }}
                viewTickets.repaint();
            });
            viewTickets.add(buyTicket);
            scrollView.setViewportView(ticketList);
        }
        viewTickets.add(goBack);
        viewTickets.add(scrollView);
        viewTickets.revalidate();
        viewTickets.repaint();
    }
}
