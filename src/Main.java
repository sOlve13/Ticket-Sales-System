import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import javax.swing.*;


public class Main {  
    static boolean swtich = true;
    static Color BgColor = new Color(0, 36, 107);
    public static String readFile(String filePath){
        String data1 = "";
        try{
        data1 = new String(Files.readAllBytes(Paths.get("C:/Users/Rinat/Documents/University/YEAR 2/OOP 2 Project/Game/Game/tickets/tickets.txt")));
        }
        catch(IOException e){
            System.out.println("File was not read");
        }
        System.out.println(data1);
        return data1;

    }
    public static void appendFile(String filePath, String text){
        try {
            FileWriter textapp = new FileWriter(filePath, true);
            textapp.write(text);
            textapp.close();
        } catch (Exception e) {
            System.out.println("Couldn't open the file");
        }
    }

    public static void goBackB(JPanel AuthorizationPanel, JPanel MainSystem, JPanel viewTickets, boolean AP, boolean  MS, boolean VT){
        MainSystem.setVisible(MS);
        AuthorizationPanel.setVisible(AP);
        viewTickets.setVisible(VT);

        MainSystem.repaint();
        AuthorizationPanel.repaint();
        viewTickets.repaint();

    }
    public static void pressedView(JPanel viewTickets, JScrollPane scrollView, JPanel MainSystem, JPanel AuthorizationPanel, int CenterX){
        viewTickets.setVisible(true);
        MainSystem.setVisible(false);
        AuthorizationPanel.setVisible(false);


        JTextArea ticketsArea = new JTextArea(readFile("C:/Users/Rinat/Documents/University/YEAR 2/OOP 2 Project/Game/Game/tickets/tickets.txt"));
        ticketsArea.setEditable(false);
        ticketsArea.setForeground(Color.WHITE);
        ticketsArea.setBackground(BgColor);
        ticketsArea.setLineWrap(true);
        ticketsArea.setWrapStyleWord(true);


        JButton goBack = new JButton("Go Back");
        goBack.setBounds(CenterX, 50, 150, 80);
        goBack.setBackground(new Color(202, 220, 252));
        
        goBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                goBackB(MainSystem, AuthorizationPanel, viewTickets, true, false, false);
            }
        });

        scrollView.setViewportView(ticketsArea); 
        viewTickets.add(goBack);
        viewTickets.add(scrollView);
        


    }
    public static void main(String[] args) {  
        JFrame mainframe = new JFrame();
        mainframe.setSize(800,800);
        mainframe.setTitle("Ticket Selling System");
        mainframe.setLayout(null); 
        mainframe.setResizable(false);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.getContentPane().setBackground(new Color(39,39,39));


        JPanel AuthorizationPanel = new JPanel();
        AuthorizationPanel.setLayout(null);
        AuthorizationPanel.setBounds(0, 0, 800, 800); 
        AuthorizationPanel.setBackground(BgColor);


        JPanel MainSystem = new JPanel();
        MainSystem.setLayout(null);
        MainSystem.setBounds(0, 0, 800, 800); 
        MainSystem.setBackground(BgColor);

        JPanel viewTickets = new JPanel();
        viewTickets.setLayout(null);
        viewTickets.setBounds(0, 0, 800, 800);
        viewTickets.setBackground(BgColor);
        
        JScrollPane scrollView = new JScrollPane();
        scrollView.setBounds(0,150,800,775);
        scrollView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollView.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        Dimension MSpos = mainframe.getSize();
        int CenterX = (MSpos.width - 200)/2;

        JButton ButtonBack = new JButton("Go Back");
        ButtonBack.setBounds(CenterX,50,150,80);
        ButtonBack.setBackground(new Color(202, 220, 252));

        ButtonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                goBackB(AuthorizationPanel, MainSystem, viewTickets, true, false, false);
            }
        });


        JButton Button1 = new JButton();
        Button1.setVisible(true);
        Button1.setBounds(CenterX,400, 200,70);
        Button1.setBackground(new Color(202, 220, 252));


        JTextField loginField = new JTextField("Write your login");
        loginField.setBounds(CenterX,200, 200,30); 
        loginField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent event) {
                if (loginField.getText().equals("Write your login")) {
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
        passwordField.setBounds(CenterX, 300, 200,30); 

        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent event){
                if(passwordField.getText().equals("Write your password")){
                    passwordField.setText("");
                }
            }
            public void focusLost(FocusEvent event){
                if(passwordField.getText().isEmpty()){
                    passwordField.setText("Write your password");
                }
            }
        });

        Button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String login = loginField.getText();
                String password = passwordField.getText();
                
                if ("a".equals(login) && "a".equals(password)) {
                        AuthorizationPanel.setVisible(false);
                        MainSystem.setVisible(true);
                        loginField.setText("Write your login");
                        passwordField.setText("Write your password");


                } else {
                    JOptionPane.showMessageDialog(mainframe, "Invalid login or password.");
                    loginField.setText("Write your login");
                    passwordField.setText("Write your password");
                }
                System.out.println(swtich);
                AuthorizationPanel.revalidate();
                MainSystem.revalidate();
                MainSystem.repaint();
                AuthorizationPanel.repaint();
                
            }
        });


        JButton viewButton = new JButton("View Tickets");
        viewButton.setBounds(CenterX, 500, 150, 80);
        viewButton.setBackground(new Color(202, 220, 252));
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                pressedView(viewTickets, scrollView, MainSystem, AuthorizationPanel, CenterX);
            }
        });

        AuthorizationPanel.add(loginField);
        AuthorizationPanel.add(passwordField);
        AuthorizationPanel.add(Button1);
        MainSystem.add(ButtonBack);
        MainSystem.add(viewButton);
        AuthorizationPanel.setVisible(swtich);
        mainframe.add(AuthorizationPanel);
        mainframe.add(MainSystem);
        mainframe.add(viewTickets);
        mainframe.setVisible(true);
        


        
    }  
}


