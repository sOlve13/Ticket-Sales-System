import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Button {
    private int width;
    private int x;
    private int y;
    private int height;
    private String name;
    private Color color;
    private JPanel container;
    private JButton mainButton;

    public Button(int width, int x, int y, int height, String name, Color color, JPanel container) {
        this.width = width;
        this.x = x;
        this.y = y;
        this.height = height;
        this.name = name;
        this.color = color;
        this.container = container;
    }

    public JButton createButton() {
        JButton mainButton = new JButton(name);
        mainButton.setBounds(x, y, width, height);
        mainButton.setBackground(color);
        this.mainButton = mainButton;
        container.add(mainButton);
        return mainButton;
    }
    public JButton setAction(){
        return mainButton;
    }

    public void goBack(JPanel authorizationPanel, JPanel mainSystem, JPanel viewTickets, 
                       boolean showAuthPanel, boolean showMainSystem, boolean showViewTickets) {
        mainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                authorizationPanel.setVisible(showAuthPanel);
                mainSystem.setVisible(showMainSystem);
                viewTickets.setVisible(showViewTickets);

                authorizationPanel.repaint();
                mainSystem.repaint();
                viewTickets.repaint();
            }
        });
    }

    public void pressedView(){}
}
