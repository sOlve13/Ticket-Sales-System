import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;

public class Main {  
    static boolean swtich = true;
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
        AuthorizationPanel.setBackground(new Color(0, 36, 107));


        JPanel MainSystem = new JPanel();
        MainSystem.setLayout(null);
        MainSystem.setBounds(0, 0, 800, 800); 
        MainSystem.setBackground(new Color(0, 36, 107));


        JButton ButtonMain = new JButton("Go Back");
        ButtonMain.setBounds(50,50,200,200);
        ButtonMain.setBackground(new Color(202, 220, 252));
        ButtonMain.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event){
                if(swtich == false){
                    AuthorizationPanel.setVisible(true);
                    MainSystem.setVisible(false);
                }else{
                    AuthorizationPanel.setVisible(false);
                    MainSystem.setVisible(true);
                }
                swtich = !swtich;
                System.out.println(swtich);
            }
        });


        JButton Button1 = new JButton();
        Button1.setVisible(true);
        Button1.setBounds(50,300, 200,70);
        Button1.setBackground(new Color(202, 220, 252));


        JTextField loginField = new JTextField("Write your login");
        loginField.setBounds(50,100, 200,30); 
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
        passwordField.setBounds(50,200, 200,30); 

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
                    if(swtich == true){
                        AuthorizationPanel.setVisible(false);
                        MainSystem.setVisible(true);
                    }else{
                        AuthorizationPanel.setVisible(true);
                        MainSystem.setVisible(false);
                    }
                    /*mainframe.setVisible(false);
                    mainframe.dispose();
                    JFrame newWindow = new JFrame();
                    newWindow.setSize(800, 800);
                    newWindow.setLayout(null); 
                    newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    JLabel messageLabel = new JLabel("Hello World", SwingConstants.CENTER);
                    messageLabel.setBounds(50, 50, 200, 50);
                    newWindow.add(messageLabel);
                    newWindow.setVisible(true);*/
                    
                } else {
                    JOptionPane.showMessageDialog(mainframe, "Invalid login or password.");
                }
                swtich = !swtich;
                System.out.println(swtich);
            }
        });



  



        AuthorizationPanel.add(loginField);
        AuthorizationPanel.add(passwordField);
        AuthorizationPanel.add(Button1);
        MainSystem.add(ButtonMain);
        AuthorizationPanel.setVisible(swtich);
        MainSystem.setVisible(!swtich);
        mainframe.add(AuthorizationPanel);
        mainframe.add(MainSystem);
        mainframe.setVisible(true);
    }  
}


