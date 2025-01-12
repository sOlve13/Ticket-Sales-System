import java.awt.Color;
import javax.swing.*;

public class ViewTicket {

    public static void pressedView(JPanel viewTickets, JScrollPane scrollView, JPanel MainSystem, JPanel AuthorizationPanel, int CenterX, Color BgColor, JPanel myTicketPanel, JScrollPane myScroll) {
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
        goBack.addActionListener(event -> Main.goBackB(AuthorizationPanel, MainSystem, viewTickets, false, true, false, false));

        //updateFreeTickets();
        Main.client.send_text(Main.adm + "::" + "update" + "::");
        if (adminrules) {
            //String adm3 = "" + Main.adminrules;
            Main.client.send_text(Main.adm + "::" + "getallfreeticket" + "::");
            String[] tickets2 = Main.client.get_free_tickets();

            //String[] tickets = ReadFile.readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\freeTickets.txt").split("\n");
 
            DefaultListModel<String> model = new DefaultListModel<>();
            for (String ticket : tickets2) {
                model.addElement(ticket);
            }
        
            JList<String> ticketList = new JList<>(model);
            ticketList.setBackground(BgColor);
            ticketList.setForeground(Color.WHITE);
            ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
            scrollView.setViewportView(ticketList);
            viewTickets.add(scrollView);
        
            JButton removeTButton = new JButton("Remove Ticket");
            removeTButton.setBounds(400, 50, 200, 80);
            removeTButton.setBackground(new Color(202, 220, 252));
        
            removeTButton.addActionListener(event -> {
                String selectedTicket = ticketList.getSelectedValue();
                if (selectedTicket == null) {
                    JOptionPane.showMessageDialog(viewTickets, "Please select a ticket to remove.");
                } else {
                    model.removeElement(selectedTicket);
        
                    StringBuilder updatedTickets = new StringBuilder();
                    for (int i = 0; i < model.getSize(); i++) {
                        updatedTickets.append(model.getElementAt(i)).append("\n");
                    }
                    //String adm = "" + Main.adminrules;
                    Main.client.send_text(Main.adm + "::" + "removeticket" +"::" + updatedTickets.toString());
                    //WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\freeTickets.txt", updatedTickets.toString(), false);
                    //updateFreeTickets();
                    Main.client.send_text(Main.adm + "::" + "update" + "::");
                    
                    JOptionPane.showMessageDialog(viewTickets, "Ticket removed successfully.");
                }
        
                viewTickets.repaint();
            });
        
            viewTickets.add(removeTButton);
            viewTickets.revalidate();
            viewTickets.repaint();
        } 
        else {
            //String adm2 = "" + Main.adminrules;
            Main.client.send_text(Main.adm + "::" + "getallfreeticket" + "::");
            String[] tickets = Main.client.get_free_tickets();
            //String[] tickets = ReadFile.readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\freeTickets.txt").split("\n");
            DefaultListModel<String> model = new DefaultListModel<>();
            for (String ticket : tickets) {
                model.addElement(ticket);
            }

            JList<String> ticketList = new JList<>(model);
            ticketList.setBackground(BgColor);
            ticketList.setForeground(Color.WHITE);
            ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            JButton myTickets = new JButton("My Tickets");
            myTickets.setBounds(300, 50, 150, 80);
            myTickets.setBackground(new Color(202, 220, 252));
            myTickets.addActionListener(event -> {
                UserBoughtTicket.myTicketPanelB(myTicketPanel, myScroll, MainSystem, AuthorizationPanel, viewTickets, CenterX, BgColor);
            });
            

            JButton buyTicket = new JButton("Buy Ticket");
            buyTicket.setBounds(500, 50, 150, 80);
            buyTicket.setBackground(new Color(202, 220, 252));
            buyTicket.addActionListener(event -> {
                String selectedTicket = ticketList.getSelectedValue();
                if (selectedTicket == null) {
                    JOptionPane.showMessageDialog(viewTickets, "Select a ticket.");
                } else {
                    /* 
                    String[] boughtTickets = ReadFile.readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt").split("::");
                    
                    if (Main.isAlreadyBoughtTicket(boughtTickets, selectedTicket)) {
                        JOptionPane.showMessageDialog(viewTickets, "This ticket is already bought by someone else");
                    } else {
                        selectedTicket = selectedTicket.replace("\n", "").replace("\r", "");
                        String record = Main.loginname + "::" + selectedTicket + "::" + "\n";
                        //String adm1 = "" + Main.adminrules;
                        Main.client.send_text(Main.adm + "::" + "buyticket" +"::" + record);
                        //WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", record, true);
                        //updateFreeTickets();
                        Main.client.send_text(Main.adm + "::" + "update" + "::");
                        model.removeElement(selectedTicket);

                        JOptionPane.showMessageDialog(viewTickets, "Congrats, ticket bought!");
                    }*/

                    Main.client.send_text(Main.adm + "::" + "isalredaybought" + "::" + selectedTicket);
                    if(Main.client.get_bool()){
                        JOptionPane.showMessageDialog(viewTickets, "This ticket is already bought by someone else");
                    }else{
                        Main.client.send_text(Main.adm + "::" + "buyticket" +"::" + Main.loginname + "::" + selectedTicket);
                        Main.client.send_text(Main.adm + "::" + "update" + "::");
                        model.removeElement(selectedTicket);
                        JOptionPane.showMessageDialog(viewTickets, "Congrats, ticket bought!");
                    }



                }
                viewTickets.repaint();
            });
            viewTickets.add(myTickets);

            viewTickets.add(buyTicket);
            scrollView.setViewportView(ticketList);
            viewTickets.revalidate();
            viewTickets.repaint();
        }
        viewTickets.add(goBack);
        viewTickets.add(scrollView);
        viewTickets.revalidate();
        viewTickets.repaint();
    }
/* 
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
    }*/
}
