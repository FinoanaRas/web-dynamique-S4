package etu2054.framework;
public class FileUpload{
    String name;
    String path;
    byte[] image;

    public String getName(){
        return name;
    }

    public String getPath(){
        return path;
    }

    public byte[] getImage(){
        return image;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPath(String path){
        this.path = path;
    }

    public void setImage(byte[] image){
        this.image = image;
    }

    public FileUpload(){}
    public FileUpload(String name, String path, byte[] image){
        setImage(image);
        setName(name);
        setPath(path);
    }
}