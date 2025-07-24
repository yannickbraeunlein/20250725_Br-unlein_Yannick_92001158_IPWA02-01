package com.ghost;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named("helloBean")
@RequestScoped
public class HelloBean {
    private String name;
    private String greeting;

    public void sayHello() {
        greeting = "Hello, " + name + "! Welcome to the Ghost Net Recovery System.";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGreeting() {
        return greeting;
    }
}
