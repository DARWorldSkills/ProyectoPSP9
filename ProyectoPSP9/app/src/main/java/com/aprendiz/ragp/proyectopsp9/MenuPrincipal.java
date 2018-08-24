package com.aprendiz.ragp.proyectopsp9;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aprendiz.ragp.proyectopsp9.Controles.Menu_Proyecto;
import com.aprendiz.ragp.proyectopsp9.models.AdapterP;
import com.aprendiz.ragp.proyectopsp9.models.CProject;
import com.aprendiz.ragp.proyectopsp9.models.ManagerDB;

import java.util.List;

public class MenuPrincipal extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton btnIngresar;

    public static CProject project=new CProject();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        inizialite();
        inputAdapter();
        inputClick();
    }

    //Método para inicializar los views
    private void inizialite() {
        recyclerView = findViewById(R.id.recyclerView);
        btnIngresar = findViewById(R.id.btnIngresar);
    }

    //Método para ingresar el adapatador al recyclerView
    private void inputAdapter() {
        ManagerDB managerDB = new ManagerDB(this);
        final List<CProject> projectList = managerDB.projectList();
        AdapterP adapterP = new AdapterP(projectList);
        recyclerView.setAdapter(adapterP);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        adapterP.setMlistener(new AdapterP.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                project = projectList.get(position);
                Intent intent = new Intent(MenuPrincipal.this, Menu_Proyecto.class);
                startActivity(intent);

            }
        });
    }

    //Método para el click del votoón btningresar para mostar un dialog el cual se ingresará un proyecto
    private void inputClick() {
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MenuPrincipal.this);
                dialog.setContentView(R.layout.item_aproject);
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                final EditText txtNombre = dialog.findViewById(R.id.txtNombrePro);
                final EditText txtTiempo = dialog.findViewById(R.id.txtTiempo);
                Button btnAceptar = dialog.findViewById(R.id.btnAceptar);
                Button btnCancelar = dialog.findViewById(R.id.btnCancelar);
                btnAceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tiempo=0;
                        String nombre = txtNombre.getText().toString();
                        if (nombre.length()>0) {
                            try {
                                tiempo = Integer.parseInt(txtTiempo.getText().toString());
                                if (tiempo > 0) {
                                    ManagerDB managerDB = new ManagerDB(MenuPrincipal.this);
                                    CProject project = new CProject();
                                    project.setName(nombre);
                                    project.setTime(tiempo);
                                    managerDB.insertProject(project);
                                    Toast.makeText(MenuPrincipal.this, "Se ha ingresado el proyecto correctamente", Toast.LENGTH_SHORT).show();
                                    inputAdapter();
                                    dialog.cancel();
                                }else {
                                    Toast.makeText(MenuPrincipal.this, "Por favor el valor del tiempo no puede ser negativo o 0", Toast.LENGTH_SHORT).show();
                                    txtTiempo.setError("Por favor este valor no puede ser negativo o 0");
                                }
                            } catch (Exception e) {
                                Toast.makeText(MenuPrincipal.this, "Hay un campo incompleto", Toast.LENGTH_SHORT).show();
                                txtTiempo.setError("Por favor no deje este campo incompleto");

                            }
                        }else{
                            try {
                                tiempo = Integer.parseInt(txtTiempo.getText().toString());
                                if (tiempo > 0) {
                                }else {
                                    Toast.makeText(MenuPrincipal.this, "Por favor el valor del tiempo no puede ser negativo o 0", Toast.LENGTH_SHORT).show();
                                    txtTiempo.setError("Por favor este valor no puede ser negativo o 0");
                                }
                            } catch (Exception e) {
                                txtTiempo.setError("Por favor no deje este campo incompleto");

                            }

                            Toast.makeText(MenuPrincipal.this, "Hay un campo incompleto", Toast.LENGTH_SHORT).show();
                            txtNombre.setError("Por favor no deje este campo incompleto");


                        }


                    }
                });

                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });
    }
}
