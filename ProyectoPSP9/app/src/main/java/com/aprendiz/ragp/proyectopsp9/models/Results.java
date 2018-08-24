package com.aprendiz.ragp.proyectopsp9.models;

public class Results {
    //Declaración de variables
    private String phase;
    private int time;
    private float percent;

    //Constructor vacio
    public Results() {
    }

    //Encapsulamiento de todas las variables de la clase Results
    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
