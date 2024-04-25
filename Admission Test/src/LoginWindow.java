import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginWindow() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 20, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(100, 20, 160, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 50, 160, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 80, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Authenticate user (validate credentials with database)
                if (authenticate(username, password)) {
                    dispose(); // Close login window
                    openMCQPage(username); // Open MCQ page with username
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }
            }
        });

        add(panel);
    }

    private boolean authenticate(String username, String password) {
        try {
            Connection conn = DatabaseManager.getConnection();
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            // If there is a matching record, authentication succeeds
            boolean isValidUser = rs.next();
            
            rs.close();
            stmt.close();
            conn.close();
            
            return isValidUser;
        } catch (SQLException ex) {
            // Handle database connection or query errors
            ex.printStackTrace();
            return false;
        }
    }

    private void openMCQPage(String username) {
        MCQPage mcqPage = new MCQPage(username);
        mcqPage.setVisible(true);
    }
}
