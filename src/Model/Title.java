package Model;

/**
 * Created by goekh on 15.08.2017.
 */
public class Title {

    private int id;
    private String titles;

    public Title(int id,String titles){
        super();
        this.id=id;
        this.titles=titles;
    }

    public void setTitles(String titles){this.titles=titles;}
    public String getTitles(){return titles;}

    public void setId(int id){this.id=id;}
    public int getId(){return id;}


}
