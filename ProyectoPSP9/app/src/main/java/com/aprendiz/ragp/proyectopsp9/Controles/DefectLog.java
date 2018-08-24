package com.aprendiz.ragp.proyectopsp9.Controles;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiz.ragp.proyectopsp9.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DefectLog extends AppCompatActivity implements View.OnClickListener{
    
    EditText txtDate,txtFix, txtDescripcion;
    Button btnDate, btnGo, btnStop, btnReset;
    Spinner spinnerType, spinnerPhaseInjected, spinnerPhaseRemoved;
    
    Date date;
    
    Thread thread;

    boolean bandera = true;
    boolean bandera1 = false;

    int tiempo [] = {0,0};
    int validar = 0;

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
        txtDate.setText("");
        txtFix.setText("");
        txtDescripcion.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defect_log);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Llamos la flecha back
        
        inicializar();
        escuchar();
        validar();
        Listar();
        cronometro();
    }

    //Creamos un hilo el cual nos ayuda a llevar el cronometro
    private void cronometro() {

        thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (bandera){

                    try {
                        {
                            Thread.sleep(1000);

                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (bandera1){

                                    tiempo[0]++;
                                    if (tiempo[0] == 60){
                                        tiempo[1]++;
                                        tiempo[0] = 0;
                                    }

                                    if (tiempo[0] >= 0 && tiempo [0] <10){

                                        if (tiempo[0] >= 0 && tiempo [0] <10){
                                            txtFix.setText("0" + tiempo[1] + ":" + "0" + tiempo[0]);
                                        }else {

                                            txtFix.setText(tiempo[1] + ":" + "0" + tiempo[0]);
                                        }
                                    }
                                    if (tiempo[0]  >= 10 && tiempo [0] < 60){
                                        if (tiempo[0]  >= 0 && tiempo[0] < 10){
                                            txtFix.setText("0" + tiempo[1] + ":" + tiempo[0]);
                                        }else {

                                            txtFix.setText(tiempo[1] + ":" + tiempo[0]);
                                        }
                                    }
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

   //Creamos una lista Pra agragar los datos de los Spinners
    private void Listar() {

        List<String>Type = new ArrayList<>();
        Type.add("Documentation");
        Type.add("Syntax");
        Type.add("Build");
        Type.add("Package");
        Type.add("Assigment");
        Type.add("Interface");
        Type.add("Checking");
        Type.add("Data");
        Type.add("Function");
        Type.add("System");
        Type.add("Environment");

        List<String>phases = new ArrayList<>();
        phases.add("PLAN");
        phases.add("DLC");
        phases.add("CODE");
        phases.add("COMPILE");
        phases.add("UT");
        phases.add("PM");

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Type);
        spinnerType.setAdapter(adapter);

        ArrayAdapter adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, phases);
        spinnerPhaseInjected.setAdapter(adapter1);
        spinnerPhaseRemoved.setAdapter(adapter1);

    }

    //Validamos los campos para que cuando se registre un defect no haya campos vacios
    private void validar() {

        validar =0;

        if (txtDate.getText().toString().length()>0){
            validar++;

        }else {
            txtDate.setError("You need this field");
        }
        if (txtFix.getText().toString().length()>0){
            validar++;

        }else {
            txtFix.setError("You need this field");
        }

    }

    //Inicializamos los botones
    private void escuchar() {
        btnDate.setOnClickListener(this);
        btnGo.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        
        
        
    }

    //Referenciamos los campos que vamos a utilizar
    private void inicializar() {
        
        txtDate = findViewById(R.id.txtDate);
        txtFix = findViewById(R.id.txtFixTime);
        txtDescripcion = findViewById(R.id.txtDefectDesc);
        
        btnDate = findViewById(R.id.btnDate);
        btnGo = findViewById(R.id.btnGo);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnRestart);
        
        spinnerType = findViewById(R.id.SpinnerType);
        spinnerPhaseInjected = findViewById(R.id.SpinnerPhaseInjected);
        spinnerPhaseRemoved = findViewById(R.id.SpinnerPhaseRemovede);
        
        
    }

    @Override
    public void onClick(View v) {
        
        switch (v.getId()){
            
            case R.id.btnDate:
                
                TomarFecha();
                
                break;

            case R.id.btnGo:

                bandera1 = true;
                Toast.makeText(this, "Go", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnStop:

                bandera1 = false;
                Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();

                break;

            case R.id.btnRestart:

                bandera1 = false;
                tiempo [0] = 0;
                tiempo [1] = 0;
                txtFix.setText("0" + tiempo[1] + ":" + tiempo[0]);

                Toast.makeText(this, "Reset", Toast.LENGTH_SHORT).show();
                break;
        }
        
    }

    //Obtenemos fecha y hora del dispositivo
    private void TomarFecha() {
        
        date = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String fecha1 = fecha.format(date);
        txtDate.setText(fecha1);
        
    }

    public  void inputData(){

    }
}
