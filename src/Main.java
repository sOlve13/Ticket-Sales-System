import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;

public class Main {
    static boolean adminrules = false;
    static Color BgColor = new Color(0, 36, 107);
    static String loginname = "";

    public static String readFile(String filePath) {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\tickets.txt")));
        } catch (IOException e) {
            System.out.println("File was not read");
        }
        return data;
    }

    public static void writeFile(String filePath, String text) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(text);
        } catch (IOException e) {
            System.out.println("Couldn't write to the file");
        }
    }

    public static void writeBoughtFile(String filePath, String text) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(text);
        } catch (IOException e) {
            System.out.println("Couldn't write to the file");
        }
    }

    public static void goBackB(JPanel AuthorizationPanel, JPanel MainSystem, JPanel viewTickets, boolean AP, boolean MS, boolean VT) {
        if (AP) {
            adminrules = false;
            loginname = "";
        }
        AuthorizationPanel.setVisible(AP);
        MainSystem.setVisible(MS);
        viewTickets.setVisible(VT);
    }

    public static void pressedView(JPanel viewTickets, JScrollPane scrollView, JPanel MainSystem, JPanel AuthorizationPanel, int CenterX) {
        viewTickets.removeAll();

        viewTickets.setVisible(true);
        MainSystem.setVisible(false);
        AuthorizationPanel.setVisible(false);

        JButton goBack = new JButton("Go Back");
        goBack.setBounds(100, 50, 150, 80);
        goBack.setBackground(new Color(202, 220, 252));
        goBack.addActionListener(event -> goBackB(AuthorizationPanel, MainSystem, viewTickets, false, true, false));
        viewTickets.add(goBack);

        if (adminrules) {
            JTextArea ticketsArea = new JTextArea(readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\tickets.txt"));
            ticketsArea.setEditable(true);
            ticketsArea.setForeground(Color.WHITE);
            ticketsArea.setBackground(BgColor);
            ticketsArea.setLineWrap(true);
            ticketsArea.setWrapStyleWord(true);

            JButton changeButton = new JButton("Save Changes");
            changeButton.setBounds(400, 50, 150, 80);
            changeButton.setBackground(new Color(202, 220, 252));
            changeButton.addActionListener(event -> {
                writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\tickets.txt", ticketsArea.getText());
                JOptionPane.showMessageDialog(viewTickets, "Changes saved.");
            });
            viewTickets.add(changeButton);
            scrollView.setViewportView(ticketsArea);
        } else {
            String[] tickets = readFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\tickets.txt").split("\n");
            JList<String> ticketList = new JList<>(tickets);
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
                    String record = loginname + " bought: " + selectedTicket + "\n";
                    writeBoughtFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\boughtTickets.txt", record);
                    JOptionPane.showMessageDialog(viewTickets, "Congrats");
                }
            });
            viewTickets.add(buyTicket);
            scrollView.setViewportView(ticketList);
        }

        viewTickets.add(scrollView);
        viewTickets.revalidate();
        viewTickets.repaint();
    }
        /*public static void pressedView(JPanel viewTickets, JScrollPane scrollView, JPanel MainSystem, JPanel AuthorizationPanel, int CenterX){
        viewTickets.removeAll();

        viewTickets.setVisible(true);
        MainSystem.setVisible(false);
        AuthorizationPanel.setVisible(false);

        JTextArea ticketsArea = new JTextArea(readFile("C:/Users/Rinat/Documents/University/YEAR 2/OOP 2 Project/Game/Game/tickets/tickets.txt"));
        ticketsArea.setEditable(adminrules);
        ticketsArea.setForeground(Color.WHITE);
        ticketsArea.setBackground(BgColor);
        ticketsArea.setLineWrap(true);
        ticketsArea.setWrapStyleWord(true);


        JButton goBack = new JButton("Go Back");
        goBack.setBounds(100, 50, 150, 80);
        goBack.setBackground(new Color(202, 220, 252));
        
        goBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                goBackB(AuthorizationPanel, MainSystem, viewTickets, false, true, false);
            }
        });
        if (!adminrules) {
            String[] tickets = readFile("C:/Users/Rinat/Documents/University/YEAR 2/OOP 2 Project/Game/Game/tickets/tickets.txt").split("\n");
            JList<String> ticketList = new JList<>(tickets);
            ticketList.setBackground(BgColor);
            ticketList.setForeground(Color.WHITE);
            ticketList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
            // Wrap JList in a JScrollPane
            scrollView.setViewportView(ticketList);
            JButton buyTicket = new JButton("Buy Ticket");
            buyTicket.setBounds(400, 50, 150, 80);
            buyTicket.setBackground(new Color(202, 220, 252));
    
            buyTicket.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    String selectedTicket = ticketList.getSelectedValue();
                    if (selectedTicket == null) {
                        JOptionPane.showMessageDialog(viewTickets, "Please select a ticket to buy.");
                    } else {
                        // Append ticket and username to boughtTickets.txt
                        String purchaseRecord = loginname + " bought: " + selectedTicket + "\n";
                        writeFileAppend("C:/Users/Rinat/Documents/University/YEAR 2/OOP 2 Project/Game/Game/tickets/boughtTickets.txt", purchaseRecord);
                        JOptionPane.showMessageDialog(viewTickets, "Ticket purchased successfully!");
                    }
                }
            });
    
            viewTickets.add(buyTicket);
        }
        if(adminrules == true){
        JButton changeButton = new JButton("Save Changes");
        changeButton.setBounds(400, 50, 150, 80);
        changeButton.setBackground(new Color(202, 220, 252));
        
        changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String changedText = ticketsArea.getText();
                writeFile("C:/Users/Rinat/Documents/University/YEAR 2/OOP 2 Project/Game/Game/tickets/tickets.txt", changedText);
                JOptionPane.showMessageDialog(viewTickets, "Changes were made");
            }
        });
        viewTickets.add(changeButton);
    } else{
            for (Component components : viewTickets.getComponents()){
                if(components instanceof JButton && ((JButton) components).getText().equals("Save Changes")){
                    viewTickets.remove(components);
                    break;
                }

            }

    }
        scrollView.setViewportView(ticketsArea); 
        viewTickets.add(goBack);
        viewTickets.add(scrollView);
        

    }*/

    public static void main(String[] args) {
        JFrame mainframe = new JFrame();
        mainframe.setSize(800, 800);
        mainframe.setTitle("Ticket Selling System");
        mainframe.setLayout(null);
        mainframe.setResizable(false);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.getContentPane().setBackground(new Color(39, 39, 39));

        JPanel AuthorizationPanel = new JPanel(null);
        AuthorizationPanel.setBounds(0, 0, 800, 800);
        AuthorizationPanel.setBackground(BgColor);

        JPanel MainSystem = new JPanel(null);
        MainSystem.setBounds(0, 0, 800, 800);
        MainSystem.setBackground(BgColor);

        JPanel viewTickets = new JPanel(null);
        viewTickets.setBounds(0, 0, 800, 800);
        viewTickets.setBackground(BgColor);

        JScrollPane scrollView = new JScrollPane();
        scrollView.setBounds(0, 150, 785, 600);
        scrollView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollView.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        int CenterX = (mainframe.getWidth() - 200) / 2;

        JTextField loginField = new JTextField("Write your login");
        loginField.setBounds(CenterX, 200, 200, 30);
        loginField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent event) {
                if ("Write your login".equals(loginField.getText())) {
                    loginField.setText("");
                }
            }

            public void focusLost(FocusEvent event) {
                if (loginField.getText().isEmpty()) {
                    loginField.setText("Write your login");
                }
            }
        });

        JTextField passwordField = new JTextField("Write your password");
        passwordField.setBounds(CenterX, 300, 200, 30);
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent event) {
                if ("Write your password".equals(passwordField.getText())) {
                    passwordField.setText("");
                }
            }

            public void focusLost(FocusEvent event) {
                if (passwordField.getText().isEmpty()) {
                    passwordField.setText("Write your password");
                }
            }
        });

        JButton startButton = new JButton("Log In");
        startButton.setBounds(CenterX, 400, 200, 70);
        startButton.setBackground(new Color(202, 220, 252));
        startButton.addActionListener(event -> {
            String login = loginField.getText();
            String password = passwordField.getText();

            if ("a".equals(login) && "a".equals(password)) {
                adminrules = true;
            } else {
                loginname = login;
            }
            goBackB(AuthorizationPanel, MainSystem, viewTickets, false, true, false);
        });

        JButton viewButton = new JButton("View Tickets");
        viewButton.setBounds(CenterX, 500, 150, 80);
        viewButton.setBackground(new Color(202, 220, 252));
        viewButton.addActionListener(event -> pressedView(viewTickets, scrollView, MainSystem, AuthorizationPanel, CenterX));

        JButton ButtonBack = new JButton("Go Back");
        ButtonBack.setBounds(CenterX, 50, 150, 80);
        ButtonBack.setBackground(new Color(202, 220, 252));
        ButtonBack.addActionListener(event -> goBackB(AuthorizationPanel, MainSystem, viewTickets, true, false, false));

        AuthorizationPanel.add(loginField);
        AuthorizationPanel.add(passwordField);
        AuthorizationPanel.add(startButton);

        MainSystem.add(ButtonBack);
        MainSystem.add(viewButton);

        mainframe.add(AuthorizationPanel);
        mainframe.add(MainSystem);
        mainframe.add(viewTickets);
        mainframe.setVisible(true);
    }
}
