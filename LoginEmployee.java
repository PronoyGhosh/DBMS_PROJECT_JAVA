package dbms1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginEmployee extends JFrame {

    // Pre-stored username and password for simplicity (in real applications, these would be stored in a database)
    private static final String STORED_USERNAME = "*YOUR USERNAME*";
    private static final String STORED_PASSWORD = "*YOUR PASSWORD*";

    // Declare components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;

    public LoginEmployee() {
        // Set up the frame
        setTitle("Login Page");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        // Set layout manager
        setLayout(new GridLayout(4, 2));

        // Create UI components
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        messageLabel = new JLabel("", JLabel.CENTER);

        // Add components to the frame
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Empty space
        add(loginButton);
        add(messageLabel);

        // Add button action listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Validate login credentials
                if (validateLogin(username, password)) {
                    messageLabel.setText("Login Successful!");
                // When button is clicked, open the new class (e.g., another window)
                EmployeeDBMS window = new EmployeeDBMS();
                window.frmEmpManagementSystem.setVisible(true);
                setVisible(false);  // Hide the current frame
            }// Hide the current frame
            
                    
                 else {
                    messageLabel.setText("Invalid username or password.");
                    messageLabel.setForeground(Color.RED);
                }
            }
        });
    }

    // Method to validate username and password
    private boolean validateLogin(String username, String password) {
        return STORED_USERNAME.equals(username) && STORED_PASSWORD.equals(password);
    }

    // Main method to run the login frame
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginEmployee LoginEmployee = new LoginEmployee();
                LoginEmployee.setVisible(true);
            }
        });
    }
}
