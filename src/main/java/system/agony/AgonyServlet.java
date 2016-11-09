package system.agony;

import java.io.IOException;
import java.util.Collections;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import system.packages.StaticVariables;
import system.packages.params;

@WebFilter(filterName = "RequestFilter", urlPatterns = {"/*"})
public class AgonyServlet implements Filter {

    ServletSupport objServletSupport = new ServletSupport();
    String folderViewName;

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpRedirection.request = (HttpServletRequest) request; //setting request variable for redirection.
        HttpRedirection.response = (HttpServletResponse) response; //setting response variable for redirection.

        HttpServletRequest req = (HttpServletRequest) request;
        String browserUrl = req.getRequestURI();//gets requested url.
        String contextPath = req.getContextPath();
        setRegularParams(req); // setting regular getParameter() as agony system params
        if (objServletSupport.findContextPath(contextPath, browserUrl)) {
            System.out.println("Running From context path.....");
            if (StaticVariables.checkIfResources(browserUrl)) { // redirection for resources (css,js,image and fonts)
                System.out.println("BrowserURL:::: " + browserUrl);
                System.out.println("CONTEXT PATH:::: " + contextPath.substring(1));
                String regex = "\\s*\\b" + contextPath.substring(1) + "\\b\\s*";
                browserUrl = browserUrl.replaceAll(regex, "");
                if (browserUrl.substring(0, 1).contains("/")) {
                    System.out.println("FINAL URL::: " + browserUrl.substring(1));
                    HttpRedirection.pageRender(browserUrl.substring(1));
                }
                return;
            }
            objServletSupport.classUrlMaker(browserUrl, true);
            objServletSupport.urlParser();
            folderViewName = objServletSupport.classCaller(); // gets method name with class name. need to make dir per each class.
        } else {
            System.out.println("Running from root....");
            if (StaticVariables.checkIfResources(browserUrl)) {
                HttpRedirection.pageRender(browserUrl);
                return;
            }
            objServletSupport.classUrlMaker(browserUrl, false);
            objServletSupport.urlParser();
            folderViewName = objServletSupport.classCaller(); // gets method name with class name. need to make dir per each class.
        }
        checkResponse(req);
        if (StaticVariables.requestRedirect) {
            HttpRedirection.pageRedirection(); // calling method for redirection
        } else {
            HttpRedirection.pageRender(folderViewName); // calling method for page rendering
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    public void setRegularParams(HttpServletRequest request) {
        if (!request.getParameterNames().hasMoreElements()) {
            return;
        }
        for (String keyName : Collections.list(request.getParameterNames())) {
            params.setParams(keyName, request.getParameter(keyName));
        }
    }

    public void checkResponse(HttpServletRequest request) {
        if (params.getParams("exception") != null) {
            request.setAttribute("excep", params.getParams("exception"));
        }
    }

}
