package boundary;

import javax.swing.*;
import java.awt.*;
import entity.*;
import controller.InstanceController;

public class LoginView extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    public JButton submitLoginButton;
    private JButton registerButton;
    private InstanceController instance;
    private JFrame parentFrame;

    public LoginView(JFrame parent) {  // Remove Login parameter
        this.parentFrame = parent;
        this.instance = InstanceController.getInstance();
        setLayout(new GridBagLayout());
        initializeComponents();
    }
  

  private void initializeComponents() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);

    // Title
    JLabel titleLabel = new JLabel("Movie Ticket Reservation System");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    gbc.insets = new Insets(20, 20, 40, 20);
    add(titleLabel, gbc);

    // Username
    JLabel usernameLabel = new JLabel("Username:");
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    gbc.anchor = GridBagConstraints.EAST;
    gbc.insets = new Insets(5, 5, 5, 5);
    add(usernameLabel, gbc);

    usernameField = new JTextField(20);
    gbc.gridx = 1;
    gbc.gridy = 1;
    add(usernameField, gbc);

    // Password
    JLabel passwordLabel = new JLabel("Password:");
    gbc.gridx = 0;
    gbc.gridy = 2;
    add(passwordLabel, gbc);

    passwordField = new JPasswordField(20);
    gbc.gridx = 1;
    gbc.gridy = 2;
    add(passwordField, gbc);

    // Buttons Panel
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

    submitLoginButton = new JButton("Login");
    submitLoginButton.setPreferredSize(new Dimension(100, 30));
    buttonPanel.add(submitLoginButton);

    registerButton = new JButton("Register");
    registerButton.setPreferredSize(new Dimension(100, 30));
    buttonPanel.add(registerButton);

    JButton guestButton = new JButton("Continue as Guest");
    guestButton.setPreferredSize(new Dimension(150, 30));
    buttonPanel.add(guestButton);

    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 2;
    gbc.insets = new Insets(30, 5, 5, 5);
    add(buttonPanel, gbc);

    // Add action listeners

    submitLoginButton.addActionListener(e -> {
      // Here you would normally validate login credentials
      // For now, just navigate to MainView
      MainView mainView = new MainView(parentFrame);
      parentFrame.setContentPane(mainView);
      parentFrame.revalidate();
      parentFrame.repaint();
    });

    registerButton.addActionListener(e -> {
      RegistrationView registrationView = new RegistrationView(parentFrame);
      parentFrame.setContentPane(registrationView);
      parentFrame.revalidate();
      parentFrame.repaint();
    });

    guestButton.addActionListener(e -> {
        // Create ordinary user and set in InstanceController
        UserOrdinary guestUser = new UserOrdinary(0, "Guest", "guest@example.com", "");
        instance.setUser(guestUser);
        
        // Navigate to MainView
        MainView mainView = new MainView(parentFrame);
        parentFrame.setContentPane(mainView);
        parentFrame.revalidate();
        parentFrame.repaint();
    });
  }

  public String getUsername() {
    return usernameField.getText();
  }

  public String getPassword() {
    return new String(passwordField.getPassword());
  }

  public void clearFields() {
    usernameField.setText("");
    passwordField.setText("");
  }
}