package dbms1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class DBHostel {
    // Declaring connection variables
    static Connection conn;

    // Initialize method to initialize the database with StudentData table
    public static void dbInit() {
        try {
            // Replace with your database details
            String url = "jdbc:mysql://localhost:3306/*YOUR DB NAME*";
            String user = "*YOUR USERNAME*";
            String password = "*YOUR PASSWORD*";
            conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();
            // SQL query to create StudentData table
            String query;
            query = "CREATE TABLE IF NOT EXISTS StudentData ( "
                    + "Student_id VARCHAR(255) PRIMARY KEY,"
                    + "Student_name VARCHAR(255),"
                    + "department VARCHAR(255),"
                    + "Student_joinDate DATE,"
                    + "Student_gender VARCHAR(10),"
                    + "Student_contact VARCHAR(255),"
                    + "Parent_contact VARCHAR(255),"
                    + "Student_email VARCHAR(255),"
                    + "Parent_email VARCHAR(255),"
                    + "Student_address VARCHAR(255)"
                    + ");";
            // Executing the query using statement variable
            stmt.executeUpdate(query);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    // Function to add the StudentData into the database
    protected static void insertStudentData(String id, String name, String department,
                                             String joinDate, String gender, String contact,
                                             String parentcontact, String email,String parentemail, String address) throws SQLException {
        String query = "INSERT INTO StudentData (Student_id, Student_name, department,"
                + "Student_joinDate, Student_gender, Student_contact, Parent_contact, Student_email, Parent_email, Student_address) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hosteljava", "root", "password");
        var pstmt = conn.prepareStatement(query);
        pstmt.setString(1, id);
        pstmt.setString(2, name);
        pstmt.setString(3, department);
        pstmt.setString(4, joinDate);
        pstmt.setString(5, gender);
        pstmt.setString(6, contact);
        pstmt.setString(7, parentcontact);
        pstmt.setString(8, email);
        pstmt.setString(9, parentemail);
        pstmt.setString(10, address);
        
        pstmt.executeUpdate();
        conn.close();
    }

    // Function to update the StudentData data using the id
    protected static void updateStudentData(String id, String name, String department,
                                             String joinDate, String gender, String contact,
                                             String parentcontact, String email,String parentemail, String address) throws SQLException {
        String query = "UPDATE StudentData SET "
                + "Student_name = ?, department = ?, Student_joinDate = ?, Student_gender = ?,"
                + "Student_contact = ?, Parent_contact = ?, Student_email = ?, "
                + "Parent_email = ?, Student_address = ? WHERE Student_id = ?";

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hosteljava", "root", "password");
        var pstmt = conn.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, department);
        pstmt.setString(3, contact);
        pstmt.setString(4, parentcontact);
        pstmt.setString(5, joinDate);
        pstmt.setString(6, gender);
        pstmt.setString(7, email);
        pstmt.setString(8, parentemail);
        pstmt.setString(9, address);
        pstmt.setString(10, id);
        
        pstmt.executeUpdate();
        conn.close();
    }

    // Function to delete the StudentData from the database
    protected static void deleteStudentData(String id) throws SQLException {
        String query = "DELETE FROM StudentData WHERE Student_id = ?";

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hosteljava", "root", "password");
        var pstmt = conn.prepareStatement(query);
        pstmt.setString(1, id);
        
        pstmt.executeUpdate();
        conn.close();
    }

    // Function that searches the StudentData in the database and updates the values using table model
    public static void searchStudentData(DefaultTableModel model, String searchTerm, String column) throws SQLException {
        model.setRowCount(0);
        String query = "SELECT * FROM StudentData WHERE " + column + " LIKE ?";
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hosteljava", "root", "password");
        var pstmt = conn.prepareStatement(query);
        pstmt.setString(1, "%" + searchTerm + "%");
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            String id = rs.getString("Student_id");
            String name = rs.getString("Student_name");
            String department = rs.getString("department");
            String joinDate = rs.getString("Student_joinDate");
            String gender = rs.getString("Student_gender");
            String contact = rs.getString("Student_contact");
            String parentcontact = rs.getString("Parent_contact");
            String email = rs.getString("Student_email");
            String parentemail = rs.getString("Parent_email");
            String address = rs.getString("Student_address");

            model.addRow(new Object[]{id, name, department, joinDate, gender, contact, parentcontact,email, parentemail, address});
        }
        
        conn.close();
        rs.close();
    }

    // Function to fetch the data and add it to the model so that the JTable is updated
    public static void loadData(DefaultTableModel model) throws SQLException {
        model.setRowCount(0);
        String query = "SELECT * FROM StudentData";
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hosteljava", "root", "password");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            
            try {
    if (rs.findColumn("Parent_contact") != -1) {
        String parentcontact = rs.getString("Parent_contact");
    } else {
        System.out.println("Column 'Parent_contact' not found.");
    }
} catch (SQLException e) {
    System.out.println("Error accessing 'Parent_contact' column: " + e.getMessage());
}

            String id = rs.getString("Student_id");
            String name = rs.getString("Student_name");
            String department = rs.getString("department");
            String joinDate = rs.getString("Student_joinDate");
            String gender = rs.getString("Student_gender");
            String contact = rs.getString("Student_contact");
            String parentcontact = rs.getString("Parent_contact");
            String email = rs.getString("Student_email");
            String parentemail = rs.getString("Parent_email");
            String address = rs.getString("Student_address");

            model.addRow(new Object[]{id, name, department, joinDate, gender, contact, parentcontact, email, parentemail, address});
        }
        
        conn.close();
        rs.close();
    }
}
