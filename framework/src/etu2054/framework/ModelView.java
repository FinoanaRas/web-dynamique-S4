package etu2054.framework;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelView {
    String view;
    HashMap<String,Object> data;
    HashMap<String,Object> sessions;
    boolean isJson;
    boolean invalidate;
    List<String> removeSession;

    

    public List<String> getRemoveSession() {
        return removeSession;
    }

    public void setRemoveSession(List<String> removeSession) {
        this.removeSession = removeSession;
    }

    public boolean isInvalidate() {
        return invalidate;
    }

    public void setInvalidate(boolean invalidate) {
        this.invalidate = invalidate;
    }

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
        setRemoveSession(new ArrayList<String>());
    }

    public void addItem(String key,Object obj){
        this.getData().put(key,obj);
    }

    public void addSession(String key,Object obj){
        this.getSessions().put(key,obj);
    }

    public void removeSession(String session){
        this.getRemoveSession().add(session);
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
