
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
}
