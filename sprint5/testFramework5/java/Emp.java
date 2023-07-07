package model;
import annotation.*;
import etu1907.framework.*;

public class Emp {
    
    public Emp(){}
    
    @MyAnnotation(value="salut")
    public void Hii(){
        System.out.println("hello");
    }
    
    @MyAnnotation(value="bye")
    public void Aurevoir(){
        System.out.println("Good bye");
    }
    
}
