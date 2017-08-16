package View;

import Database.DatabaseManager;
import Model.CustomerModel;
import Model.CustomerTableModel;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;



/**
 * Created by goekh on 15.08.2017.
 */
public class Overview extends Observable implements ActionListener,Observer {

    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private JPanel contentPane;
    private JTable table;
    private JButton btnEditEmployee;
    private JButton btnDeleteEmployee;
    private JButton btnAddEmployee;
    private JButton btnClose;

    private AddCustomerView addCustomerView;
    private EditCustomerView editCustomerView;
    private CustomerModel customerModel = new CustomerModel();

    public Overview() {
        super();
        frame = new JFrame();
        frame.setTitle("Overview");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(100, 100, 709, 382);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(contentPane);
        contentPane.setLayout(null);
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(6, 6, 697, 240);
        contentPane.add(scrollPane);

        btnAddEmployee = new JButton("Add FlightCustomer");
        btnAddEmployee.setBounds(6, 256, 128, 29);
        contentPane.add(btnAddEmployee);

        btnEditEmployee = new JButton("Edit FlightCustomer");
        btnEditEmployee.setBounds(134, 256, 128, 29);
        contentPane.add(btnEditEmployee);

        btnDeleteEmployee = new JButton("Delete FlightCustomer");
        btnDeleteEmployee.setBounds(261, 256, 128, 29);
        contentPane.add(btnDeleteEmployee);

        btnClose = new JButton("Close");
        btnClose.setBounds(387, 256, 117, 29);
        contentPane.add(btnClose);
    }

    public void setzeAlleActionListener(ActionListener l)
    {
        btnAddEmployee.addActionListener(l);
        btnEditEmployee.addActionListener(l);
        btnDeleteEmployee.addActionListener(l);
        btnClose.addActionListener(l);
    }

    public void setTableModel(CustomerTableModel customerTM) {
        this.table.setModel(customerTM);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(String.class, centerRenderer);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
    }




    public void openWindow() throws Exception{
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
            customerModel.getAllCustomers();
        }
        catch(SQLException e)
        {
            System.err.println("Employeee Daten runterladen Fehlgeshlagen " + e);
        }
        setzeAlleActionListener(this);
        this.setTableModel(customerModel.getCustomerTableModel());

        frame.setVisible(true);
    }

    public JButton getBtnAdd()
    {
        return btnAddEmployee;
    }
    public JButton getBtnEdit()
    {
        return btnEditEmployee;
    }
    public JButton getBtnDelete()
    {
        return btnDeleteEmployee;
    }

    public void addEmployeePressed()
    {
        addCustomerView = new AddCustomerView(customerModel);
        addCustomerView.addObserver(this);
        addCustomerView.openWindow();

    }
    public void editEmployeePressed()
    {
        int index = getTableIndex();
        if (index == -1)
        {
            JOptionPane.showMessageDialog(null,
                    "You have to choose an employee!",
                    "Failure Notification",JOptionPane.WARNING_MESSAGE);
            return;
        }
        editCustomerView = new EditCustomerView(customerModel,index);
        addCustomerView.addObserver(this);
        addCustomerView.openWindow();
    }
    private int getTableIndex()
    {
        return this.table.getSelectedRow();
    }
    public void deleteEmployeePressed()
    {
        {
            int index = getTableIndex();
            if (index == -1)
            {
                JOptionPane.showMessageDialog(null,
                        "You have to choose a employee!",
                        "Failure Notification",JOptionPane.WARNING_MESSAGE);
                return;
            }
            String name = customerModel.getCustomerName(index);
            String gender = customerModel.getCustomerGender(index);
            Object[] options = {"Yes","No"};
            int selected = JOptionPane.showOptionDialog(null, "Do you want to delete the customer '" + name +" , "+ gender+"?",
                    "Are you sure?",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);
            if(selected==0)
            {
                try {
                    customerModel.deleteCustomer(index);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                update(null,null);
            }
            else if(selected==1)
            {
                return;
            }
        }
    }

    public void closedPressed()
    {
        super.setChanged();
        super.notifyObservers();
        frame.dispose();
    }

    public JButton getBtnClose()
    {
        return btnClose;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource() == getBtnAdd())
        {
            addEmployeePressed();
        }
        else if(e.getSource() == getBtnDelete())
        {
            deleteEmployeePressed();
        }
        else if(e.getSource() == getBtnEdit())
        {
            editEmployeePressed();
        }
        else if(e.getSource() == getBtnClose())
        {
            closedPressed();
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        try {
            customerModel.getAllCustomers();
        } catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTableModel(customerModel.getCustomerTableModel());

    }
}
