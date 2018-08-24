package com.aprendiz.ragp.proyectopsp9.models;

public class CTimeLog {
    //Declaración de variables
    private int id, interruptions, delta, project;
    private String phase, start, stop, comments;

    //Constructor vacio
    public CTimeLog() {
    }

    //Getter and Setter para todas las variables de la clase CTimeLog
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInterruptions() {
        return interruptions;
    }

    public void setInterruptions(int interruptions) {
        this.interruptions = interruptions;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
