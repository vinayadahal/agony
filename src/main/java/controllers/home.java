package controllers;

import system.packages.Data;
import system.packages.params;

public class home {

    public void index() {
//        System.out.println("from controller:::::" + params.Long("id"));
        System.out.println("Params from controller::::: " + params.getParams("test"));
        System.out.println("Params2::::: " + params.getParams("test2"));

        System.out.println("Setting variable for jsp page");

        Data.setValue("test", "testing");
        System.out.println("FROM CONTROLLER:::: "+Data.getValue("test"));

    }

    public void show(String text) {
        System.out.println("Show called");
        System.out.println("TEXT>>>>" + text);
    }

}
