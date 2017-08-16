package Model;

/**
 * Created by goekh on 14.08.2017.
 */
public class Login {

        /*
    @Attribute der Tabelle Login in der Datenbank
     */

    private int idLogin;
    private String username;
    private String password;
    private String emailadress;

    public Login(int id, String username,String password,String emailadress){
        this.idLogin=id;
        this.username=username;
        this.password=password;
        this.emailadress=emailadress;
    }

    public Login(){}

    public Login(String username,String password){
        super();
        this.username=username;
        this.password=password;
    }

    //id setter - getter
    public int getIdLogin(){return idLogin;}
    public void setIdLogin(int idLogin){this.idLogin=idLogin;}

    //Username setter - getter
    public String getUsername(){return username;}
    public void setUsername(String username){this.username=username;}

    //Password setter - getter
    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}

    //Username setter - getter
    public String getEmailadress(){return emailadress;}
    public void setEmailadress(String emailadress){this.emailadress=emailadress;}


}
