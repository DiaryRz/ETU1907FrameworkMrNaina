package etu1907.framework;

import java.util.HashMap;

public class ModelView {
    String View; 
    HashMap<String,Object> Data;

    public HashMap<String, Object> getData() {
        return Data;
    }

    public String getView() {
        return View;
    }

    public void setView(String View) {
        this.View = View;
    }
    
    public HashMap<String,Object> AddItem(String key,Object obj){
        HashMap<String,Object> hash = new HashMap<String,Object>();
        hash.put(key,obj);
        Data = hash;
        return Data;
    }
    
}
