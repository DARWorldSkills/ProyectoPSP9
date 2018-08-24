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

import com.aprendiz.ragp.proyectopsp9.Controles.Menu_Proyecto;
import com.aprendiz.ragp.proyectopsp9.MenuPrincipal;
import com.aprendiz.ragp.proyectopsp9.R;
import com.aprendiz.ragp.proyectopsp9.models.AdapterR;
import com.aprendiz.ragp.proyectopsp9.models.ManagerDB;

public class Defect_Inject_In_Phase extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Defect_Inject_In_Phase() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Defect_Inject_In_Phase.
     */
    // TODO: Rename and change types and number of parameters
    public static Defect_Inject_In_Phase newInstance(String param1, String param2) {
        Defect_Inject_In_Phase fragment = new Defect_Inject_In_Phase();
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
        View view = inflater.inflate(R.layout.fragment_defect__inject__in__phase, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewDI);
        ManagerDB managerDB = new ManagerDB(getContext());
        AdapterR adapterR = new AdapterR(managerDB.defectsInjected(MenuPrincipal.project.getId()));
        recyclerView.setAdapter(adapterR);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        return view;
    }




}
