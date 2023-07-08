package model;

import java.util.HashMap;

import etu2054.framework.ModelView;
import etu2054.framework.annotations.Auth;
import etu2054.framework.annotations.UrlAnnot;

public class SessionR {
    HashMap<String,Object> sessions;

    public HashMap<String, Object> getSessions() {
        return sessions;
    }

    public void setSessions(HashMap<String, Object> sessions) {
        this.sessions = sessions;
    }
    
    @UrlAnnot(url = "checkSession.do")
    @Auth
    public ModelView needSession(){
        ModelView modelView = new ModelView();
        modelView.setView("./simple.jsp");
        return modelView;
    }

    @UrlAnnot(url = "checkSessionAdmin.do")
    @Auth(profile = "admin")
    public ModelView needSessionAdmin(){
        ModelView modelView = new ModelView();
        modelView.setView("./admin.jsp");
        return modelView;
    }

}
