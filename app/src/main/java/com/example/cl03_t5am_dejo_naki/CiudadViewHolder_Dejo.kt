package com.example.cl03_t5am_dejo_naki

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CiudadViewHolder_Dejo(vista: View) : RecyclerView.ViewHolder(vista){

    val lblNombreProvincia: TextView =vista.findViewById(R.id.txtProvincia)
    val lblConfirmados= vista.findViewById<TextView>(R.id.txtConfirmadosProv)
    val lblMuertos: TextView =vista.findViewById(R.id.txtMuertosProv)


}