package com.aprendiz.ragp.proyectopsp9.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aprendiz.ragp.proyectopsp9.MenuPrincipal;
import com.aprendiz.ragp.proyectopsp9.R;
import com.aprendiz.ragp.proyectopsp9.models.AdapterP;
import com.aprendiz.ragp.proyectopsp9.models.AdapterR;
import com.aprendiz.ragp.proyectopsp9.models.CProject;
import com.aprendiz.ragp.proyectopsp9.models.ManagerDB;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link TimeInPhase#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimeInPhase extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    public TimeInPhase() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimeInPhase.
     */
    // TODO: Rename and change types and number of parameters
    public static TimeInPhase newInstance(String param1, String param2) {
        TimeInPhase fragment = new TimeInPhase();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_in_phase, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        final ManagerDB managerDB = new ManagerDB(getContext());
        final EditText txtTiempo = view.findViewById(R.id.txtTiempo);
        txtTiempo.setText(MenuPrincipal.project.getTime());
        Button btnGuardar = view.findViewById(R.id.btnSave);
        //Acción la cual permite guardar el tiempo ingresado en el campo txtTiempo
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tiempo=0;
                try {
                    tiempo = Integer.parseInt(txtTiempo.getText().toString());
                    if (tiempo>1){

                        CProject project = new CProject();
                        project.setTime(tiempo);
                        managerDB.updateProject(project);
                        MenuPrincipal.project.setTime(project.getTime());
                    }else {
                        Toast.makeText(getContext(), "Por favor que el número sea mayor a 0", Toast.LENGTH_SHORT).show();
                        txtTiempo.setError("Por favor que el número sea mayor a 0");
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), "Por favor no ingrese caracteres especiales o no deje vacio", Toast.LENGTH_SHORT).show();
                    txtTiempo.setError("Por favor no ingrese caracteres especiales o no deje vacio");
                }
            }
        });


        return view;
    }


    private void inputAdaptet(){
        ManagerDB managerDB = new ManagerDB(getContext());
        AdapterR adapterR = new AdapterR(managerDB.timeInPhase(MenuPrincipal.project.getId()));
        recyclerView.setAdapter(adapterR);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
    }
}
