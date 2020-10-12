package com.example.cl03_t5am_dejo_naki

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterCuidad_Dejo(val actividad: Activity) : RecyclerView.Adapter<CiudadViewHolder_Dejo>() {

    var  lista= ArrayList<Ciudad_DejoVicente>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CiudadViewHolder_Dejo {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_ciudad,parent,false)
        return CiudadViewHolder_Dejo(vista)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: CiudadViewHolder_Dejo, position: Int) {

        val CiudadEnPosicion = lista[position]

        holder.lblNombreProvincia.text=CiudadEnPosicion.nombreDepartamento
        holder.lblConfirmados.text=CiudadEnPosicion.totalConfirmados.toString()
        holder.lblMuertos.text=CiudadEnPosicion.totalMuertos.toString()

    }


    fun agregarElementos(nuevaLista : ArrayList<Ciudad_DejoVicente>){

        lista.addAll(nuevaLista)
        notifyDataSetChanged()
    }

}