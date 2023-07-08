package etu2054.framework;
import java.util.HashMap;

public class ModelView {
    String view;
    HashMap<String,Object> data;
    HashMap<String,Object> sessions;
    boolean isJson;
    
    

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
        setSessions(new HashMap<String,Object>());
    }

    public void addItem(String key,Object obj){
        this.getData().put(key,obj);
    }

    public void addSession(String key,Object obj){
        this.getSessions().put(key,obj);
    }

    public HashMap<String, Object> getSessions() {
        return sessions;
    }

    public void setSessions(HashMap<String, Object> sessions) {
        this.sessions = sessions;
    }

    public boolean getIsJson() {
        return isJson;
    }

    public void setIsJson(boolean isJson) {
        this.isJson = isJson;
    }
}
