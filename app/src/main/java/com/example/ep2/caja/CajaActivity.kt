package com.example.ep2.caja

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ep2.R
import com.example.ep2.datos.Datos
import com.example.ep2.ui.theme.EP2Theme
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
import androidx.compose.foundation.layout.Box as Column

class CajaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leerDatos()
    }

    private fun leerDatos() {
        val arrayList = ArrayList<HashMap<String, String>>()
        var miVariableString: String = "Hola, Mundo!"
        val datos = Datos(this, miVariableString, null, 0)
        val cursor: Cursor = datos.movimientosSelect(datos)

        if (cursor.moveToFirst()) {
            do {

                val idMovimiento = cursor.getString(cursor.getColumnIndexOrThrow("idMovimiento"))
                val fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha"))
                val descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"))
                val monto = cursor.getString(cursor.getColumnIndexOrThrow("monto"))
                val tipo = cursor.getString(cursor.getColumnIndexOrThrow("tipo"))

                val map = HashMap<String, String>()
                map["idMovimiento"] = idMovimiento
                map["fecha"] = fecha
                map["descripcion"] = descripcion
                map["monto"] = monto
                map["tipo"] = tipo
                arrayList.add(map)

            } while (cursor.moveToNext())


        }


        ///DATOS TOTALES
        val datosTotal = Datos(this, miVariableString, null, 0)
        val cursorTotal: Cursor = datosTotal.movimientosTotal(datosTotal)

        var subTotal = 0.0

        if (cursorTotal.moveToFirst()) {
            subTotal = cursorTotal.getDouble(cursorTotal.getColumnIndexOrThrow("subtotal"))
        }

        ///INGRESOS TOTALES
        val datosI = Datos(this, miVariableString, null, 0)
        val cursorI: Cursor = datosI.movimientosTotalIngresos(datosI)

        var totalI = 0.0

        if (cursorI.moveToFirst()) {
            totalI = cursorI.getDouble(cursorI.getColumnIndexOrThrow("ingreso"))
        }

        ///SALIDA TOTALES
        val datosS = Datos(this, miVariableString, null, 0)
        val cursorS: Cursor = datosS.movimientosTotalGastos(datosS)

        var totalS = 0.0

        if (cursorS.moveToFirst()) {
            totalS = cursorS.getDouble(cursorS.getColumnIndexOrThrow("gastos"))
        }

        dibujar(arrayList, subTotal, totalI, totalS)


    }

    @OptIn(ExperimentalMaterial3Api::class)
    private fun dibujar(arrayList: ArrayList<HashMap<String, String>>, subTotal: Double, ingresos: Double, gastos: Double) {


        setContent {
            EP2Theme {

                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.title_activity_caja)) },
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

                Box(modifier = Modifier.fillMaxSize()) {

                    /*  var double: Double = 123123.123
                     var  respuesta: String = formatNumber(double)
                      Text(text = "Ingresos: S/. $respuesta", style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold))*/


                    Text(text = "Ingresos: S/.${formatNumber(ingresos)}", style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold))
                    Text(text = "Gastos: S/.${formatNumber(gastos)}", style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold))
                    Text(text = "Sub Total: S/.${formatNumber(subTotal)}", style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold))

                    LazyColumn(

                        content = {
                            items(items = arrayList, itemContent = {

                                var tipo = it["tipo"].toString();

                                var color: Color = if (tipo == "1") {
                                    Color.Blue
                                } else {
                                    Color.Red
                                }

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(all = dimensionResource(id = R.dimen.dimen))
                                        .border(width = 1.dp, color = color, shape = RectangleShape)
                                        .padding(all = dimensionResource(id = R.dimen.dimen_2)),
                                ) {

                                    Text(
                                        text = it["idMovimiento"].toString(),
                                        style = MaterialTheme.typography.titleLarge,
                                    )

                                    Text(
                                        text = it["fecha"].toString(),
                                        style = MaterialTheme.typography.titleLarge,
                                    )
                                    Text(
                                        text = it["descripcion"].toString(),
                                        style = MaterialTheme.typography.titleLarge,
                                    )
                                    Text(
                                        text = it["monto"].toString(),
                                        style = MaterialTheme.typography.titleLarge,
                                    )
                                    Text(
                                        text = it["tipo"].toString(),
                                        style = MaterialTheme.typography.titleLarge,
                                    )
                                }

                                /*            Card(
                                                colors = CardDefaults.cardColors(
                                                    containerColor = Color(255, 211, 155, 255)
                                                ),
                                                modifier = Modifier
                                                    .padding(all = dimensionResource(id = R.dimen.espacio2))
                                                    .fillMaxWidth()
                                                    .clickable {
                                                    }
                                            ) {
                                                Column(
                                                    modifier = Modifier.padding(all = dimensionResource(id = R.dimen.espacio))
                                                ) {
                                                    Text(
                                                        text = it["idMovimiento"].toString(),
                                                        style = MaterialTheme.typography.titleLarge,
                                                    )

                                                    Text(
                                                        text = it["fecha"].toString(),
                                                        style = MaterialTheme.typography.titleLarge,
                                                    )
                                                    Text(
                                                        text = it["descripcion"].toString(),
                                                        style = MaterialTheme.typography.titleLarge,
                                                    )
                                                    Text(
                                                        text = it["monto"].toString(),
                                                        style = MaterialTheme.typography.titleLarge,
                                                    )
                                                    Text(
                                                        text = it["tipo"].toString(),
                                                        style = MaterialTheme.typography.titleLarge,
                                                    )
                                                }

                                            }*/

                            })
                        },
                    )



                    FloatingActionButton(
                        onClick = {
                            startActivity(Intent(this@CajaActivity, CajaInsert::class.java))
                        },
                        modifier = Modifier
                            .padding(all = 20.dp)
                            .align(Alignment.BottomEnd)
                    ) { Icon(Icons.Filled.Add, contentDescription = null) }
                }
            }


            /*        EP2Theme {
                        Column {
                      *//*      TopAppBar(
                        title = { Text(text = stringResource(id = R.string.title_activity_caja)) },
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

                    )*//*




                }
            }*/

        }

    }

    fun formatNumber(number: Double): String {
        val decimalFormat = NumberFormat.getInstance(Locale.getDefault()) as DecimalFormat
        decimalFormat.applyPattern("#,##0.00")
        return decimalFormat.format(number)
    }

}