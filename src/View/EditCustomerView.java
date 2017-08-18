package View;

import Database.DatabaseManager;
import Model.FlightCustomer;
import Model.CustomerModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by goekh on 15.08.2017.
 */
public class EditCustomerView extends Observable implements ActionListener {

    private JPanel contentPane;
    private JFrame frame;
    private JTextField txtId;
    private JTextField txtSurname;
    private JTextField txtLastname;
    private JTextField txtAdress;

    private JTextField txtNet;
    private JComboBox<String> cbxTitel;
    private JLabel lblId;
    private JLabel lblTitel;
    private JLabel lblSurname;
    private JLabel lblLastname;
    private JLabel lblAdress;
    private JLabel lblPostcode;
    private JLabel lblBirthday;
    private JLabel lblGross;
    private JLabel lblNet;
    private JButton btnAddEmployee;
    private JButton btnCancel;
    private CustomerModel customerM;
    private FlightCustomer newcustomer;
    private int index;

    public EditCustomerView(CustomerModel customerM,int index) {
        this.customerM = customerM;
        this.index = index;
        frame = new JFrame("Edit FlightCustomer");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 427, 348);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);

        lblId = new JLabel("ID");
        lblId.setBounds(27, 28, 61, 16);
        contentPane.add(lblId);

        lblTitel = new JLabel("Name");
        lblTitel.setBounds(27, 56, 61, 16);
        contentPane.add(lblTitel);

        lblSurname = new JLabel("Gender");
        lblSurname.setBounds(27, 84, 61, 16);
        contentPane.add(lblSurname);

        lblLastname = new JLabel("Nationality");
        lblLastname.setBounds(27, 112, 61, 16);
        contentPane.add(lblLastname);

        btnAddEmployee = new JButton("Save");
        btnAddEmployee.setBounds(100, 287, 117, 29);
        contentPane.add(btnAddEmployee);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(218, 287, 117, 29);
        contentPane.add(btnCancel);

        txtId = new JTextField();
        txtId.setText("ID");
        txtId.setBounds(205, 23, 130, 26);
        contentPane.add(txtId);
        txtId.setColumns(10);

        txtSurname = new JTextField();
        txtSurname.setText("name");
        txtSurname.setBounds(205, 79, 130, 26);
        contentPane.add(txtSurname);
        txtSurname.setColumns(10);

        txtLastname = new JTextField();
        txtLastname.setText("gender");
        txtLastname.setBounds(205, 107, 130, 26);
        contentPane.add(txtLastname);
        txtLastname.setColumns(10);

        txtAdress = new JTextField();
        txtAdress.setText("nationality");
        txtAdress.setBounds(205, 137, 130, 26);
        contentPane.add(txtAdress);
        txtAdress.setColumns(10);


        cbxTitel = new JComboBox<String>();
        cbxTitel.setBounds(205, 52, 130, 27);
        contentPane.add(cbxTitel);
        frame.setVisible(false);
    }
    public void openWindow()
    {
        try{
            DatabaseManager.verbinden();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        setzeAlleActionListener(this);
        setFields();
        //felder deaktivieren
        txtId.setEnabled(false);
        txtNet.setEnabled(false);
        frame.setVisible(true);
    }
    public void setzeAlleActionListener(ActionListener l)
    {
        btnAddEmployee.addActionListener(l);
        btnCancel.addActionListener(l);
    }

    public void setFields()
    {
        txtId.setText(Integer.toString(customerM.getCustomerPassportNumber(index)));
        txtSurname.setText(customerM.getCustomerName(index));
        txtLastname.setText(customerM.getCustomerGender(index));
        txtAdress.setText(customerM.getCustomerNationality(index));
    }

    public String getIdFromTextfield()
    {
        return txtId.getText();
    }
    public String getSurnameFromTextfield()
    {
        return txtSurname.getText();
    }
    public String getLastnameFromTextfield()
    {
        return txtLastname.getText();
    }
    public String getAdressFromTextfield()
    {
        return txtAdress.getText();
    }

    public String getTitleFromCbx()
    {
        return (String) cbxTitel.getSelectedItem();
    }
    public boolean checkFields()
    {
        if(getSurnameFromTextfield().isEmpty())
        {
            return false;
        }
        else if(getLastnameFromTextfield().isEmpty())
        {
            return false;
        }
        else if(getAdressFromTextfield().isEmpty())
        {
            return false;
        }

        else
        {
            return true;
        }
    }
    public void setTextfieldsClear()
    {
        txtSurname.setText("");
        txtLastname.setText("");
        txtAdress.setText("");
        txtNet.setText("");
    }
    public JButton getBtnAddEmployee()
    {
        return btnAddEmployee;
    }
    public JButton getBtnCancel()
    {
        return btnCancel;
    }

    public void addpressed()
    {
        if(checkFields())
        {
            //calculate net

            int passport_number = Integer.parseInt(getIdFromTextfield());
            String name = getTitleFromCbx();
            String gender = getSurnameFromTextfield();
            String nationality = getLastnameFromTextfield();

            newcustomer = new FlightCustomer(passport_number, name, gender,nationality);
            try
            {
                customerM.updateCustomer(newcustomer);
                super.setChanged();
                super.notifyObservers();
                JOptionPane.showMessageDialog(null,
                        "The employee was successfully added!",
                        "Failure Notification",JOptionPane.WARNING_MESSAGE);
                frame.dispose();

            }
            catch(Exception e1)
            {
                e1.printStackTrace();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,
                    "You have to fill in all textfields",
                    "Failure Notification",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void cancelpressed()
    {
        frame.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == getBtnAddEmployee())
        {
            // addpressed
            addpressed();
        }
        else if(e.getSource() == getBtnCancel())
        {
            //cancel pressed
            cancelpressed();
        }

    }

}
