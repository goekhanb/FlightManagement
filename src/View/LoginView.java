package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Database.DatabaseManager;
import Model.Login;
import Model.LoginModel;
import View.Overview;


public class LoginView extends JFrame implements ActionListener,Observer {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtTeo;
    private JPasswordField passwordField;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JButton btnLogin;
    private JButton btnCancel;

    private LoginModel loginM = new LoginModel();
    private Login userlogv = new Login();
    private Overview overview;

    public LoginView() {
        setTitle("Login @ FlightCustomer Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        lblUsername = new JLabel("Username :");
        lblUsername.setBounds(50, 50, 78, 16);
        contentPane.add(lblUsername);
        lblPassword = new JLabel("Password :");
        lblPassword.setBounds(50, 79, 78, 16);
        contentPane.add(lblPassword);
        txtTeo = new JTextField();
        txtTeo.setText("");
        txtTeo.setBounds(153, 45, 161, 26);
        contentPane.add(txtTeo);
        txtTeo.setColumns(10);
        passwordField = new JPasswordField();
        passwordField.setBounds(153, 74, 161, 26);
        contentPane.add(passwordField);
        passwordField.setText("");
        btnLogin = new JButton("LogIn");
        btnLogin.setBounds(98, 107, 117, 29);
        contentPane.add(btnLogin);
        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(225, 107, 117, 29);
        contentPane.add(btnCancel);
    }
    public void openWindow()
    {
        try
        {
            DatabaseManager.verbinden();
        }
        catch(Exception e)
        {
            System.err.println("DB Fehlgeschalgen " + e);
        }
        try
        {
            loginM.getAllLoginUsers();
        }
        catch(SQLException e)
        {
            System.err.println("Login User Daten runterladen Fehlgeshlagen " + e);
        }
        setzeAlleActionListener(this);
        this.setVisible(true);
    }
    public void setzeAlleActionListener(ActionListener l)
    {
        btnLogin.addActionListener(l);
        btnCancel.addActionListener(l);
    }
    private JButton getButtonLogin()
    {
        return btnLogin;
    }
    private JButton getButtonCancel()
    {
        return btnCancel;
    }

    public String getUsernameFromJTextfield() {
        return txtTeo.getText();
    }
    @SuppressWarnings("deprecation")
    public String getPasswordFromJTextfield() {
        return passwordField.getText();
    }

    public void loginpressed() throws Exception {
        String username=getUsernameFromJTextfield();
        String password=getPasswordFromJTextfield();
        userlogv = new Login(username,password);

        /*if(username.equals("") || password.equals(""))
        {
            JOptionPane.showMessageDialog(null,"Login failed ! Username or password is not correct.",
                    "Failure Notification",JOptionPane.WARNING_MESSAGE);
            System.err.println("Kein Username / Passwort ; beides leere Inhalte!");
        }
        else
        {
            if(loginM.checkLogin2(userlogv))
            {*/
                overview = new Overview();
                overview.addObserver(this);
                overview.openWindow();
                this.dispose();
           /* }
            else
            {
                JOptionPane.showMessageDialog(null,"Login failed ! Username or password is not correct.",
                        "Failure Notification",JOptionPane.WARNING_MESSAGE);
                System.err.println("Incorrect Username/PASSWORD");
            }
        }*/
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == getButtonLogin())
        {
            try {
                loginpressed();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else if(e.getSource() == getButtonCancel())
        {
            System.exit(0);
        }


    }
    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        this.setVisible(true);

    }
}
