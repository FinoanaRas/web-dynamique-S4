package model;
import etu2054.framework.ModelView;
import etu2054.framework.annotations.UrlAnnot;
import etu2054.framework.annotations.RestAPI;
import java.util.ArrayList;
import java.lang.Integer;
import java.sql.Date;

public class Personne {
    String name;
    String gender;
    Date dtn;
    Integer num;
    String[] langues;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String[] getLangues() {
        return langues;
    }
    public void setLangues(String[] langues) {
        this.langues = langues;
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

    public Personne(String name, String gender,Date dtn,Integer num, String[] langues){
        setName(name);
        setGender(gender);
        setDtn(dtn);
        setNum(num);
        setLangues(langues);
    }

    @UrlAnnot(url="info.do")
    public ModelView info(){
        ModelView modelView = new ModelView();
        Personne pers = new Personne(getName(),getGender(),getDtn(),getNum(),getLangues());
        modelView.setView("./info.jsp");
        modelView.addItem("pers",pers);
        return modelView;
    }

    @UrlAnnot(url="login.do")
    public ModelView login(){
        ModelView modelView = new ModelView();
        Personne pers = new Personne(getName(),getGender(),getDtn(),getNum(),getLangues());
        modelView.setView("./info.jsp");
        modelView.addItem("pers",pers);
        modelView.addSession("isConnected", "true");
        modelView.addSession("profile", getName());
        
        return modelView;
    }

    @UrlAnnot(url="formulaire.do")
    public ModelView form(){
        ModelView modelView = new ModelView();
        modelView.setView("./form.jsp");
        return modelView;
    }

    @UrlAnnot(url="informations.do")
    public ModelView spec(Integer id){
        ModelView modelView = new ModelView();
        ArrayList<Personne> liste = new ArrayList<Personne>();
        liste.add(new Personne("Marie","female",Date.valueOf("1998-03-04"),35,new String[] {"fr","en","mg"}));
        // liste.add(new Personne("Json","male",Date.valueOf("2000-07-11"),124));
        // liste.add(new Personne("Py","male",Date.valueOf("2008-05-24"),138));
        modelView.setView("./spec.jsp");
        modelView.addItem("pers",liste.get(id.intValue()));
        return modelView;
    }
    @UrlAnnot(url="instancePers.do")
    public ModelView instance(){
        ModelView modelView = new ModelView();
        Integer nbInstance = getNum();
        if(nbInstance == null){
            nbInstance = 0;
        }
        setNum(nbInstance+1);
        modelView.setView("./instanceCounter.jsp");
        modelView.addItem("num",getNum());
        return modelView;
    }

    @UrlAnnot(url="persToJson.do")
    public ModelView json(){
        ModelView modelView = new ModelView();
        // ArrayList<Personne> liste = new ArrayList<Personne>();
        modelView.addItem("p1",new Personne("Marie","female",Date.valueOf("1998-03-04"),35,new String[] {"fr","en","mg"}));
        modelView.addItem("p2",new Personne("Json","male",Date.valueOf("2000-07-11"),124,new String[] {"fr","mg"}));
        modelView.addItem("p3",new Personne("Py","male",Date.valueOf("2008-05-24"),138,new String[] {"en","mg"}));
        // modelView.addItem("personnes",liste);
        modelView.setIsJson(true);
        return modelView;
    }

    @RestAPI
    @UrlAnnot(url="persToJsonRest.do")
    public Personne getPers(){
        return new Personne("Json","male",Date.valueOf("2000-07-11"),124,new String[] {"fr","mg"});
    }
}
