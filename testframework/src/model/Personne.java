package model;
import etu2054.framework.ModelView;
import etu2054.framework.annotations.UrlAnnot;
import java.util.ArrayList;

public class Personne {
    String name;
    String gender;
    
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


    public Personne() {
    }

    public Personne(String name, String gender){
        setName(name);
        setGender(gender);
    }

    @UrlAnnot(url="info/")
    public ModelView info(){
        ModelView modelView = new ModelView();
        ArrayList<Personne> liste = new ArrayList<Personne>();
        liste.add(new Personne("Georges","male"));
        liste.add(new Personne("Marie","female"));
        modelView.setView("../info.jsp");
        modelView.addItem("lst",liste);
        return modelView;
    }
}
