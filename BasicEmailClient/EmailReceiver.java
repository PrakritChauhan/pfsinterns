import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.util.Properties;

public class EmailReceiver {

    public static void checkInbox(JTextArea inboxDisplay, JButton refreshButton) {
        // Disable the refresh button while checking inbox
        refreshButton.setEnabled(false);
        
        // SwingWorker to perform background tasks
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                final String username = "prakritchauhan@gmail.com";  // Replace with your email
                final String password = "nksd jzkx ipsb tcqp";  // Replace with your app password

                Properties properties = new Properties();
                properties.put("mail.store.protocol", "imap");
                properties.put("mail.imap.host", "imap.gmail.com");
                properties.put("mail.imap.port", "993");
                properties.put("mail.imap.ssl.enable", "true");

                try {
                    Session session = Session.getDefaultInstance(properties);
                    Store store = session.getStore("imap");
                    store.connect(username, password);

                    Folder inbox = store.getFolder("INBOX");
                    inbox.open(Folder.READ_ONLY);

                    Message[] messages = inbox.getMessages();
                    inboxDisplay.setText("");  // Clear the inbox display before appending new content
                    for (Message message : messages) {
                        String subject = "Subject: " + message.getSubject();
                        String from = "From: " + message.getFrom()[0];
                        String content = getTextFromMessage(message);
                        inboxDisplay.append(subject + "\n" + from + "\n" + content + "\n\n");
                    }

                    inbox.close(false);
                    store.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void done() {
                // Re-enable the refresh button once the inbox is checked
                refreshButton.setEnabled(true);
            }
        };

        worker.execute(); // Execute the worker in a background thread
    }

    private static String getTextFromMessage(Message message) throws Exception {
        if (message.isMimeType("text/plain")) {
            return message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            return getTextFromMimeMultipart(mimeMultipart);
        }
        return "";
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            }
        }
        return result.toString();
    }
}
