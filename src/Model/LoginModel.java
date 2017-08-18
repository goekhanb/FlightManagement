package Model;

import Database.DatabaseManager;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by goekh on 14.08.2017.
 */
public class LoginModel {

    ArrayList<Login> arrayListLogin = new ArrayList<>();

    public void getAllLoginUsers() throws SQLException {
        arrayListLogin =  DatabaseManager.getInstance().getAllLoginUser();
        System.out.print("Loading all user");
    }

    public boolean checkLogin2(Login user) {
        int i = 0;

        while (arrayListLogin.size() > 1) {
            if (user.getPassport_number().equals(arrayListLogin.get(i).getPassport_number()) && user.getName().equals(arrayListLogin.get(i).getName())) {
                return true;
            }
            i++;
        }
        return false;
    }

}
