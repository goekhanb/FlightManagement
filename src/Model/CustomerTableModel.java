package Model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

/**
 * Created by goekh on 14.08.2017.
 */
public class CustomerTableModel implements TableModel {

    private ArrayList<FlightCustomer> customers;

    public CustomerTableModel(ArrayList<FlightCustomer> customers){this.customers =customers;}

    @Override
    public int getRowCount() {
        return customers.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch ( columnIndex ){
            case 0: return"Passport_number";
            case 1: return "Name";
            case 2: return "Gender";
            case 3: return "Nationality";

        }

        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    if(columnIndex==0) return customers.get(rowIndex).getPassport_number();
    else if(columnIndex==1)return customers.get(rowIndex).getName();
    else if(columnIndex==2)return customers.get(rowIndex).getGender();
    else if(columnIndex==3)return customers.get(rowIndex).getNationality();
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
