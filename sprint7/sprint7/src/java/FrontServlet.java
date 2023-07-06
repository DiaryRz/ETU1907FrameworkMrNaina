/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import etu1907.framework.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Emp;
/**
 *
 * @author ITU
 */
public class FrontServlet extends HttpServlet {

    HashMap<String,Mapping> MappingUrl; 

    public void init() throws ServletException{
        try {
            String path = getServletContext().getRealPath("/") ;
            MappingUrl = Utilitaire.getHashMap(path,"model","MyAnnotation") ;
        } catch (Exception ex) {
            Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FrontServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FrontServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            
            Utilitaire appelUtilitaire = new Utilitaire();
            String urlPath = request.getPathInfo();
            
            String[] Response = appelUtilitaire.RecupererString(urlPath) ;
            for(int i = 0 ; i < Response.length ; i++)
            {
                //out.println(Response[i]);
            }
            
            try{
                
                String[] ValeurURL = appelUtilitaire.RecupererString(urlPath) ;
                HashMap<String,Mapping> valiny = appelUtilitaire.AfficherMethodeClass(MappingUrl,ValeurURL) ;
                for(String i : valiny.keySet()){
                    //out.println("<p>");
                    //out.println("La methode qui a cette valeur est :"+valiny.get(i).getMethod());  
                    //out.println("</p>");
                    //out.println("La class ou se trouve cette method est :"+valiny.get(i).getClassName());
                    Class classe = Class.forName("model."+valiny.get(i).getClassName());
                    List<String> nomsChamps = new ArrayList<>(request.getParameterMap().keySet());
                    Object instance = classe.newInstance();
                    Field[] attribut = classe.getDeclaredFields() ;
                    for(Field field : attribut){
                        for (String nomChamp : nomsChamps) {
                            if(nomChamp.equals(field.getName())){
                                instance.getClass().getMethod("set"+field.getName(), String.class).invoke(instance,request.getParameter(nomChamp));
                                Emp a = (Emp)instance;
                                out.print(a.getPrenom());
                                ModelView view = Utilitaire.GetView(MappingUrl,ValeurURL,instance);
                                request.setAttribute("myObject", view.getData());
                                RequestDispatcher dispatcher = request.getRequestDispatcher("/"+view.getView());
                                dispatcher.forward(request, response);
                            }
                        }
                    }
                }
                
                //response.sendRedirect(request.getContextPath()+"/"+view.getView());
                
               
                
            }catch(Exception e){out.print(e);}
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
