package dbms1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class DBEmployee {
    // Declaring connection variables
    static Connection conn;

    // Initialize method to initialize the database with StudentData table
    public static void dbInitEmp() {
        try {
            // Replace with your database details
            String url = "jdbc:mysql://localhost:3306/*YOUR DB NAME*";
            String user = "*YOUR USERNAME*";
            String password = "*YOUR PASSWORD*";
            conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();
            // SQL query to create StudentData table
            String query;
            query = "CREATE TABLE IF NOT EXISTS EmpData ( "
                    + "emp_id VARCHAR(255) PRIMARY KEY,"
                    + "emp_name VARCHAR(255),"
                    + "emp_joinDate DATE,"
                    + "emp_gender VARCHAR(10),"
                    + "emp_contact VARCHAR(255),"
                    + "emp_email VARCHAR(255)"
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
    protected static void insertEmpData(String id, String name,
                                             String joinDate, String gender, String contact,
                                             String email) throws SQLException {
        String query = "INSERT INTO EmpData (emp_id, emp_name,"
                + "emp_joinDate, emp_gender, emp_contact, emp_email) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hosteljava", "root", "password");
        var pstmt = conn.prepareStatement(query);
        pstmt.setString(1, id);
        pstmt.setString(2, name);
        pstmt.setString(3, joinDate);
        pstmt.setString(4, gender);
        pstmt.setString(5, contact);
        pstmt.setString(6, email);
        
        pstmt.executeUpdate();
        conn.close();
    }

    // Function to update the StudentData data using the id
    protected static void updateEmpData(String id, String name,
                                             String joinDate, String gender, String contact,
                                             String email) throws SQLException {
        String query = "UPDATE EmpData SET "
                + "emp_name = ?, emp_joinDate = ?, emp_gender = ?,"
                + "emp_contact = ?, "
                + "emp_email = ?, WHERE emp_id = ?";

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hosteljava", "root", "password");
        var pstmt = conn.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, contact);
        pstmt.setString(3, joinDate);
        pstmt.setString(4, gender);
        pstmt.setString(5, email);
        pstmt.setString(6, id);
        
        pstmt.executeUpdate();
        conn.close();
    }

    // Function to delete the StudentData from the database
    protected static void deleteStudentData(String id) throws SQLException {
        String query = "DELETE FROM EmpData WHERE emp_id = ?";

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hosteljava", "root", "password");
        var pstmt = conn.prepareStatement(query);
        pstmt.setString(1, id);
        
        pstmt.executeUpdate();
        conn.close();
    }

    // Function that searches the StudentData in the database and updates the values using table model
    public static void searchStudentData(DefaultTableModel model, String searchTerm, String column) throws SQLException {
        model.setRowCount(0);
        String query = "SELECT * FROM EmpData WHERE " + column + " LIKE ?";
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hosteljava", "root", "password");
        var pstmt = conn.prepareStatement(query);
        pstmt.setString(1, "%" + searchTerm + "%");
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            String id = rs.getString("emp_id");
            String name = rs.getString("emp_name");
            String joinDate = rs.getString("emp_joinDate");
            String gender = rs.getString("emp_gender");
            String contact = rs.getString("emp_contact");
            String empemail = rs.getString("emp_email");

            model.addRow(new Object[]{id, name, joinDate, gender, contact, empemail, });
        }
        
        conn.close();
        rs.close();
    }

    // Function to fetch the data and add it to the model so that the JTable is updated
    public static void loadData(DefaultTableModel model) throws SQLException {
        model.setRowCount(0);
        String query = "SELECT * FROM EmpData";
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hosteljava", "root", "password");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {

            String id = rs.getString("emp_id");
            String name = rs.getString("emp_name");
            String joinDate = rs.getString("emp_joinDate");
            String gender = rs.getString("emp_gender");
            String contact = rs.getString("emp_contact");
            String empemail = rs.getString("emp_email");

            model.addRow(new Object[]{id, name, joinDate, gender, contact, empemail, });
        }
        
        conn.close();
        rs.close();
    }
}
