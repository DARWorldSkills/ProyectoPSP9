package com.aprendiz.ragp.proyectopsp9.Controles;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.aprendiz.ragp.proyectopsp9.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimerLog extends AppCompatActivity implements View.OnClickListener{


    EditText txtStart, txtInterrupcion, txtStop, txtDelta, txtComentario;
    Button btnStart, btnStop;
    Spinner spinnerPhase;

    SharedPreferences sharedPreferences;
    Date dateStart, dateStop;

    int delta = 0;

    int interrupciones = 0;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    limpiar();
                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:

                    return true;
            }
            return false;
        }
    };

    private void limpiar() {

        txtStart.setText("");
        txtStop.setText("");
        txtInterrupcion.setText("");
        txtDelta.setText("");
        txtComentario.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_log);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true); //Generamos la flecha de back

        inicializar();
        escuchar();
        Listar();
        capturar();
    }

    private void capturar() {

        txtStart.setText(sharedPreferences.getString("start ", ""));
    }

    @Override
    public void onBackPressed() {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("start", txtStart.getText().toString());
        editor.commit();


        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //Función que hace retrocer por medio de la flecha back
        if (id == android.R.id.home){

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //Validamos que los campos no estes vacíos cuando se vaya a registrar
    private void validar() {

        int validar = 0;
        if (txtStart.getText().toString().length()>0){
            validar++;
        }else{
            txtStart.setError("You need this field");
        }
        if (txtStop.getText().toString().length()>0){
            validar++;


        }else {
            txtStop.setError("You need this field");
        }
        if (delta >= 0){  // Validamos que el resultado de delta no puede ser negativo
            validar++;
        }else {

            txtDelta.setError("This field can not negative");
        }

    }

    //Creamos una lista para agregar los datos al spinner
    private void Listar() {

        List<String>phase = new ArrayList<>();
        phase.add("PLAN");
        phase.add("DLC");
        phase.add("CODE");
        phase.add("COMPILE");
        phase.add("UT");
        phase.add("PM");

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, phase);
        spinnerPhase.setAdapter(adapter);

    }

    private void escuchar() {

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    //Referenciamos
    private void inicializar() {

        txtStart = findViewById(R.id.txtStart);
        txtInterrupcion = findViewById(R.id.txtInterrupciones);
        txtStop = findViewById(R.id.txtStop);
        txtDelta = findViewById(R.id.txtDelta);
        txtComentario = findViewById(R.id.txtComentarios);


        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        spinnerPhase = findViewById(R.id.spinnerPhase);

        sharedPreferences = getSharedPreferences("start", Context.MODE_PRIVATE); // Creo el archivo

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnStart:

                obtenerHora();

                break;

            case R.id.btnStop:

                obtenerHora2();
                calcularDelta();

                break;
        }

    }

    //Calcamos el resultado de delta haciendo una operacion para que sea en minutos
    private void calcularDelta() {

        calcularInterrupciones();
        double diferencia = dateStop.getTime() - dateStart.getTime();
        delta = (int) ((diferencia / 60000)) - interrupciones;
        txtDelta.setText(Integer.toString(delta));


    }

    //Calculamos las interrupciones que hay en el proyecto
    private void calcularInterrupciones() {

        try {
            interrupciones = Integer.parseInt(txtInterrupcion.getText().toString());

        }catch (Exception e){
            interrupciones = 0;

        }

    }

    //Obtenemos la fehca y hora del dispositivo
    private void obtenerHora2() {

        dateStop = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String fecha1 = fecha.format(dateStop);
        txtStop.setText(fecha1);

    }

    //Obtenemos la fehca y hora del dispositivo
    private void obtenerHora() {

        dateStart = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String fecha1 = fecha.format(dateStart);
        txtStart.setText(fecha1);
    }
}
