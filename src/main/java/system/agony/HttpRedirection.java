package system.agony;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.packages.Redirection;
import system.packages.StaticVariables;

public class HttpRedirection {

    public static HttpServletResponse response;
    public static HttpServletRequest request;

    public static void pageRedirection() {
        try {
            if (StaticVariables.parameters != null) {
                response.sendRedirect(request.getContextPath() + "/" + StaticVariables.controllerName + "/" + StaticVariables.methodName + "/" + StaticVariables.parameters);
            } else {
                response.sendRedirect(request.getContextPath() + "/" + StaticVariables.controllerName + "/" + StaticVariables.methodName);
            }
        } catch (IOException ex) {
            Logger.getLogger(Redirection.class.getName()).log(Level.SEVERE, null, ex);
        }
        StaticVariables.requestRedirect = false;
    }

    public static void pageRender(String folderViewName) {
        try {
            Map dataForResponse = StaticVariables.responseData;
            for (Object key : dataForResponse.keySet()) {
                System.out.println(key + ": " + dataForResponse.get(key));
                request.setAttribute(key.toString(), dataForResponse.get(key));
            }
            if (checkIfResources(folderViewName)) {
                request.getRequestDispatcher(folderViewName).forward(request, response);
            } else {
                request.getRequestDispatcher("/" + folderViewName + ".jsp").forward(request, response);
            }
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Redirection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean checkIfResources(String path) {
        return path.contains("css") || path.contains("js") || path.contains("images") || path.contains("fonts"); // for dealing with css and js.
    }
}
