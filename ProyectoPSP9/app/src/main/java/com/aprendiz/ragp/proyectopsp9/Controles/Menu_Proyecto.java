
package com.aprendiz.ragp.proyectopsp9.Controles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.aprendiz.ragp.proyectopsp9.R;

public class Menu_Proyecto extends AppCompatActivity implements View.OnClickListener{

    Button btnTimer, btnDefect, btnPlanSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__proyecto);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        inicializar();
        escuchar();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //Hacemos escuchar el boton para que este pueda funcionar
    private void escuchar() {
        btnTimer.setOnClickListener(this);
        btnDefect.setOnClickListener(this);
        btnPlanSummary.setOnClickListener(this);

    }

    //Referenciamos Los campos
    private void inicializar() {

        btnTimer = findViewById(R.id.btnTimerLog);
        btnDefect = findViewById(R.id.btnDefectLog);
        btnPlanSummary = findViewById(R.id.btnProyectPlanS);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnTimerLog:

                //Funcion  que permite ir de una actividad a otra
                Intent intent = new Intent(Menu_Proyecto.this,TimerLog.class);
                startActivity(intent);

                break;

            case R.id.btnDefectLog:

                Intent intent1 = new Intent(Menu_Proyecto.this,DefectLog.class);
                startActivity(intent1);

                break;

            case R.id.btnProyectPlanS:
                Intent intent2 = new Intent(Menu_Proyecto.this,ProyectPlanSummary.class);
                startActivity(intent2);

                break;

        }

    }
}
