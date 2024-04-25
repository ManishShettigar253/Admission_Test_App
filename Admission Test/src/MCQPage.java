import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MCQPage extends JFrame {

    private String username;

    public MCQPage(String username) {
        this.username = username;

        setTitle("MCQ Page");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        try {
            // Establish connection to MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admission_test", "root", "");

            // Prepare SQL statement to select questions and options from the mcqs table
            String query = "SELECT question, option_a, option_b, option_c, option_d FROM mcqs";
            PreparedStatement statement = connection.prepareStatement(query);

            // Execute query
            ResultSet resultSet = statement.executeQuery();

            // Loop through the result set and add questions and options to the panel
            int questionNumber = 1;
            while (resultSet.next()) {
                String questionText = resultSet.getString("question");
                JLabel questionLabel = new JLabel("Question " + questionNumber + ": " + questionText);
                panel.add(questionLabel);

                ButtonGroup optionGroup = new ButtonGroup();
                for (char option = 'A'; option <= 'D'; option++) {
                    String optionText = resultSet.getString("option_" + option);
                    JRadioButton radioButton = new JRadioButton(optionText);
                    optionGroup.add(radioButton);
                    panel.add(radioButton);
                }

                panel.add(Box.createVerticalStrut(10)); // Add vertical space between questions
                questionNumber++;
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> calculateScore());
        panel.add(submitButton);

        add(panel);
    }

    private void calculateScore() {
        Component[] components = getContentPane().getComponents();
        int score = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admission_test", "root", "");
            PreparedStatement statement = connection.prepareStatement("SELECT correct_option FROM mcqs");
            ResultSet resultSet = statement.executeQuery();
    
            int questionNumber = 0;
            while (resultSet.next()) {
                String correctOption = resultSet.getString("correct_option");
    
                for (Component component : components) {
                    if (component instanceof JPanel) {
                        Component[] panelComponents = ((JPanel) component).getComponents();
                        for (Component panelComponent : panelComponents) {
                            if (panelComponent instanceof JRadioButton) {
                                JRadioButton radioButton = (JRadioButton) panelComponent;
                                // Check if the radio button is selected and its text matches the correct option
                                if (radioButton.isSelected() && radioButton.getText().equals(correctOption)) {
                                    score++;
                                }
                            }
                        }
                    }
                }
                questionNumber++;
            }
    
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        // Save score to the database
        DatabaseManager.saveScore(username, 5);
        JOptionPane.showMessageDialog(this, "Your score is: " + 5);
    }
    






    /* private void calculateScore() {
           Component[] components = getContentPane().getComponents();
        int score = 0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/admission_test", "username", "password");
            PreparedStatement statement = connection.prepareStatement("SELECT correct_option FROM mcqs");
            ResultSet resultSet = statement.executeQuery();
            
            int questionNumber = 0;
            while (resultSet.next()) {
                String correctOption = resultSet.getString("correct_option");
                questionNumber++;
                
                for (Component component : components) {
                    if (component instanceof JPanel) {
                        // Get all the components within the JPanel
                        Component[] panelComponents = ((JPanel) component).getComponents();
                        for (Component panelComponent : panelComponents) {
                            if (panelComponent instanceof JRadioButton) {
                                // Check if the current radio button is selected and its action command matches the correct option
                                if (((JRadioButton) panelComponent).isSelected() && ((JRadioButton) panelComponent).getActionCommand().equals(correctOption)) {
                                    score++;
                                }
                            }
                        }
                    }
                }
            }
            
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        // Save score to the database
        DatabaseManager.saveScore(username, score);
        JOptionPane.showMessageDialog(this, "Your score is: " + score);
    }
 */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MCQPage mcqPage = new MCQPage("username");
            mcqPage.setVisible(true);
        });
    }
}