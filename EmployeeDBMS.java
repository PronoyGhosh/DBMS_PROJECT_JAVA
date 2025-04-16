package dbms1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.sql.SQLException;

public class EmployeeDBMS {

  JFrame frmEmpManagementSystem;
  private JTextField nameTextField;
  private JTextField contactTextField;
  private JTextField emailTextField;
  private JTextField idTextField;
  private JTextField joinDateTextField;
  private JTable table;
  private JTextField deletetextfield;
  private JTextField searchTextField;
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
      
       EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          DBEmployee.dbInitEmp();
          EmployeeDBMS window = new EmployeeDBMS();
          window.frmEmpManagementSystem.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    
  }

  /**
   * Create the application.
   */
  public EmployeeDBMS() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  public void initialize() {
//    Creating new frame for the components
    frmEmpManagementSystem = new JFrame();
    frmEmpManagementSystem.setTitle("Student Details Portal");
    frmEmpManagementSystem.setBounds(100, 100, 1100, 700);
    frmEmpManagementSystem.setResizable(false);
    frmEmpManagementSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmEmpManagementSystem.getContentPane();

//    creating the table model for jtable
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Employee No.");
    model.addColumn("Name");
    model.addColumn("Joining Date");
    model.addColumn("Gender");
    model.addColumn("Contact");
    model.addColumn("Salary");
    
//    Adding the UI components
    JPanel inputPanel = new JPanel();
    inputPanel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
    inputPanel.setBounds(0, 0, 412, 680);
    inputPanel.setBackground(Color.WHITE);
    inputPanel.setLayout(null);
    
    JLabel idLabel = new JLabel("Employee No");
    idLabel.setBounds(22, 197, 196, 27);
    inputPanel.add(idLabel);
    
    idTextField = new JTextField();
    idTextField.setBounds(206, 197, 196, 27);
    idTextField.setHorizontalAlignment(SwingConstants.LEFT);
    inputPanel.add(idTextField);
    
    JLabel namelabel = new JLabel("Employee Name");
    namelabel.setBounds(22, 231, 196, 27);
    inputPanel.add(namelabel);
    
    nameTextField = new JTextField();
    nameTextField.setBounds(206, 231, 196, 27);
    nameTextField.setToolTipText("");
    nameTextField.setHorizontalAlignment(SwingConstants.LEFT);
    inputPanel.add(nameTextField);
    
    JLabel joinDateLabel = new JLabel("Joining Date");
    joinDateLabel.setBounds(22, 299, 196, 27);
    inputPanel.add(joinDateLabel);
    
    joinDateTextField = new JTextField();
    joinDateTextField.setBounds(206, 299, 196, 27);
    joinDateTextField.setHorizontalAlignment(SwingConstants.LEFT);
    inputPanel.add(joinDateTextField);
    
    JLabel genderLabel = new JLabel("Gender");
    genderLabel.setBounds(22, 333, 196, 27);
    inputPanel.add(genderLabel);
    
    JComboBox<String> genderComboBox = new JComboBox<String>();
    genderComboBox.setBounds(206, 333, 196, 27);
    genderComboBox.setEditable(false);
    genderComboBox.setMaximumRowCount(3);
    genderComboBox.addItem("Male");
    genderComboBox.addItem("Female");
    genderComboBox.addItem("Other");
    frmEmpManagementSystem.getContentPane().setLayout(null);
    inputPanel.add(genderComboBox);
    
    JLabel contactLabel = new JLabel("Contact");
    contactLabel.setBounds(22, 367, 196, 27);
    inputPanel.add(contactLabel);
    
    contactTextField = new JTextField();
    contactTextField.setBounds(206, 367, 196, 27);
    contactTextField.setHorizontalAlignment(SwingConstants.LEFT);
    inputPanel.add(contactTextField);
    
    JLabel emailLabel = new JLabel("Salary");
    emailLabel.setBounds(22, 440, 196, 27);
    inputPanel.add(emailLabel);
    
    emailTextField = new JTextField();
    emailTextField.setBounds(206, 440, 196, 27);
    emailTextField.setHorizontalAlignment(SwingConstants.LEFT);
    inputPanel.add(emailTextField);
    
    frmEmpManagementSystem.getContentPane().add(inputPanel);
    
//    Adding the insert button action listener to insert the Student on click
    JButton insertButton = new JButton("Insert");
    insertButton.setBounds(12, 550, 196, 27);
    inputPanel.add(insertButton);
    insertButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          DBEmployee.insertEmpData(idTextField.getText(), 
                       nameTextField.getText(),   
                       joinDateTextField.getText(),
                       genderComboBox.getSelectedItem().toString(),
                       contactTextField.getText(),
                       emailTextField.getText());
          DBEmployee.loadData(model);
          JOptionPane.showMessageDialog(inputPanel,"Student successfully inserted","Inserted", JOptionPane.INFORMATION_MESSAGE);

          
        } catch (Exception e2) {
          JOptionPane.showMessageDialog(inputPanel,"Student ID Already exists","ERROR", JOptionPane.ERROR_MESSAGE);
          e2.printStackTrace();
        }
      }
    });
    
//    Adding the update button action listener to update the Student data on click
    JButton updateButton = new JButton("Update");
    updateButton.setBounds(206, 550, 196, 27);
    inputPanel.add(updateButton);
    
    // Create the first JTextArea
JTextArea titleText1 = new JTextArea();
titleText1.setEditable(false);
titleText1.setLineWrap(true);
titleText1.setWrapStyleWord(true);
titleText1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 29));
titleText1.setBackground(Color.WHITE);
titleText1.setText("Employee DETAILS PORTAL");
titleText1.setBounds(12, 17, 388, 50); // Adjust height for the first text box
inputPanel.add(titleText1);

// Create the second JTextArea
JTextArea titleText2 = new JTextArea();
titleText2.setEditable(false);
titleText2.setLineWrap(true);
titleText2.setWrapStyleWord(true);
titleText2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
titleText2.setBackground(Color.WHITE);
titleText2.setText("""
                     By:
                     Mohammad Kaif RA2311003011022  
                     Pronoy Ghosh RA2311003011035 """);
titleText2.setBounds(12, 70, 388, 140); 
inputPanel.add(titleText2);

    updateButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
      
        try {
          DBEmployee.updateEmpData(idTextField.getText(), 
                 nameTextField.getText(),   
                 joinDateTextField.getText(),
                 genderComboBox.getSelectedItem().toString(),
                 contactTextField.getText(),
                 emailTextField.getText());
          DBEmployee.loadData(model);
          JOptionPane.showMessageDialog(inputPanel,"Student successfully Updated","Updated", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e2) {
          
          e2.printStackTrace();
        }
      }
    });
    
    JPanel outputPanel = new JPanel();
    outputPanel.setBounds(413, 0, 687, 680);
    outputPanel.setBackground(Color.WHITE);
    outputPanel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
    frmEmpManagementSystem.getContentPane().add(outputPanel);
    
    table = new JTable(model);
    table.setBackground(Color.WHITE);
    table.setVisible(true);

    outputPanel.setLayout(null);
    
    
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds(12, 143, 663, 410);
    scrollPane.setViewportBorder(null);
    outputPanel.add(scrollPane);
    
    JButton loadButton = new JButton("Load Data");
    loadButton.setBounds(351, 12, 117, 25);
    outputPanel.add(loadButton);
    loadButton.addActionListener(new ActionListener() {
          
          @Override
          public void actionPerformed(ActionEvent e) {
            
            try {
              DBEmployee.loadData(model);
            } catch (SQLException e2) {
              
              e2.printStackTrace();
            }
          }
        });
    
//    Adding the delete button action listener to delete the Student on click
    JButton deleteButton = new JButton("Delete");
    deleteButton.setBounds(12, 12, 117, 25);
    outputPanel.add(deleteButton);
    deleteButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {

        try {
          DBEmployee.deleteStudentData(deletetextfield.getText());
          DBEmployee.loadData(model);
          JOptionPane.showMessageDialog(outputPanel,"Student successfully deleted","Deleted", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e2) {
          
          e2.printStackTrace();
        }
      }
    });
    
    deletetextfield = new JTextField();
    deletetextfield.setBounds(154, 12, 185, 25);
    outputPanel.add(deletetextfield);
    deletetextfield.setColumns(10);
    
    ButtonGroup bg = new ButtonGroup();
    
    JRadioButton nameRadio = new JRadioButton("Name");
    nameRadio.setBackground(Color.WHITE);
    nameRadio.setBounds(115, 100, 75, 23);
    outputPanel.add(nameRadio);
    
    JRadioButton idRadio = new JRadioButton("Reg. ID");
    idRadio.setBackground(Color.WHITE);
    idRadio.setBounds(305, 100, 75, 23);
    outputPanel.add(idRadio);
    
    JRadioButton emailRadio = new JRadioButton("Salary");
    emailRadio.setBackground(Color.WHITE);
    emailRadio.setBounds(495, 100, 75, 23);
    outputPanel.add(emailRadio);
    
    bg.add(nameRadio);
    bg.add(idRadio);
    bg.add(emailRadio);

    //    Adding the search button action listener to search the Student on click
    JButton searchButton = new JButton("Search");
    searchButton.setBounds(12, 62, 117, 25);
    outputPanel.add(searchButton);
    searchButton.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
          try {
            
            if (nameRadio.isSelected()) {
              
              DBEmployee.searchStudentData(model,searchTextField.getText(),"emp_name" );
              
            }else if (idRadio.isSelected()) {
              
              DBEmployee.searchStudentData(model,searchTextField.getText(),"emp_id" );
              
            }else if(emailRadio.isSelected()) {
              DBEmployee.searchStudentData(model,searchTextField.getText(),"emp_email" );
            }else {
              JOptionPane.showMessageDialog(outputPanel,"Please Select a field to search","ERROR", JOptionPane.ERROR_MESSAGE);

            }
            
            
          } catch (Exception e2) {
            JOptionPane.showMessageDialog(outputPanel,"NOT Found","ERROR", JOptionPane.ERROR_MESSAGE);
            e2.printStackTrace();
          }
      }
    });
    
    searchTextField = new JTextField();
    searchTextField.setColumns(10);
    searchTextField.setBounds(154, 62, 185, 25);
    outputPanel.add(searchTextField);
    
    JLabel searchBy = new JLabel("Search By:");
    searchBy.setBounds(12, 99, 98, 25);
    outputPanel.add(searchBy);
  
  }
}