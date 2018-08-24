package com.aprendiz.ragp.proyectopsp9.Controles;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.aprendiz.ragp.proyectopsp9.MenuPrincipal;
import com.aprendiz.ragp.proyectopsp9.R;
import com.aprendiz.ragp.proyectopsp9.models.AdapterR;
import com.aprendiz.ragp.proyectopsp9.models.AdapterT;
import com.aprendiz.ragp.proyectopsp9.models.CTimeLog;
import com.aprendiz.ragp.proyectopsp9.models.ManagerDB;

import java.util.List;

public class LTimeLog extends AppCompatActivity {
    RecyclerView recyclerView;
    public static int modo=0;
    public static CTimeLog timeLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ltime_log);
        recyclerView = findViewById(R.id.recyclerView);
        inputAdaper();
    }

    private void inputAdaper() {
        final ManagerDB managerDB = new ManagerDB(this);
        final List<CTimeLog> timeLogs = managerDB.timeLogList(MenuPrincipal.project.getId());
        AdapterT adapterT = new AdapterT(timeLogs);
        recyclerView.setAdapter(adapterT);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        adapterT.setMlistener(new AdapterT.OnItemClickListener() {
            @Override
            public void itemClick(final int position) {
                timeLog=timeLogs.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(LTimeLog.this);
                builder.setTitle("¿Qué desea hacer con este TimeLog?");
                builder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        modo=1;
                        Toast.makeText(LTimeLog.this, "Has entrado en modo editar", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LTimeLog.this,TimerLog.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder bEliminar = new AlertDialog.Builder(LTimeLog.this);
                        bEliminar.setTitle("¿Éstas seguro de elimiar el TimeLog?");
                        bEliminar.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                managerDB.deleteTimeLog(timeLog);
                                inputAdaper();
                            }
                        });
                        bEliminar.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        bEliminar.show();
                    }


                });

                builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();

            }
        });
    }
}
