package dbms1;

import javax.swing.*;

public class MessTable extends JFrame {

    public MessTable() {
        setTitle("Details Table");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sample column names and data
        String[] columnNames = {"Day", "Breakfast", "Lunch", "Dinner"};
        Object[][] data = {
            {"Monday", "Omelets", "Sambar", "Chole"},
            {"Tuesday", "Rajma", "Aloo Gobi", "Lauki"},
            {"Wednesday", "Pasta", "Okra Curry", "Egg Curry"},
            {"Thursday", "Noodles", "Plantain Curry", "Soya Beans"},
            {"Friday", "Boiled Egg", "Chicken Curry", "Dal"},
            {"Saturday", "Chole", "Aloo Curry", "Paneer"},
            {"Daily", "Bread/Jam/Milk", "Rice/Chappati", "Rice/Chappati/Milk"}
        };

        // Create table
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
    }
}
