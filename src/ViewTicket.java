import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;

public class ViewTicket {

    public static void pressedView(JPanel viewTickets, JScrollPane scrollView, JPanel MainSystem, JPanel AuthorizationPanel, int CenterX, Color BgColor) {
        viewTickets.removeAll();
        scrollView.repaint();
        scrollView.revalidate();

        viewTickets.setVisible(true);
        MainSystem.setVisible(false);
        AuthorizationPanel.setVisible(false);

        boolean adminrules = Main.adminrules;

        JButton goBack = new JButton("Go Back");
        goBack.setBounds(100, 50, 150, 80);
        goBack.setBackground(new Color(202, 220, 252));
        goBack.addActionListener(event -> Main.goBackB(AuthorizationPanel, MainSystem, viewTickets, false, true, false));

        updateFreeTickets();
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
            String[] tickets = ReadFile.readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\freeTickets.txt").split("\n");
            DefaultListModel<String> model = new DefaultListModel<>();
            for (String ticket : tickets) {
                model.addElement(ticket);
            }

            JList<String> ticketList = new JList<>(model);
            ticketList.setBackground(BgColor);
            ticketList.setForeground(Color.WHITE);
            ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JButton buyTicket = new JButton("Buy Ticket");
            buyTicket.setBounds(400, 50, 150, 80);
            buyTicket.setBackground(new Color(202, 220, 252));
            buyTicket.addActionListener(event -> {
                String selectedTicket = ticketList.getSelectedValue();
                if (selectedTicket == null) {
                    JOptionPane.showMessageDialog(viewTickets, "Select a ticket.");
                } else {
                    String[] boughtTickets = ReadFile.readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt").split("::");

                    if (Main.isLoginUserBoughtTicket(boughtTickets, selectedTicket)) {
                        JOptionPane.showMessageDialog(viewTickets, "You already bought this ticket");
                    } else if (Main.isAlreadyBoughtTicket(boughtTickets, selectedTicket)) {
                        JOptionPane.showMessageDialog(viewTickets, "This ticket is already bought by someone else");
                    } else {
                        selectedTicket = selectedTicket.replace("\n", "").replace("\r", "");
                        String record = Main.loginname + "::" + selectedTicket + "::" + "\n";
                        WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", record, true);
                        updateFreeTickets();
                        model.removeElement(selectedTicket);

                        JOptionPane.showMessageDialog(viewTickets, "Congrats, ticket bought!");
                    }
                }
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

    private static void updateFreeTickets() {
        List<String> allTickets = new ArrayList<>(Arrays.asList(ReadFile.readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\tickets.txt").split("\n")));
        List<String> boughtTickets = new ArrayList<>(Arrays.asList(ReadFile.readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt").split("::")));

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
        WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\freeTickets.txt", updatedFreeTickets, false);
    }
}
