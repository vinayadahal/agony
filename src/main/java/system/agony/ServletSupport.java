package system.agony;

import configs.UrlMapper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import system.packages.params;
import system.packages.Stacktrace;

public class ServletSupport {

    String controller;
    String method;
    String parameter;
    List<String> classUrl = new ArrayList();
    UrlMapper objUrlMapper = new UrlMapper();

    public boolean findContextPath(String contextPath, String browserUrl) {
        String[] ctx = contextPath.split("/");
        String[] brsPath = browserUrl.split("/");
        if (ctx.length < 2) {
            return false;
        }
        return ctx[1].equals(brsPath[1]);
    }

    public void classUrlMaker(String browserUrl, boolean context) {
        if (context) {
            String[] splittedUrl = browserUrl.split("/");
            int j = 0;
            for (int i = 0; i < splittedUrl.length; i++) {
                if (i != 0 && i != 1) {
                    classUrl.add(j++, splittedUrl[i]);
                }
            }
        } else {
            String[] splittedUrl = browserUrl.split("/");
            int j = 0;
            for (String splittedUrlData : splittedUrl) {
                if (!"".equals(splittedUrlData)) {
                    classUrl.add(j++, splittedUrlData);
                }
            }
        }
    }

    public void urlParser() {
        switch (classUrl.size()) {
            case 3: // if url contains controllername, method and parameter.
                System.out.println("case 3");
                controller = classUrl.get(0);
                method = classUrl.get(1);
                params.id = classUrl.get(2);
                classUrl.clear();
                break;
            case 2: // if url contains controllername and method name
                System.out.println("case 2");
                controller = classUrl.get(0);
                method = classUrl.get(1);
                classUrl.clear();
                break;
            case 1: // if url contains only controller only. During this, default method will be called
                System.out.println("case 1");
                controller = classUrl.get(0);
                method = objUrlMapper.defaultMethod; // default method to call if url doesn't contains any methodname
                classUrl.clear();
                break;
            default:
                System.out.println("case default");
                System.out.println("Url error: 404");
                if (controller == null) {
                    System.out.println("controller should not be null");
                    String controllerUrl = objUrlMapper.welcomeFile;
                    String[] splitted = controllerUrl.split("/");
                    controller = splitted[0];
                    method = splitted[1];
                    classUrl.clear();
                }
                break;
        }
        System.out.println("Controller: " + controller);
        System.out.println("Method: " + method);
        System.out.println("params: " + params.id);
    }

    public String classCaller() {
        String path = "controllers." + controller;
        try {
            Class cls = Class.forName(path);
            if (parameter != null) {
                System.out.println("Got Params");
                Class[] argTypes = new Class[]{String.class}; // creates string parameter for class being called
                Object obj = parameter;
                Method classMethod = cls.getMethod(method, argTypes);
                classMethod.invoke(cls.newInstance(), obj);
            } else {
                System.out.println("null params");
                Method classMethod = cls.getMethod(method);
                classMethod.invoke(cls.newInstance());
            }
            return controller + "/" + method;
        } catch (InstantiationException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            System.out.println(Stacktrace.getStackTrace(ex));
            params.setParams("exception", Stacktrace.getStackTrace(ex));
            return "error";
        }
    }

}
