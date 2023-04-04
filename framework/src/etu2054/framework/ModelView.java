package etu2054.framework;
import java.util.HashMap;

public class ModelView {
    String view;
    HashMap<String,Object> data;

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public HashMap<String,Object> getData() {
        return data;
    }

    public void setData(HashMap<String,Object> data) {
        this.data = data;
    }

    public ModelView(String view) {
        this.view = view;
    }

    public ModelView() {
        setData(new HashMap<String,Object>());
    }

    public void addItem(String key,Object obj){
        this.getData().put(key,obj);
    }
}
