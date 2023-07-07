package model;
import annotation.*;
import etu1907.framework.*;

public class Emp {
    
    public Emp(){}
    
    @MyAnnotation(value="salut")
    public ModelView Hii(){
        ModelView model = new ModelView();
        model.setView("index.jsp");
        return model;
    }
    
    @MyAnnotation(value="bye")
    public ModelView Aurevoir(){
        ModelView model = new ModelView();
        model.setView("index2.jsp");
        System.out.println("Good bye");
        return model;
    }
    
}
