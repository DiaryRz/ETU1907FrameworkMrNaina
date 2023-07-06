package model;
import annotation.*;
import etu1907.framework.*;

public class Emp {
    String Nom;
    String Prenom;

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }
    
    public Emp(String Nom, String Prenom) {
        this.Nom = Nom;
        this.Prenom = Prenom;
    }
    
    public Emp(){}
    
    @MyAnnotation(value="salut")
    public ModelView Hii(){
        ModelView model = new ModelView();
        model.setView("index.jsp");
        model.AddItem("salutText",1);
        System.out.println("hello world"+model.getData());
        return model;
    }
    
    @MyAnnotation(value="bye")
    public ModelView Aurevoir(){
        ModelView model = new ModelView();
        model.setView("index2.jsp");
        model.AddItem(this.getNom(),this.getNom());
        System.out.println("Good bye");
        return model;
    }
    
    @MyAnnotation(value="Emp-add")
    public ModelView Save(){
        ModelView model = new ModelView();
        model.setView("index.jsp");
        model.AddItem(this.getNom(),this.getNom());
        System.out.print(this.getNom());
        return model;
    }

    
}
