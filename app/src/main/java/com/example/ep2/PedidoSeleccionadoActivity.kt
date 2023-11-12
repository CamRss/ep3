package com.example.ep2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ep2.ui.theme.Color2
import com.example.ep2.ui.theme.Color6
import com.example.ep2.ui.theme.EP2Theme
import org.json.JSONArray



class PedidoSeleccionadoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        if (bundle != null) {

            leerServicio (Idpedido = bundle.getString("idpedido").toString())
        }

        setContent {
            EP2Theme {

            }
        }
    }//onCreate

    private fun leerServicio(Idpedido:String) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://servicios.campus.pe/pedidosdetalle.php?idpedido=" + Idpedido
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("DATOS", response)
                llenarLista(response)
            },

            {
                Log.d("DATOSERROR", it.message.toString())
            })

        queue.add(stringRequest)
    }//leerServicio

    private fun llenarLista(response: String?) {
        val jsonArray = JSONArray(response);
        val arrayList = ArrayList<HashMap<String, String>>()
        for (i in 0 until jsonArray.length()) {
            val idpedido = jsonArray.getJSONObject(i).getString("idpedido")
            val nombre = jsonArray.getJSONObject(i).getString("nombre")
            val detalle = jsonArray.getJSONObject(i).getString("detalle")
            val cantidad = jsonArray.getJSONObject(i).getString("cantidad")
            val imagenchica = jsonArray.getJSONObject(i).getString("imagenchica")
            val map = HashMap<String, String>();
            map.put("idpedido", idpedido)
            map.put("nombre", nombre)
            map.put("detalle", detalle)
            map.put("cantidad", cantidad)
            map.put("imagenchica", imagenchica)



            arrayList.add(map)
        }//for
        dibujar(arrayList)
    }//lenarLista

    private fun dibujar(arrayList: ArrayList<HashMap<String, String>>) {
        setContent {
            LazyRow(
                modifier = Modifier.fillMaxSize(),
                content = {
                    items(items = arrayList, itemContent = { pedido ->
                        Box(
                            modifier = Modifier.fillParentMaxSize()
                        ) {
                            DibujarPedido(pedido)
                        }//Box
                    })

                })//LazyRown
        }//setContent
    }//llenarlista


    @Composable
    fun DibujarPedido(pedido: java.util.HashMap<String, String>) {
        AsyncImage(
            model = "https://servicios.campus.pe/" + pedido["imagenchica"],
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Fit
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 15.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Pedido: " +pedido["idpedido"].toString(),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .background(color = Color6)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                color = Color.White
            )
            Text(
                text = "Producto: " + pedido["nombre"].toString(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .background(color = Color2)
                    .padding(horizontal = 10.dp, vertical = 5.dp),

                )
            Text(
                text = "Cantidad: " + pedido["cantidad"].toString(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .background(color = Color6)
                    .padding(horizontal = 10.dp, vertical = 5.dp),

                )
            Text(
                text = "Detalle: " + pedido["detalle"].toString(),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .background(color = Color6)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                color = Color.White
            )

        }//Column
    }
}















