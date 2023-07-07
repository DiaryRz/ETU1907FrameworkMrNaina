package etu1907.framework;

import etu1907.framework.Mapping;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.lang.Object.*;
import java.lang.reflect.*;


/**
 *
 * @author ITU
 */
public class Utilitaire {
    
    public String[] RecupererString(String urlPath)
    {
        String[] Response = urlPath.split("/") ;
        return Response;
    }
    
    public static List<Class> TousFichiers(String doss,String pack) throws Exception
    {
        String dossier = doss+"WEB-INF\\classes";
        File dir = new File(dossier+"\\"+pack+"\\");
        File[] list = dir.listFiles();
        int m = 0 ;
        List<String> ListString= new ArrayList<String>();
        List<Class> Tout = new ArrayList<Class>();
        for(int i = 0 ; i < list.length ; i++)
        {
            if(list[i].isFile()){
                ListString.add(list[i].getName().split("\\.")[0]);
                Tout.add(Class.forName(pack+"."+ListString.get(m)));
                m++;
            }
        }
        return Tout;
    }
    
    public static List<Method> MakaMethode(String path,String model,String MethodAnn)throws Exception
    {
        List<Class> AllClass = TousFichiers(path,model);
        List<Method> AllResponse = new ArrayList<Method>();
        for(int j =  0; j < AllClass.size() ; j++)
        {
            Method[] All = AllClass.get(j).getDeclaredMethods();
            for(int i = 0 ; i < All.length ; i++)
            {
                Class cl = Class.forName("annotation."+MethodAnn);
                if(All[i].isAnnotationPresent(cl))
                {
                    AllResponse.add(All[i]);
                }
            }
        }
        return AllResponse;
    }
    
    public static List<Mapping> GetAllMethod(String path,String model,String MethodAnn)throws Exception
    {
        List<Class> AllClass = TousFichiers(path,model);
        List<Method> AllResponse = new ArrayList<Method>();
        List<Mapping> map = new ArrayList<Mapping>();
        for(int j =  0; j < AllClass.size() ; j++)
        {
            Method[] All = AllClass.get(j).getDeclaredMethods();
            for(int i = 0 ; i < All.length ; i++)
            {
                Class cl = Class.forName("annotation."+MethodAnn);
                if(All[i].isAnnotationPresent(cl))
                {
                    AllResponse.add(All[i]);
                    String[] clas = String.valueOf(AllClass.get(j)).split(" ")[1].split("\\.");
                    String[] met = String.valueOf(All[i]).split(" ")[2].split("\\.");
                    String methodNom = met[met.length-1].split("\\(")[0] ;
                    map.add(new Mapping(clas[clas.length-1],methodNom));
                }
            }
        }
        return map;
    }
    
    public static HashMap<String,Mapping> getHashMap(String path,String packFonct,String MethodAnn)throws Exception
    {
        List<Mapping> listMap = GetAllMethod(path,packFonct,MethodAnn) ;
        List<Class> NomClass = new ArrayList<Class>();
        HashMap<String,Mapping> hash =  new HashMap<>();
        List<Method> LesMethodes = MakaMethode(path,packFonct,MethodAnn) ;
        for(int i = 0 ; i < LesMethodes.size() ; i++){
            String valeur = LesMethodes.get(i).getAnnotation(annotation.MyAnnotation.class).value(); 
            hash.put(valeur,listMap.get(i));
        }
        return hash;
    }
    
    public static HashMap<String,Mapping> AfficherMethodeClass(HashMap<String,Mapping> MappingUrl,String[] ValeurURL)throws Exception
    {
        HashMap<String,Mapping> hash =  new HashMap<>();
        int h = 0 ;
        for(String i : MappingUrl.keySet()){
            for(int j = 0 ; j < ValeurURL.length ; j++ ){ 
                if(ValeurURL[j].equals(i))
                {
                    hash.put(i, MappingUrl.get(i));
                    System.out.println(hash.get(i).getClassName());
                    h++;
                }
            }
        }
        if(h == 0 ){
            throw new Exception("Cette class n'existe pas");
        }
        return hash;
    }
    
    public static ModelView GetView(HashMap<String,Mapping> MappingUrl,String[] ValeurURL,Object classes)throws Exception
    {
        HashMap<String,Mapping> hash = AfficherMethodeClass(MappingUrl,ValeurURL);
        ModelView model = new ModelView();
        for(String i : hash.keySet()){
            Class cl = Class.forName("model."+hash.get(i).getClassName());
            //Object classes = cl.newInstance();
            Method mt = classes.getClass().getMethod(hash.get(i).getMethod());
            model = (ModelView)mt.invoke(classes);
        }
        System.out.println(model.getData());        
        return model;
    }
    
    public static void main(String[] args) throws Exception{
       HashMap<String,Mapping> mm = getHashMap("E:\\JspNetBins\\LienFrameworkodif\\build\\web\\","model","MyAnnotation");
       String[] val = new String[1] ;
       val[0] = "bye";
       
       //ModelView md = GetView(mm,val);
       //AfficherMethodeClass(mm,val);
       
    }
    
    
    
}
