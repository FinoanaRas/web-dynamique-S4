package model;
import etu2054.framework.ModelView;
import etu2054.framework.annotations.UrlAnnot;
import java.util.ArrayList;
import java.lang.Integer;
import java.sql.Date;

public class Personne {
    String name;
    String gender;
    Date dtn;
    Integer num;
    
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

    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }

    public Personne() {
    }

    public Personne(String name, String gender,Date dtn,Integer num){
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

    @UrlAnnot(url="informations/")
    public ModelView spec(Integer id){
        ModelView modelView = new ModelView();
        ArrayList<Personne> liste = new ArrayList<Personne>();
        liste.add(new Personne("Marie","female",Date.valueOf("1998-03-04"),35));
        liste.add(new Personne("Json","male",Date.valueOf("2000-07-11"),124));
        liste.add(new Personne("Py","male",Date.valueOf("2008-05-24"),138));
        modelView.setView("../spec.jsp");
        modelView.addItem("pers",liste.get(id.intValue()));
        return modelView;
    }
}
