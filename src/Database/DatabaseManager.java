package Database;

import Model.FlightCustomer;
import Model.Login;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by goekh on 14.08.2017.
 */
public class DatabaseManager {

    private final static String dbserver = "localhost";
    private final static int dbport = 3306;
    private final static String dbname = "travelagency";
    private final static String dbuser = "root";
    private final static String dbpass = "root";
    private final static String url = "jdbc:mysql://" + dbserver + ":" + dbport + "/" + dbname + "?useSSL=false";

    private static String query = "";
    private static DatabaseManager instance = null;
    private static Connection con = null;

    public DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        return instance;
    }

    private void verbinden(String url, String dbuser, String dbpass, String driver) throws Exception {
        Class.forName(driver);
        this.con = DriverManager.getConnection(url, dbuser, dbpass);
    }

    public static void verbinden() throws Exception {
        instance = new DatabaseManager();
        instance.verbinden(url, dbuser, dbpass, "com.mysql.jdbc.Driver");
    }

    public ResultSet ausfuehren(String query) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.execute();
        return preparedStatement.getResultSet();
    }

    public ArrayList<Login> getAllLoginUser() throws SQLException {
        ArrayList<Login> loginArrayList = new ArrayList<>();
        query = "select * from travelagency.login";
        ResultSet resultSet = ausfuehren(query);
        while (resultSet.next()){

            String passport_number = resultSet.getString(1);
            String name = resultSet.getString(2);
            loginArrayList.add(new Login(name,passport_number));

        }
        return loginArrayList;
    }

    public ArrayList<FlightCustomer> getAllCustomers() throws Exception{
        ArrayList<FlightCustomer> employeeArrayList = new ArrayList<>();
        query="select * from `travelagency`.`flightcustomer";
        ResultSet resultSet = ausfuehren(query);
        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String gender = resultSet.getString(3);
            String nationality = resultSet.getString(4);
            employeeArrayList.add(new FlightCustomer(id,name,gender,nationality));

        }
                  return employeeArrayList;
    }


    public void deleteEmployee(int id) throws SQLException
    {
        query ="DELETE FROM `customerproject`.`employee` WHERE ID = ?;";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, id);
        ausfuehren(pstmt.toString());
    }

    public void insertEmployee(FlightCustomer newEmployee) throws SQLException {
        query="insert into flightcustomer VALUES(?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(0,newEmployee.getPassport_number());
        pstmt.setString(1,newEmployee.getName());
        pstmt.setString(2,newEmployee.getGender());
        pstmt.setString(3,newEmployee.getNationality());
        ausfuehren(pstmt.toString());
    }

    public void updateEmployee(FlightCustomer updateEmployee) throws SQLException {
        query="update flightcustomer set name= ?, gender= ?, nationality=?  where id = ?";
        PreparedStatement pstm = con.prepareCall(query);
        pstm.setString(1,updateEmployee.getName());
        pstm.setString(2,updateEmployee.getGender());
        pstm.setString(3,updateEmployee.getNationality());
        pstm.setInt(4,updateEmployee.getPassport_number());
                ausfuehren(pstm.toString());
    }


    public int getEmployeeMax() throws SQLException
    {
        String query = "select max(id) from travelagency.flightcustomer";
        int count = 0;
        ResultSet rs = ausfuehren(query);
        while (rs.next()) {
            count = rs.getInt(1)+1;
        }
        return count;
    }


}
