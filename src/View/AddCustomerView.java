package View;

import Database.DatabaseManager;
import Model.FlightCustomer;
import Model.CustomerModel;
import Model.Title;
import Model.TitleModel;

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
public class AddCustomerView extends Observable implements ActionListener {

    private JFrame frmAddEmployee;
    private JPanel contentPane;
    private JTextField txtId;
    private JComboBox<String> cbxTitel;
    private JTextField txtName;
    private JTextField txtGender;
    private JTextField txtNationality;

    private JLabel lblPassportNumber;
    private JLabel lblName;
    private JLabel lblGender;
    private JLabel lblNationality;

    private JButton btnAddEmployee;
    private JButton btnCancel;

    private int id;

    private TitleModel titelM = new TitleModel();
    private FlightCustomer newemployee;
    private CustomerModel customerM;


    public AddCustomerView(CustomerModel customerM) {
        this.customerM = customerM;
        frmAddEmployee = new JFrame();
        frmAddEmployee.setTitle("Add FlightCustomer");
        frmAddEmployee.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmAddEmployee.setBounds(100, 100, 427, 348);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frmAddEmployee.setContentPane(contentPane);
        contentPane.setLayout(null);

        lblPassportNumber = new JLabel("ID");
        lblPassportNumber.setBounds(27, 28, 61, 16);
        contentPane.add(lblPassportNumber);

        lblName = new JLabel("Name");
        lblName.setBounds(27, 56, 61, 16);
        contentPane.add(lblName);

        lblGender = new JLabel("Gender");
        lblGender.setBounds(27, 84, 61, 16);
        contentPane.add(lblGender);

        lblNationality = new JLabel("Nationality");
        lblNationality.setBounds(27, 112, 61, 16);
        contentPane.add(lblNationality);

        cbxTitel = new JComboBox<String>();
        cbxTitel.setBounds(205, 52, 130, 27);
        contentPane.add(cbxTitel);

        frmAddEmployee.setVisible(false);

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
        try {
            titelM.loadTitles();
        } catch (SQLException e) {
            e.getErrorCode();
        }
        setzeAlleActionListener(this);
        setIDField();
        //felder deaktivieren
        fillComboBox();
        txtId.setEnabled(false);
        txtName.setEnabled(false);
        frmAddEmployee.setVisible(true);
    }
    public void setzeAlleActionListener(ActionListener l)
    {

        btnAddEmployee.addActionListener(l);
        btnCancel.addActionListener(l);
    }

    public void fillComboBox()
    {
        ArrayList<Title> titelList = titelM.returnList();
        for(int i=0;i<titelList.size();i++)
        {
            cbxTitel.addItem(titelList.get(i).getTitles());
        }
    }
    public void setIDField()
    {
        try
        {
            id = DatabaseManager.getInstance().getEmployeeMax();
        }
        catch(SQLException e)
        {
            e.getSQLState();
        }
        txtId.setText(Integer.toString(id));
    }

    public int getPassportNumberId(){return txtId.getColumns();}
    public String getName(){return txtName.getText();}
    public String getGender()
    {
        return txtGender.getText();
    }
    public String getNationality(){return txtNationality.getText();}
    public boolean checkFields()
    {
        if(getName().isEmpty())
        {
            return false;
        }
        else if(getGender().isEmpty())
        {
            return false;
        }
        else if(getNationality().isEmpty())
        {
            return false;
        }

     	/*else if(getNetFromTextfield().isEmpty())
		{
			return false;
		}*/
        else
        {
            return true;
        }
    }
    public void setTextfieldsClear()
    {
        setIDField();
        txtName.setText("");
        txtGender.setText("");
        txtNationality.setText("");
        //txtNet.setText("");
    }
    public JButton getBtnAddEmployee()
    {
        return btnAddEmployee;
    }
    public JButton getBtnCancel()
    {
        return btnCancel;
    }
    private void calculateNet()
    {
        int gros = Integer.parseInt(getNationality());
        double net = gros * 0.61;
        txtNationality.setText(Double.toString(net));
    }
    public void addEmployeePressed()
    {
        if(checkFields())
        {
            // Calculate NET
            calculateNet();
            int id =getPassportNumberId();
            String name = getName();
            String gender = getGender();
            String nationality = getNationality();
            newemployee = new FlightCustomer(id, name,gender,nationality);
            try
            {
                customerM.addCustomer(newemployee);
                super.setChanged();
                super.notifyObservers();
                Object[] options = {"Yes","No"};
                int selected = JOptionPane.showOptionDialog(null, "FlightCustomer was successfully added, do you want to add one employee more?",
                        "Adding customer was succesfull",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0]);
                if(selected==0)
                {
                    setTextfieldsClear();
                    setIDField();

                }
                else if(selected==1)
                {
                    CancelPressed();
                }
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
        //>
    }
    public void CancelPressed()
    {
        frmAddEmployee.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == getBtnAddEmployee())
        {
            addEmployeePressed();
        }
        else if(e.getSource() == getBtnCancel())
        {
            CancelPressed();
        }

    }
}
