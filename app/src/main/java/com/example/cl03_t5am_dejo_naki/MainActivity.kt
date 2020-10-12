package com.example.cl03_t5am_dejo_naki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONException
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_ciudad.*

/***
 * APLICACION COVID - USANDO VOLLEY Y RECYCLER VIEW
 * URL: https://covidapi.com/api/reports?q=Peru&iso=PER
 * NOMBRES Y APELLIDOS  : DEJO VICENTE, NAKI
 * Consumo -> Tipo: REST
 *         -> Método: GET
 */

class MainActivity : AppCompatActivity() {

    private var listaCiudades: ArrayList<Ciudad_DejoVicente> = ArrayList()
    private var request: RequestQueue? = null
    private var xtotalConfirmados: Int?=0
    private var xtotalMuertos: Int?=0
    private var xtotalRecuperados: Int?=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        request = Volley.newRequestQueue(this)

        rvCovid.layoutManager = LinearLayoutManager(this)
        val adaptador = AdapterCuidad_Dejo(this)
        rvCovid.adapter = adaptador


        val jsonRequest = JsonObjectRequest(
            "https://covid-api.com/api/reports?q=Peru&iso=PER",
            null,
            { response ->
                try {
                    val dataCabecera = response["data"] as? JSONArray
                    if (dataCabecera != null) {
                        for (contador in 0 until dataCabecera.length()) {
                            val dataCuerpo = dataCabecera.getJSONObject(contador)

                            val totalConfirmados = dataCuerpo["confirmed"] as? Int ?: 0
                            val totalMuertos = dataCuerpo.getInt("deaths")
                            val totalRecuperados = dataCuerpo.getInt("recovered")

                            //REGION - OBTENIENDO SUS DATOS
                            val region = dataCuerpo.getJSONObject("region")
                            val nombreDepartamento = region.getString("province")

                            Log.i("Depa : ", nombreDepartamento)
                            val ciudad = Ciudad_DejoVicente(
                                nombreDepartamento,
                                totalConfirmados,
                                totalMuertos,
                                totalRecuperados
                            )
                            xtotalConfirmados = xtotalConfirmados?.plus(totalConfirmados)
                            xtotalMuertos =xtotalMuertos?.plus(totalMuertos)
                            xtotalRecuperados =xtotalRecuperados?.plus(totalRecuperados)

                            listaCiudades.add(ciudad)
                        }
                        txtTotalConfirmados.text=xtotalConfirmados.toString()
                        txtTotalMuertos.text=xtotalMuertos.toString()
                        txtTotalRecuperados.text=xtotalRecuperados.toString()
                        listaCiudades.removeAt(listaCiudades.size - 1)
                        adaptador.agregarElementos(listaCiudades)


                    }

                    Log.i("RES ", response.toString())

                } catch (joex: JSONException) {
                    println("---------ERROR--EN--EL--JSON-------- $joex")
                }
            },
            { error ->
                print(error)
            }
        )
        request?.add(jsonRequest)

    }


}