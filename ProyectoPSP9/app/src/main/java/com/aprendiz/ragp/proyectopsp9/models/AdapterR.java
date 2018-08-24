package com.aprendiz.ragp.proyectopsp9.models;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aprendiz.ragp.proyectopsp9.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterR extends RecyclerView.Adapter<AdapterR.Holder> {
    //Declaración de variables
    List<Results> resultsList=new ArrayList<>();
    private OnItemClickListener mlistener;
    //Interface par escuchar cuando se hace un click a un item del RecyclerView
    public interface OnItemClickListener{
        void itemClick(int position);
    }

    //Constructor para la utilizacion de la clase AdapterP


    public AdapterR(List<Results> resultsList) {
        this.resultsList = resultsList;
    }

    //Método para el ingreso de la variable mlistener
    public void setMlistener(OnItemClickListener mlistener) {
        this.mlistener = mlistener;
    }

    //Función para la creacion del holder
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project,parent,false);
        Holder holder = new Holder(view,mlistener);
        return holder;
    }

    //Método para el llamado de los metodos del objeto holder
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.connectData(resultsList.get(position));
    }

    //Función que determina el número de items a mostart
    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    //Clase Holder para la creación de los items del RecyclerView
    public class Holder extends RecyclerView.ViewHolder {
        public Holder(View itemView, final OnItemClickListener mlistener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mlistener!=null){
                        int position = getLayoutPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mlistener.itemClick(position);
                        }
                    }

                }
            });
        }

        //Método para el ingreso de datos al item
        public void connectData(Results results){
            TextView txtNombre = itemView.findViewById(R.id.txtNombreP);
            txtNombre.setText(results.getPhase());
        }
    }
}
