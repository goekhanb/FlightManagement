package Model;

/**
 * Created by goekh on 14.08.2017.
 */
public class FlightCustomer {

    private int passport_number;
    private String name;
    private String gender;
    private String nationality;


    //constructor
    public FlightCustomer(int passport_number, String name, String gender, String nationality){
        this.passport_number=passport_number;
        this.name=name;
        this.gender=gender;
        this.nationality=nationality;
        }

    //getter- setter Passport number
    public int getPassport_number() {return passport_number;}
    public void setPassport_number(int passport_number) {this.passport_number = passport_number;}

    //getter- setter Name
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    //getter- setter Gender
    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}

    //getter- setter Nationality
    public String getNationality() {return nationality;}
    public void setNationality(String nationality) {this.nationality = nationality;}
}
