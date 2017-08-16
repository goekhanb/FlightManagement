package Model;

import Database.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by goekh on 14.08.2017.
 */
public class CustomerModel {

   private ArrayList<FlightCustomer> customerArrayList = new ArrayList<>();

   public CustomerModel(){this.customerArrayList = new ArrayList<FlightCustomer>();}

    public void getAllCustomers() throws Exception {
        customerArrayList = DatabaseManager.getInstance().getAllCustomers();
        System.out.println("Loading all employees");
    }

    public CustomerTableModel getCustomerTableModel(){return new CustomerTableModel(customerArrayList);}

    public int getCustomerPassportNumber(int index){return customerArrayList.get(index).getPassport_number();}
    public String getCustomerName(int index){return customerArrayList.get(index).getName();}
    public String getCustomerGender(int index){return customerArrayList.get(index).getGender();}
    public String getCustomerNationality(int index){return customerArrayList.get(index).getNationality();}

    public void deleteCustomer(int index) throws SQLException {
        customerArrayList.get(index).getPassport_number();
        DatabaseManager.getInstance().deleteEmployee(index);
        customerArrayList.remove(index);
    }

    public void addCustomer(FlightCustomer newEmployee) throws SQLException {
        DatabaseManager.getInstance().insertEmployee(newEmployee);
        customerArrayList.add(newEmployee);
    }

    public void updateCustomer(FlightCustomer updateEmployee) throws SQLException {
        DatabaseManager.getInstance().updateEmployee(updateEmployee);

    }



}
