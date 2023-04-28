package model;
import etu2054.framework.ModelView;
import etu2054.framework.annotations.UrlAnnot;
import java.util.ArrayList;
import java.sql.Date;

public class Personne {
    String name;
    String gender;
    Date dtn;
    int num;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDtn(Date dtn){
        this.dtn = dtn;
    }
    public Date getDtn(){
        return this.dtn;
    }

    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

    public Personne() {
    }

    public Personne(String name, String gender,Date dtn,int num){
        setName(name);
        setGender(gender);
        setDtn(dtn);
        setNum(num);
    }

    @UrlAnnot(url="info/")
    public ModelView info(){
        ModelView modelView = new ModelView();
        ArrayList<Personne> liste = new ArrayList<Personne>();
        liste.add(new Personne(getName(),getGender(),getDtn(),getNum()));
        modelView.setView("../info.jsp");
        modelView.addItem("lst",liste);
        return modelView;
    }

    @UrlAnnot(url="formulaire/")
    public ModelView form(){
        ModelView modelView = new ModelView();
        modelView.setView("../form.jsp");
        return modelView;
    }
}
