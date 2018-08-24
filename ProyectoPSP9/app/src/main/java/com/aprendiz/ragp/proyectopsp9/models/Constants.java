package com.aprendiz.ragp.proyectopsp9.models;

public class Constants {
    //Declaración de constantes para la creación de la base de datos psp.db
    public static final String DATABASE_NAME = "psp.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_PROJECT = "CREATE TABLE PROJECT (IDPROJECT INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, TIME INTEGER)";
    public static final String TABLE_TIMELOG = "CREATE TABLE TIMELOG (IDTIMELOG INTEGER PRIMARY KEY AUTOINCREMENT, PHASE TEXT, START TEXT, INTERRUPCIONS INTEGER, STOP TEXT, DELTA INTEGER, COMMENTS TEXT, PROJECT INTEGER ," +
            "FOREIGN KEY (PROJECT) REFERENCES PROJECT (IDPROJECT))";
    public static final String TABLE_DEFECTLOG = "CREATE TABLE DEFECTLOG (IDDEFECTLOG INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, TYPE TEXT, PHASEI TEXT, PHASER TEXT, FIXTIME TEXT, COMMENTS TEXT, PROJECT INTEGER ," +
            "FOREIGN KEY (PROJECT) REFERENCES PROJECT (IDPROJECT))";

    //Lista de las phases
    public static final String [] listPhases={
        "PLAN","DLD","CODE","COMPILE","UT","PM"
    };
}
