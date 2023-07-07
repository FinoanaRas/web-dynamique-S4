package model;
import etu2054.framework.ModelView;
import etu2054.framework.FileUpload;
import etu2054.framework.annotations.UrlAnnot;

public class Client{
    String name;
    FileUpload badge;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public FileUpload getBadge(){
        return badge;
    }

    public void setBadge(FileUpload badge){
        this.badge = badge;
    }

    public Client(){}
    public Client(String name, FileUpload file){
        this.name = name;
        this.badge = file;
    }

    @UrlAnnot(url="client.do")
    public ModelView getClient(){
        ModelView modelView = new ModelView();
        Client client = new Client(getName(),getBadge());
        modelView.setView("./client.jsp");
        modelView.addItem("client",client);
        return modelView;
    }
}