package Model;

import Database.DatabaseManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by goekh on 15.08.2017.
 */
public class TitleModel {

    ArrayList<Title> titleArrayList = new ArrayList<>();
    public TitleModel(){
        titleArrayList = new ArrayList<>();
    }

    public void loadTitles() throws SQLException {
        titleArrayList = DatabaseManager.getInstance().getTitles();
    }

    public ArrayList<Title> returnList()
    {
        return titleArrayList;
    }

}
