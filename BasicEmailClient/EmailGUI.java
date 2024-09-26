import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmailGUI extends JFrame {
    private JButton refreshButton, sendButton;
    private JTextArea inboxDisplay;
    private JTextField recipientField, subjectField;
    private JTextArea bodyTextArea;

    public EmailGUI() {
        // Initialize GUI components
        refreshButton = new JButton("Refresh Inbox");
        sendButton = new JButton("Send Email");
        inboxDisplay = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(inboxDisplay);
        inboxDisplay.setEditable(false);  // Inbox display should be non-editable

        // Panel for sending emails
        JPanel sendEmailPanel = new JPanel();
        sendEmailPanel.setLayout(new BorderLayout());

        // Input fields for sending email
        recipientField = new JTextField(30);
        subjectField = new JTextField(30);
        bodyTextArea = new JTextArea(10, 40);

        // Panel layout for email fields
        JPanel sendEmailFields = new JPanel(new GridLayout(3, 2));
        sendEmailFields.add(new JLabel("To:"));
        sendEmailFields.add(recipientField);
        sendEmailFields.add(new JLabel("Subject:"));
        sendEmailFields.add(subjectField);
        sendEmailFields.add(new JLabel("Body:"));
        sendEmailPanel.add(sendEmailFields, BorderLayout.NORTH);
        sendEmailPanel.add(new JScrollPane(bodyTextArea), BorderLayout.CENTER);
        sendEmailPanel.add(sendButton, BorderLayout.SOUTH);

        // Add components to the frame
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(refreshButton, BorderLayout.SOUTH);
        this.add(sendEmailPanel, BorderLayout.NORTH);

        // Action Listeners
        refreshButton.addActionListener(e -> EmailReceiver.checkInbox(inboxDisplay, refreshButton));
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipient = recipientField.getText();
                String subject = subjectField.getText();
                String body = bodyTextArea.getText();
                EmailService.sendEmail(recipient, subject, body);
            }
        });

        // Set JFrame properties
        setTitle("Email Client");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window on the screen
    }

    public static void main(String[] args) {
        // Run the application on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            EmailGUI emailClientGUI = new EmailGUI();
            emailClientGUI.setVisible(true);
        });
    }
}


