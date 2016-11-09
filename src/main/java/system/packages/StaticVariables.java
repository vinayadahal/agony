package system.packages;

import java.util.HashMap;
import java.util.Map;

public class StaticVariables {

    public static Boolean requestRedirect = false;
    public static String controllerName = null;
    public static String methodName = null;
    public static String parameters = null;
    public static Map responseData = new HashMap();

    public static boolean checkIfResources(String path) {
        return path.contains("css") || path.contains("js") || path.contains("images") || path.contains("fonts"); // for dealing with css and js.
    }
}
