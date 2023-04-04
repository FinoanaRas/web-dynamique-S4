package model;
import etu2054.framework.ModelView;
import etu2054.framework.annotations.UrlAnnot;

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

    @UrlAnnot(url="info/")
    public ModelView info(){
        return new ModelView("../info.jsp");
    }
}
