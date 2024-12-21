import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class UserBoughtTicket {

    public static void myTicketPanelB(JPanel myTicket, JScrollPane myScroll, JPanel MainSystem, JPanel AuthorizationPanel, JPanel viewTickets, int CenterX, Color BgColor) {
        myTicket.removeAll();
        myScroll.setViewportView(null);
        myScroll.repaint();
        myScroll.revalidate();
    
        myTicket.setVisible(true);
        MainSystem.setVisible(false);
        AuthorizationPanel.setVisible(false);
        viewTickets.setVisible(false);
        
        

        JButton goBack = new JButton("Go Back");
        goBack.setBounds(100, 50, 150, 80);
        goBack.setBackground(new Color(202, 220, 252));
        goBack.addActionListener(event -> Main.goBackB(AuthorizationPanel, MainSystem, viewTickets, false, false, true, false));
    
        String[] boughtTickets;
        try {
            boughtTickets = ReadFile.readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt").split("::");
        } catch (Exception e) {
            boughtTickets = new String[0];
        }
    
        String[] myTickets = isLoginUserBoughtTicket(boughtTickets, "");
    
        if (myTickets.length == 0) {
            JLabel noTicketsLabel = new JLabel("No tickets purchased yet.");
            noTicketsLabel.setBounds(CenterX, 200, 700, 30);
            noTicketsLabel.setForeground(Color.RED);
            myTicket.add(noTicketsLabel);
        } else {
            DefaultListModel<String> model = new DefaultListModel<>();
            for (String ticket : myTickets) {
                model.addElement(ticket);
            }
    
            JList<String> ticketList = new JList<>(model);
            ticketList.setBackground(BgColor);
            ticketList.setForeground(Color.WHITE);
    
            myScroll.setViewportView(ticketList);
        }
    
        myTicket.add(goBack);
        myTicket.add(myScroll);
        myTicket.revalidate();
        myTicket.repaint();
    }

        public static String[] isLoginUserBoughtTicket(String[] boughtTickets, String selectedTicket) {
        List<String> userTickets = new ArrayList<>();
    
        for (int i = 0; i < boughtTickets.length - 1; i++) {
            if (boughtTickets[i].trim().isEmpty()) {
                continue;
            }    
            String user =   boughtTickets[i].trim();
            String ticketDetails = boughtTickets[i+1].trim();
            if (user.equals(Main.loginname)) {
                userTickets.add(ticketDetails);
            }
        }
    
        return userTickets.toArray(new String[0]);
    }
    
}
