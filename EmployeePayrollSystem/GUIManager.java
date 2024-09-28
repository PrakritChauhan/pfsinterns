import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GUIManager {

    private DatabaseManager dbManager = new DatabaseManager();

    public GUIManager() {
        dbManager.connect();
    }

    public void createGUI() {
        JFrame frame = new JFrame("Employee Payroll System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Employee ID");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(150, 20, 165, 25);
        panel.add(userText);

        JButton calculateButton = new JButton("Calculate Salary");
        calculateButton.setBounds(10, 80, 150, 25);
        panel.add(calculateButton);

        JLabel resultLabel = new JLabel("Net Salary: ");
        resultLabel.setBounds(10, 120, 300, 25);
        panel.add(resultLabel);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(userText.getText());
                Employee employee = dbManager.getEmployee(id);
                Payroll payroll = new Payroll(40, 500, 0.20, 1000); // Mock values for demo
                double netSalary = payroll.calculateNetSalary(employee);
                resultLabel.setText("Net Salary: " + netSalary);
            }
        });
    }
}
