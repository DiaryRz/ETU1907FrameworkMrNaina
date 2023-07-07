package model;
import annotation.*;
import etu1907.framework.*;

public class Emp {
    
    public Emp(){}
    
    @MyAnnotation(value="salut")
    public ModelView Hii(){
        ModelView model = new ModelView();
        model.setView("index.jsp");
        double[] valeur = new double[2];
            valeur[0] = 0;
            valeur[1] = 1;
        model.AddItem("salutText",valeur);
        System.out.println("hello world"+model.getData());
        return model;
    }
    
    @MyAnnotation(value="bye")
    public ModelView Aurevoir(){
        ModelView model = new ModelView();
        model.setView("index2.jsp");
        model.AddItem("byetext",4);
        System.out.println("Good bye");
        return model;
    }
    
}
