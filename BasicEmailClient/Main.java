import javax.swing.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
    // Launch the EmailClientGUI
    SwingUtilities.invokeLater(() -> {
        EmailGUI emailClientGUI = new EmailGUI();
        emailClientGUI.setVisible(true);
    });
}
}

