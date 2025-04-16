package dbms1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DBMS1 {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Hostel Management System Window");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the button
        JButton button = new JButton("Employee Management Portal");
        JButton button1 = new JButton("Hostel Management Portal");
        // Add this line inside main, along with other buttons
        JButton button3 = new JButton("View Details Table");



// Add this to the layout
button3.setBounds(100, 230, 200, 50);
frame.add(button3);

        
        // Add ActionListener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When button is clicked, open the new class (e.g., another window)
                LoginEmployee window = new LoginEmployee();
                window.setVisible(true);
                frame.setVisible(false);  // Hide the current frame
            }
        });
        
         button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When button is clicked, open the new class (e.g., another window)
                LoginStudent window = new LoginStudent();
                window.setVisible(true);
                frame.setVisible(false);  // Hide the current frame
            }
        });
         
         button3.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                    MessTable tableWindow = new MessTable();  // Open table window
                    tableWindow.setVisible(true);
                    frame.setVisible(false);  // Optional: hide the main window
                }
            });
         
         

        // Add button to frame and set visibility
        frame.add(button);
        frame.add(button1);
        frame.setLayout(null);
        button.setBounds(100, 50, 200, 50);
        button1.setBounds(100, 110, 200, 50);// Position the button
        button3.setBounds(100, 170, 200, 50);
        frame.add(button3);
        frame.setVisible(true);
    }
}
