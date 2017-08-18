package Model;

/**
 * Created by goekh on 14.08.2017.
 */
public class Login {

        /*
    @Attribute der Tabelle Login in der Datenbank
     */

    private String passport_number;
    private String name;
    private String gender   ;
    private String nationality;

    public Login(String id, String username,String password,String emailadress){
        this.passport_number=id;
        this.name=username;
        this.gender=password;
        this.nationality=emailadress;
    }

    public Login(){}

    public Login(String passport_number,String name){
        super();
        this.passport_number=passport_number;
        this.name=name;
    }

    //id setter - getter
    public String getPassport_number(){return passport_number;}
    public void setPassport_number(String passport_number){this.passport_number=passport_number;}

    //Username setter - getter
    public String getName(){return name;}
    public void setUsername(String name){this.name=name;}

    //Password setter - getter
    public String getGender(){return gender;}
    public void setGender(String gender){this.gender=gender;}

    //Username setter - getter
    public String getNationality(){return nationality;}
    public void setNationality(String nationality){this.nationality=nationality;}


}
