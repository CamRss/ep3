package com.example.ep2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ep2.ui.theme.EP2Theme
import org.json.JSONArray


class PedidosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leerServicio();

    }//onCreate

    private fun leerServicio() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://servicios.campus.pe/pedidos.php"
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
    }//leerservicio

    private fun llenarLista(response: String?) {
        val jsonArray = JSONArray(response);
        val arrayList = ArrayList<HashMap<String, String>>()
        for (i in 0 until jsonArray.length()) {
            val idpedido = jsonArray.getJSONObject(i).getString("idpedido")
            val usuario = jsonArray.getJSONObject(i).getString("usuario")
            val nombres = jsonArray.getJSONObject(i).getString("nombres")
            val total = jsonArray.getJSONObject(i).getString("total")
            val map = HashMap<String, String>();
            map.put("idpedido", idpedido)
            map.put("usuario", usuario)
            map.put("nombres", nombres)
            map.put("total", total)
            arrayList.add(map)
        }
        dibujar(arrayList)
    }//llenarlista

    @OptIn(ExperimentalMaterial3Api::class)
    private fun dibujar(arrayList: ArrayList<HashMap<String, String>>) {
        setContent {
            EP2Theme {
                Column {
                    TopAppBar(
                        title = { Text(text = stringResource(id = R.string.title_activity_pedidos)) },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ),
                        navigationIcon = {
                            IconButton(onClick = {
                                finish()
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        }

                    )
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        content = {
                            items(items = arrayList, itemContent = { pedido ->
                                Card(
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(255, 211, 155, 255)
                                    ),
                                    modifier = Modifier
                                        .padding(all = dimensionResource(id = R.dimen.espacio2))
                                        .fillMaxWidth()
                                        .clickable {
                                            seleccionarPedido(pedido)
                                        }
                                ) {
                                    Column(
                                        modifier = Modifier.padding(all = dimensionResource(id = R.dimen.espacio))
                                    ) {
                                        Text(
                                            text = stringResource(id = R.string.cantidad_pedidos) + pedido["idpedido"].toString(),
                                            style = MaterialTheme.typography.titleLarge,
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = stringResource(id = R.string.cantidad_usuario) + pedido["usuario"].toString(),
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Text(
                                            text = stringResource(id = R.string.cantidad_nombres)  + pedido["nombres"].toString(),
                                            style = MaterialTheme.typography.titleMedium
                                        )
                                        Text(
                                            text = stringResource(id = R.string.cantidad_productos)  + pedido["total"].toString()
                                        )
                                    }//column

                                }//card
                            })//items

                        })//LazyColumn

                }//Column
            }//SetContent
        }//Dibujar
    }//Sistematheme



    private fun seleccionarPedido(pedido: HashMap<String, String>) {
        val idpedido = pedido["idpedido"]
        val nombre = pedido["nombre"]
        val descripcion = pedido["descripcion"]
        Toast.makeText(this, nombre, Toast.LENGTH_SHORT).show()
        val bundle = Bundle().apply {
            putString("idpedido", idpedido)
            putString("nombre", nombre)
            putString("descripcion", descripcion)
        }
        val intent = Intent( this, PedidoSeleccionadoActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }//SeleccionarCategoria

}//Class


