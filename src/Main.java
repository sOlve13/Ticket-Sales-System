import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;


public class Main {  
    static boolean adminrules = false;
    static Color BgColor = new Color(0, 36, 107);
    static String loginname = "";
    static JPanel myTicketPanel;
    static JScrollPane myScroll;
    static Client client = new Client();
    static String adm = "false";

   
    public static void goBackB(JPanel AuthorizationPanel, JPanel MainSystem, JPanel viewTickets, boolean AP, boolean  MS, boolean VT, boolean UT){
        if(AP == true){
            adminrules = false;
            adm = "false";
        }
        MainSystem.repaint();
        AuthorizationPanel.repaint();
        viewTickets.repaint();
        MainSystem.revalidate();
        AuthorizationPanel.revalidate();
        viewTickets.revalidate();

        MainSystem.setVisible(MS);
        AuthorizationPanel.setVisible(AP);
        viewTickets.setVisible(VT);
        myTicketPanel.setVisible(UT);

        MainSystem.repaint();
        AuthorizationPanel.repaint();
        viewTickets.repaint();
    }
/*
public static boolean isAlreadyBoughtTicket(String[] boughtTickets, String selectedTicket){
    for (int i = 0; i < boughtTickets.length; i++) {
        if (selectedTicket.equals(boughtTickets[i].trim())) {
            return true;
        }
    }
    return  false;
}*/

    public static void main(String[] args) {  
        JFrame mainframe = new JFrame();
        mainframe.setSize(800,800);
        mainframe.setTitle("Ticket Selling System");
        mainframe.setLayout(null); 
        mainframe.setResizable(false);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.getContentPane().setBackground(new Color(39,39,39));

        ImageIcon img = new ImageIcon("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\bin\\icon.png");
        mainframe.setIconImage(img.getImage());

        

        

        JPanel AuthorizationPanel = new JPanel();
        AuthorizationPanel.setLayout(null);
        AuthorizationPanel.setBounds(0, 0, 800, 800); 
        AuthorizationPanel.setBackground(BgColor);
        
        JPanel userTicketInfo = new JPanel();
        userTicketInfo.setLayout(null);
        userTicketInfo.setBounds(0, 0, 800, 800); 
        userTicketInfo.setBackground(BgColor);

        myTicketPanel = new JPanel();
        myTicketPanel.setLayout(null);
        myTicketPanel.setBounds(0, 0, 800, 800); 
        myTicketPanel.setBackground(BgColor);

        JScrollPane myScroll = new JScrollPane();
        myScroll.setBounds(0,150,785,600);
        myScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        myScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel MainSystem = new JPanel();
        MainSystem.setLayout(null);
        MainSystem.setBounds(0, 0, 800, 800); 
        MainSystem.setBackground(BgColor);

        JPanel viewTickets = new JPanel();
        viewTickets.setLayout(null);
        viewTickets.setBounds(0, 0, 800, 800);
        viewTickets.setBackground(BgColor);

        JPanel AddTicketPanel = new JPanel();
        AddTicketPanel.setLayout(null);
        AddTicketPanel.setBounds(0, 0, 800, 800);
        AddTicketPanel.setBackground(BgColor);
        
        JScrollPane scrollView = new JScrollPane();
        scrollView.setBounds(0,150,785,600);
        scrollView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollView.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        Dimension MSpos = mainframe.getSize();
        int CenterX = (MSpos.width - 200)/2;

        JButton ButtonBack = new JButton("Go Back");
        ButtonBack.setBounds(CenterX,50,150,80);
        ButtonBack.setBackground(new Color(202, 220, 252));

        ButtonBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                goBackB(AuthorizationPanel, MainSystem, viewTickets, true, false, false, false);
            }
        });


        JButton startButton = new JButton("Log In");
        startButton.setVisible(true);
        startButton.setBounds(CenterX,400, 200,70);
        startButton.setBackground(new Color(202, 220, 252));

        JScrollPane scrollView2 = new JScrollPane();
        scrollView2.setBounds(0, 150, 785, 600);
        scrollView2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollView2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

//        Button bt = new Button(100, 50, 50, 50, "test", new Color(123,44,56), AuthorizationPanel);
//        bt.createButton();
        

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
        JButton AddButton = new JButton("Add");
        AddButton.setBounds(CenterX, 600, 150, 80);
        AddButton.setBackground(new Color(202, 220, 252));
        AddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                AddTicket.addTicket(AddTicketPanel,viewTickets, scrollView, MainSystem, AuthorizationPanel,CenterX, BgColor);
            }
        }); 
        
        

        
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String login = loginField.getText();
                String password = passwordField.getText();

                //String adm = "" + adminrules;
                client.send_text(adm + "::" + "login" + "::" + login + "::" + password);
                //client.get_bool();
                if(client.get_bool()){
                    adminrules = true;
                    adm = "true";
                }
                //String[] test = client.get_free_tickets();

                /*try {
                
                Socket cs = new Socket("localhost", 2555);
                DataInputStream dis = new DataInputStream(cs.getInputStream());
                DataOutputStream dos = new DataOutputStream(cs.getOutputStream());
                //objectinputsream
                String comb = login + "::" + password;
                dos.writeUTF(comb);
                dos.flush();
                String input = (String)dis.readUTF();
                System.out.println(input);
                if("1".equals(input)){
                    adminrules = true;
                }
                System.out.println("wrote");
                cs.close();

                } catch (Exception e) {
                }*/
                //

                if(login.isEmpty() || "Write your login".equals(login)){
                    JOptionPane.showMessageDialog(mainframe, "Invalid login");
                }else{
                if ("admin".equals(login) && "admin".equals(password)) {
                        //adminrules = true;
                }
                else{
                        loginname = login;
                        adminrules = false;
                }
                if(adminrules == true){
                    MainSystem.add(AddButton);
                    AddButton.setVisible(true);
                }else{
                    AddButton.setVisible(false);
                }
                AuthorizationPanel.setVisible(false);
                MainSystem.setVisible(true);
                loginField.setText("Write your login");
                passwordField.setText("Write your password");
                
                AuthorizationPanel.revalidate();
                MainSystem.revalidate();
                MainSystem.repaint();
                AuthorizationPanel.repaint();
                }
                
            }
        });


        JButton viewButton = new JButton("View Tickets");
        viewButton.setBounds(CenterX, 500, 150, 80);
        viewButton.setBackground(new Color(202, 220, 252));
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                ViewTicket.pressedView(viewTickets, scrollView, MainSystem, AuthorizationPanel, CenterX, BgColor, myTicketPanel, myScroll);
            }
        }); 


        
        mainframe.add(userTicketInfo);
        mainframe.add(AddTicketPanel);
        AuthorizationPanel.add(loginField);
        AuthorizationPanel.add(passwordField);
        AuthorizationPanel.add(startButton);
        MainSystem.add(ButtonBack);
        MainSystem.add(viewButton);
        AddTicketPanel.setVisible(false);
        myTicketPanel.setVisible(false);
        AuthorizationPanel.setVisible(true);
        MainSystem.setVisible(false);
        viewTickets.setVisible(false);
        userTicketInfo.setVisible(false);
        mainframe.add(AuthorizationPanel);
        mainframe.add(MainSystem);
        mainframe.add(viewTickets);
        mainframe.add(scrollView);
        mainframe.add(myTicketPanel);
        mainframe.setVisible(true);
        
        

        
    }  
}

