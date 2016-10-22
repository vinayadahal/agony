package system.packages;

public class Redirection {

    public static void redirect(String controller, String method) {
        StaticVariables.controllerName = controller;
        StaticVariables.methodName = method;
        StaticVariables.requestRedirect = true;
    }

    public static void redirect(String controller, String method, String params) {
        StaticVariables.controllerName = controller;
        StaticVariables.methodName = method;
        StaticVariables.parameters = params;
        StaticVariables.requestRedirect = true;
    }

}
