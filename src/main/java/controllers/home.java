package controllers;

import system.packages.params;

public class home {

    public void index() {
//        System.out.println("from controller:::::" + params.Long("id"));
        System.out.println("Params from controller::::: " + params.getParams("test"));
    }

    public void show(String text) {
        System.out.println("Show called");
        System.out.println("TEXT>>>>" + text);
    }

}
