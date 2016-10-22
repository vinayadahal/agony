package controllers;

import system.packages.Redirection;
import system.packages.params;

public class login {

    public void index() {
        System.out.println("login index page");
    }

    public void login() {
        System.out.println("username= " + params.getParams("username"));
        System.out.println("password= " + params.getParams("password"));
        Redirection.redirect("home", "index");
    }

}
