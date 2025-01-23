import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class AddTicket {

    public static void addTicket(JPanel AddTicketPanel, JPanel viewTickets, JScrollPane scrollView, JPanel MainSystem, JPanel AuthorizationPanel, int CenterX, Color BgColor) {
        viewTickets.setVisible(false);
        MainSystem.setVisible(false);
        AuthorizationPanel.setVisible(false);

        String[] from = {"Warsaw", "Krakow", "Lodz", "Wroclaw", "Poznan", "Gdansk", "Szczecin", "Bydgoszcz", "Lublin", "Katowice"};
        JComboBox<String> fromBox = new JComboBox<>(from);
        fromBox.setBounds(CenterX, 140, 230, 25);
        fromBox.setBackground(new Color(202, 220, 252));

        String[] to = {"Warsaw", "Krakow", "Lodz", "Wroclaw", "Poznan", "Gdansk", "Szczecin", "Bydgoszcz", "Lublin", "Katowice"};
        JComboBox<String> toBox = new JComboBox<>(to);
        toBox.setBounds(CenterX, 180, 230, 25);
        toBox.setBackground(new Color(202, 220, 252));

        String[] seat = {"S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "S11", "S12", "S13", "S14"};
        JComboBox<String> seatBox = new JComboBox<>(seat);
        seatBox.setBounds(CenterX, 220, 230, 25);
        seatBox.setBackground(new Color(202, 220, 252));

        String[] classS = {"Economy", "Business", "First Class"};
        JComboBox<String> classBox = new JComboBox<>(classS);
        classBox.setBounds(CenterX, 260, 230, 25);
        classBox.setBackground(new Color(202, 220, 252));

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(CenterX, 300, 100, 25);
        priceLabel.setForeground(Color.white);

        JTextField priceField = new JTextField();
        priceField.setBounds(CenterX, 320, 230, 25);

        JLabel dateStartLabel = new JLabel("Departure Date (YYYY-MM-DD)");
        dateStartLabel.setBounds(CenterX, 360, 200, 25);
        dateStartLabel.setForeground(Color.white);

        JLabel dateFinishLabel = new JLabel("Arrival Date (YYYY-MM-DD)");
        dateFinishLabel.setBounds(CenterX, 400, 200, 25);
        dateFinishLabel.setForeground(Color.white);

        JTextField dateStartField = new JTextField();
        dateStartField.setBounds(CenterX, 380, 230, 25);

        JTextField dateFinishField = new JTextField();
        dateFinishField.setBounds(CenterX, 420, 230, 25);

        JLabel timeLabel = new JLabel("Time");
        timeLabel.setBounds(CenterX, 460, 100, 25);
        timeLabel.setForeground(Color.white);

        String[] Time = {
            "00:00", "00:15", "00:30", "00:45", "01:00", "01:15", "01:30", "01:45",
            "02:00", "02:15", "02:30", "02:45", "03:00", "03:15", "03:30", "03:45",
            "04:00", "04:15", "04:30", "04:45", "05:00", "05:15", "05:30", "05:45",
            "06:00", "06:15", "06:30", "06:45", "07:00", "07:15", "07:30", "07:45",
            "08:00", "08:15", "08:30", "08:45", "09:00", "09:15", "09:30", "09:45",
            "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45",
            "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45",
            "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45",
            "16:00", "16:15", "16:30", "16:45", "17:00", "17:15", "17:30", "17:45",
            "18:00", "18:15", "18:30", "18:45", "19:00", "19:15", "19:30", "19:45",
            "20:00", "20:15", "20:30", "20:45", "21:00", "21:15", "21:30", "21:45",
            "22:00", "22:15", "22:30", "22:45", "23:00", "23:15", "23:30", "23:45"
        };
        JComboBox<String> TimeStartBox = new JComboBox<>(Time);
        TimeStartBox.setBounds(CenterX, 480, 230, 25);
        TimeStartBox.setBackground(new Color(202, 220, 252));

        JComboBox<String> TimeFinishBox = new JComboBox<>(Time);
        TimeFinishBox.setBounds(CenterX, 520, 230, 25);
        TimeFinishBox.setBackground(new Color(202, 220, 252));

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(CenterX, 560, 230, 25);
        saveButton.setBackground(new Color(202, 220, 252));

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBounds(CenterX, 10, 230, 25);
        goBackButton.setBackground(new Color(202, 220, 252));
        goBackButton.addActionListener(event -> {
            AddTicketPanel.setVisible(false);
            Main.goBackB(AuthorizationPanel, MainSystem, viewTickets, false, true, false, false);
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String from = (String) fromBox.getSelectedItem();
                String to = (String) toBox.getSelectedItem();
                String seat = (String) seatBox.getSelectedItem();
                String priceClass = (String) classBox.getSelectedItem();
                String timeStart = (String) TimeStartBox.getSelectedItem();
                String timeFinish = (String) TimeFinishBox.getSelectedItem();
                String dateStart = dateStartField.getText();
                String dateFinish = dateFinishField.getText();
                String priceStr = priceField.getText();

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);
                try {
                    dateFormat.parse(dateStart);
                    dateFormat.parse(dateFinish);

                    java.util.Date startDate = dateFormat.parse(dateStart);
                    java.util.Date finishDate = dateFormat.parse(dateFinish);

                    if (finishDate.before(startDate)) {
                        JOptionPane.showMessageDialog(AddTicketPanel, "Arrival date cannot be earlier than the departure date.");
                        return;
                    }
        
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(AddTicketPanel, "Invalid date format. Please use YYYY-MM-DD.");
                    return;
                }

                if (from.equals(to)) {
                    JOptionPane.showMessageDialog(AddTicketPanel, "Departure and arrival places cannot be the same.");
                    return;
                }

                int price;
                try {
                    price = Integer.parseInt(priceStr);
                    if (price <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddTicketPanel, "Invalid price");
                    return;
                }

                String ticketData = from + "-" + to + ", " + dateStart + "(" + timeStart + ") - " + dateFinish + "(" + timeFinish + "), " + priceClass + ", " + seat + ", " + price + "\n";
                
                //String adm = "" + Main.adminrules;
                Main.client.send_text("addticket" +"::" + ticketData);
                //WriteFile.writeFile("C:\\Users\\Rinat\\Documents\\University\\YEAR 2\\OOP 2 Project\\Game\\Game\\tickets\\tickets.txt", ticketData, true);
                JOptionPane.showMessageDialog(AddTicketPanel, "Ticket saved successfully!");

            }
        });

        AddTicketPanel.add(fromBox);
        AddTicketPanel.add(toBox);
        AddTicketPanel.add(seatBox);
        AddTicketPanel.add(classBox);
        AddTicketPanel.add(priceLabel);
        AddTicketPanel.add(priceField);
        AddTicketPanel.add(dateStartLabel);
        AddTicketPanel.add(dateFinishLabel);
        AddTicketPanel.add(dateStartField);
        AddTicketPanel.add(dateFinishField);
        AddTicketPanel.add(timeLabel);
        AddTicketPanel.add(TimeStartBox);
        AddTicketPanel.add(TimeFinishBox);
        AddTicketPanel.add(saveButton);
        AddTicketPanel.add(goBackButton);

        AddTicketPanel.setVisible(true);
        AddTicketPanel.revalidate();
        AddTicketPanel.repaint();
    }
}
