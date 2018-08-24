package com.aprendiz.ragp.proyectopsp9.models;

public class CProject {
    //Declaración de variables
    private int id, time;
    private String name;

    //Constructor vacio
    public CProject() {
    }

    //Gettet and Setter para todas las variables de la clase CProject
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
