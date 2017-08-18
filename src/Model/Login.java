package Model;

/**
 * Created by goekh on 14.08.2017.
 */
public class Login {

        /*
    @Attribute der Tabelle Login in der Datenbank
     */

    private String username;
    private String password;


    public Login(){}

    public Login(String username,String password){
        super();
        this.password=password;
        this.username=username;
    }

    //id setter - getter
    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}

    //Username setter - getter
    public String getUserName(){return username;}
    public void setUsername(String name){this.username=username;}


}
