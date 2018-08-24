package com.aprendiz.ragp.proyectopsp9.models;

public class CDefectLog {
    //Declaración de variables
    private int id, project;
    private String date, type, phaseI, phaseR, fixtime, comments;

    //Constructor vacio
    public CDefectLog() {
    }

    //Encapsulamiento de todas las variables de la clase CDefectLog
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhaseI() {
        return phaseI;
    }

    public void setPhaseI(String phaseI) {
        this.phaseI = phaseI;
    }

    public String getPhaseR() {
        return phaseR;
    }

    public void setPhaseR(String phaseR) {
        this.phaseR = phaseR;
    }

    public String getFixtime() {
        return fixtime;
    }

    public void setFixtime(String fixtime) {
        this.fixtime = fixtime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
