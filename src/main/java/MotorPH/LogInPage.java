package MotorPH;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInPage extends JFrame implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JLabel label, pwLabel, success;
    private JTextField userText;
    private JPasswordField pwText;
    private JButton button;

    public LogInPage() {
        initializeUI();
        this.setVisible(true);
    }

    private void initializeUI() {
        setTitle("MotorPH Log In");  // Set title for the frame
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        add(panel);  // Add panel directly to 'this' JFrame

        label = new JLabel("User Name:");
        label.setBounds(10, 20, 80, 25);
        panel.add(label);

        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        pwLabel = new JLabel("Password:");
        pwLabel.setBounds(10, 60, 80, 25);
        panel.add(pwLabel);

        pwText = new JPasswordField();
        pwText.setBounds(100, 60, 165, 25);
        panel.add(pwText);

        button = new JButton("Login");
        button.setBounds(135, 90, 80, 25);
        button.addActionListener(this);
        panel.add(button);

        success = new JLabel("");
        success.setBounds(10, 120, 300, 25);
        panel.add(success);
    }

    private boolean authenticateUser(String enteredEmpNo, String enteredPassword) throws IOException, CsvValidationException {
        String filename = "Credentials.csv";
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            reader.readNext(); // Skip headers
            String[] employeeData;
            while ((employeeData = reader.readNext()) != null) {
                if (employeeData[0].equals(enteredEmpNo) && employeeData[2].equals(enteredPassword)) {
                    return true; // Successful authentication
                }
            }
        }
        return false; // No matching credentials found
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String enteredEmpNo = userText.getText();
            String enteredPassword = new String(pwText.getPassword());

            if (authenticateUser(enteredEmpNo, enteredPassword)) {
                // Create the correct Employee type
                Employee employee = Employee.createEmployeeInstance(enteredEmpNo);
                // Set session & open HomePage
                HomePage home = new HomePage(employee);
                employee.setHomePage(home);

                home.setVisible(true);
                this.dispose();
            } else {
                success.setText("Incorrect Login Credentials");
                success.setBounds(92, 120, 300, 25);
            }

        } catch (IOException | CsvValidationException ex) {
            Logger.getLogger(LogInPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
