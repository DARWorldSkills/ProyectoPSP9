package com.aprendiz.ragp.proyectopsp9.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ManagerDB {
    //Declaración de variables
    Context context;
    GestorDB gestorDB;
    SQLiteDatabase db;

    //Constructor para la utilización de la clase ManagerDB
    public ManagerDB(Context context) {
        this.context = context;
    }

    //Método para abrir la base de datos en modo lectura
    private void openReadDB(){
        gestorDB = new GestorDB(context);
        db= gestorDB.getReadableDatabase();
    }

    //Método para abrir la base de datos en modo escritura
    private void openWriteDB(){
        gestorDB = new GestorDB(context);
        db= gestorDB.getWritableDatabase();
    }

    //Método para cerrar la base de datos
    private void closeDB(){
        db.close();
    }

    //Método para ingresar valores a la table PROJECT
    public void insertProject(CProject project){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("NAME",project.getName());
        values.put("TIME",project.getTime());
        db.insert("PROJECT",null,values);
        closeDB();
    }

    //Método para ingresar valores a la tabla TIMELOG
    public void insertTimeLog(CTimeLog timeLog){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("PHASE",timeLog.getPhase());
        values.put("START",timeLog.getStart());
        values.put("INTERRUPCIONS",timeLog.getInterruptions());
        values.put("STOP",timeLog.getStop());
        values.put("DELTA",timeLog.getDelta());
        values.put("COMMENTS",timeLog.getComments());
        values.put("PROJECT",timeLog.getProject());
        db.insert("TIMELOG",null,values);
        closeDB();
    }

    //Método para ingresar valores a la tabla DEFECTLOG
    public void insertDefectLog(CDefectLog cDefectLog){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("DATE",cDefectLog.getDate());
        values.put("TYPE",cDefectLog.getType());
        values.put("PHASEI",cDefectLog.getPhaseI());
        values.put("PHASER",cDefectLog.getPhaseR());
        values.put("FIXTIME",cDefectLog.getFixtime());
        values.put("COMMENTS",cDefectLog.getComments());
        values.put("PROJECT",cDefectLog.getProject());
        db.insert("DEFECTLOG",null,values);
        closeDB();
    }

    //Método para actualizar valores a la tabla PROJECT
    public void updateProject(CProject project){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("TIME",project.getTime());
        db.update("PROJECT",values,"IDPROJECT="+project.getId(),null);
        closeDB();
    }

    //Método para actualizar valores a la tabla TIMELOG
    public void updateTimeLog(CTimeLog timeLog){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("PHASE",timeLog.getPhase());
        values.put("START",timeLog.getStart());
        values.put("INTERRUPCIONS",timeLog.getInterruptions());
        values.put("STOP",timeLog.getStop());
        values.put("DELTA",timeLog.getDelta());
        values.put("COMMENTS",timeLog.getComments());
        db.update("TIMELOG",values,"IDTIMELOG="+timeLog.getId(),null);
        closeDB();
    }

    //Método para eliminar valores a la tabla DEFECTLOG
    public void updateDefectLog(CDefectLog cDefectLog){
        openWriteDB();
        ContentValues values = new ContentValues();
        values.put("DATE",cDefectLog.getDate());
        values.put("TYPE",cDefectLog.getType());
        values.put("PHASEI",cDefectLog.getPhaseI());
        values.put("PHASER",cDefectLog.getPhaseR());
        values.put("FIXTIME",cDefectLog.getFixtime());
        values.put("COMMENTS",cDefectLog.getComments());
        db.update("DEFECTLOG",values,"IDDEFECTLOG="+cDefectLog.getId(),null);
        closeDB();
    }


    //Método para eliminar valores a la tabla PROJECT
    public void deleteProject(CProject project){
        openWriteDB();
        db.delete("PROJECT","IDPROJECT="+project.getId(),null);
        closeDB();
    }

    //Método para eliminar valores a la tabla TIMELOG
    public void deleteTimeLog(CTimeLog timeLog){
        openWriteDB();
        db.delete("TIMELOG","IDTIMELOG="+timeLog.getId(),null);
        closeDB();
    }

    //Método para eliminar valores a la tabla DEFECTLOG
    public void deleteDefectLog(CDefectLog cDefectLog){
        openWriteDB();
        db.delete("DEFECTLOG","IDDEFECTLOG="+cDefectLog.getId(),null);
        closeDB();
    }

    //Fucnión para el listado de valores de la tabla PROJECT
    public List<CProject> projectList(){
        List<CProject> results = new ArrayList<>();
        openReadDB();
        Cursor cursor = db.rawQuery("SELECT * FROM PROJECT ORDER BY IDPROJECT DESC;",null);
        if (cursor.moveToFirst()){
            do {
                CProject project = new CProject();
                project.setId(cursor.getInt(0));
                project.setName(cursor.getString(1));
                project.setTime(cursor.getInt(2));
                results.add(project);
            }while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();

        return results;
    }


    //Fucnión para el listado de valores de la tabla TIMELOG
    public List<CTimeLog> timeLogList(int project){
        List<CTimeLog> results = new ArrayList<>();
        openReadDB();
        Cursor cursor = db.rawQuery("SELECT * FROM TIMELOG WHERE PROJECT ="+project+";",null);
        if (cursor.moveToFirst()){
            do {
                CTimeLog timeLog = new CTimeLog();
                timeLog.setId(cursor.getInt(0));
                timeLog.setPhase(cursor.getString(1));
                timeLog.setStart(cursor.getString(2));
                timeLog.setInterruptions(cursor.getInt(3));
                timeLog.setStop(cursor.getString(4));
                timeLog.setDelta(cursor.getInt(5));
                timeLog.setComments(cursor.getString(6));
                timeLog.setProject(cursor.getInt(7));
                results.add(timeLog);
            }while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();

        return results;
    }


    //Fucnión para el listado de valores de la tabla DEFECTLOG
    public List<CDefectLog> defectLogList(int project){
        List<CDefectLog> results = new ArrayList<>();
        openReadDB();
        Cursor cursor = db.rawQuery("SELECT * FROM DEFECTLOG WHERE PROJECT ="+project+";",null);
        if (cursor.moveToFirst()){
            do {
                CDefectLog defectLog = new CDefectLog();
                defectLog.setId(cursor.getInt(0));
                defectLog.setDate(cursor.getString(1));
                defectLog.setType(cursor.getString(2));
                defectLog.setPhaseI(cursor.getString(3));
                defectLog.setPhaseR(cursor.getString(4));
                defectLog.setFixtime(cursor.getString(5));
                defectLog.setComments(cursor.getString(6));
                defectLog.setProject(cursor.getInt(7));
                results.add(defectLog);
            }while (cursor.moveToNext());
        }
        cursor.close();
        closeDB();

        return results;
    }

    //Consulta para el Time In Phase
    public List<Results> timeInPhase(int project){
        List<Results> resultsList = new ArrayList<>();
        openReadDB();
        Cursor cursor= db.rawQuery("SELECT TIME FROM PROJECT WHERE IDPROJECT="+project+";",null);
        int tiempo =0;
        if (cursor.moveToFirst()){
            do {
                tiempo=cursor.getInt(0);
            }while (cursor.moveToNext());
        }
        cursor.close();
        String [] listPhases = Constants.listPhases;
        int acumulado=0;
        for (int i=0; i<listPhases.length; i++){
            Cursor cursorM = db.rawQuery("SELECT DELTA FROM TIMELOG WHERE PHASE='"+listPhases[i]+"' AND PROJECT ="+project+" ;",null);
            if (cursorM.moveToFirst()){
                do {
                    acumulado+=cursorM.getInt(0);
                }while (cursorM.moveToNext());
            }
            Results results = new Results();

            try {
                float tmp1 = tiempo, tmp2=acumulado;
                double tmpT = (tmp2/tmp1) *100;
                BigDecimal bigDecimal = new BigDecimal(tmpT);
                float tmpF = bigDecimal.setScale(3, RoundingMode.HALF_UP).floatValue();
                results.setPercent(tmpF);

            }catch (Exception e){
                results.setPercent(0);
            }
            results.setTime(acumulado);
            results.setPhase(listPhases[i]);
            resultsList.add(results);

        }

        return resultsList;
    }


    //Consulta para los Defects injected in phase
    public List<Results> defectsInjected(int project){
        List<Results> resultsList = new ArrayList<>();
        openReadDB();
        Cursor cursor= db.rawQuery("SELECT * FROM DEFECTLOG WHERE PROJECT="+project+";",null);
        int tiempo =0;
        if (cursor.moveToFirst()){
            do {
                tiempo++;
            }while (cursor.moveToNext());
        }
        cursor.close();
        String [] listPhases = Constants.listPhases;
        int acumulado=0;
        for (int i=0; i<listPhases.length; i++){
            Cursor cursorM = db.rawQuery("SELECT * FROM DEFECTLOG WHERE PHASEI='"+listPhases[i]+"' AND PROJECT ="+project+" ;",null);
            if (cursorM.moveToFirst()){
                do {
                    acumulado++;
                }while (cursorM.moveToNext());
            }
            Results results = new Results();

            try {
                float tmp1 = tiempo, tmp2=acumulado;
                double tmpT = (tmp2/tmp1) *100;
                BigDecimal bigDecimal = new BigDecimal(tmpT);
                float tmpF = bigDecimal.setScale(3, RoundingMode.HALF_UP).floatValue();
                results.setPercent(tmpF);

            }catch (Exception e){
                results.setPercent(0);
            }
            results.setTime(acumulado);
            results.setPhase(listPhases[i]);
            resultsList.add(results);

        }

        return resultsList;
    }

    //Consulta para los Defects removed in phase
    public List<Results> defectsRemoved(int project){
        List<Results> resultsList = new ArrayList<>();
        openReadDB();
        Cursor cursor= db.rawQuery("SELECT * FROM DEFECTLOG WHERE PROJECT="+project+";",null);
        int tiempo =0;
        if (cursor.moveToFirst()){
            do {
                tiempo++;
            }while (cursor.moveToNext());
        }
        cursor.close();
        String [] listPhases = Constants.listPhases;
        int acumulado=0;
        for (int i=0; i<listPhases.length; i++){
            Cursor cursorM = db.rawQuery("SELECT * FROM DEFECTLOG WHERE PHASER='"+listPhases[i]+"' AND PROJECT ="+project+" ;",null);
            if (cursorM.moveToFirst()){
                do {
                    acumulado++;
                }while (cursorM.moveToNext());
            }
            Results results = new Results();

            try {
                float tmp1 = tiempo, tmp2=acumulado;
                double tmpT = (tmp2/tmp1) *100;
                BigDecimal bigDecimal = new BigDecimal(tmpT);
                float tmpF = bigDecimal.setScale(3, RoundingMode.HALF_UP).floatValue();
                results.setPercent(tmpF);

            }catch (Exception e){
                results.setPercent(0);
            }
            results.setTime(acumulado);
            results.setPhase(listPhases[i]);
            resultsList.add(results);

        }

        return resultsList;
    }






}
