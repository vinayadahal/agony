package controllers;

import system.packages.Data;

public class home {

    public void index() {
//        System.out.println("Params from controller::::: " + params.getParams("test"));
//        System.out.println("Params2::::: " + params.getParams("test2"));
        Data.setValue("test", "testing"); // variable for jsp page
    }

    public void show(String text) {
        System.out.println("Show called");
        System.out.println("TEXT>>>>" + text);
    }

}
