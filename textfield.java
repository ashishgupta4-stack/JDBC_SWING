package Swing;

import javax.swing.*;

//We have created a class named textfield which contains the main method. This is the entry point of our program.

public class textfield {
    public static void main(String[] args) {
        JFrame frame = new JFrame("TextField Example");

        frame.setSize(400, 200);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel Username = new JLabel("username:");
        Username.setBounds(80, 100, 150, 30);
        JLabel Password = new JLabel("password:");
        Password.setBounds(80, 150, 150, 30);

        // JPasswordField passwordField = new JPasswordField(20);
        // passwordField.setBounds(200, 150, 150, 30);

        JTextField textField = new JTextField(20);
        textField.setBounds(200, 100, 150, 30);

        JTextField textField2 = new JTextField(20);
        textField2.setBounds(200, 150, 150, 30);

        JButton button = new JButton("Login");
        button.setBounds(200, 200, 100, 30);
        button.addActionListener(e -> {
            String name = textField.getText();
            String password = textField2.getText();

            if (name.equals("Ashish") && password.equals("1234")) {
                JOptionPane.showMessageDialog(frame, "Login Successful!");
            } else
                JOptionPane.showMessageDialog(frame, "Invalid username or password.");
        });

        frame.add(Username);
        frame.add(Password);
        frame.add(textField);
        frame.add(textField2);
        // frame.add(passwordField);
        frame.add(button);
        frame.setLayout(null); // Set the layout manager to null for absolute positioning
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
