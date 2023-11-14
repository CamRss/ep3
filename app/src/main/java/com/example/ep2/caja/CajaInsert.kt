package com.example.ep2.caja

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ep2.R
import com.example.ep2.datos.Datos
import com.example.ep2.ui.theme.EP2Theme

class CajaInsert : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            EP2Theme {

                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.title_activity_ingresar)) },
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


                Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 60.dp)) {
                    var mensaje: String = "";
                    var switchState by remember { mutableStateOf(false) }
                    var monto by remember { mutableStateOf("") }
                    var descripcion by remember { mutableStateOf("") }

                    Column(modifier = Modifier.padding(all = 24.dp)) {

                        mensaje = if (switchState) {
                            "Ingreso"
                        } else {
                            "Salida"
                        }

                        Text(text = mensaje)

                        Switch(
                            checked = switchState,
                            onCheckedChange = { newSwitchState ->
                                switchState = newSwitchState
                            },
                            modifier = Modifier.padding(8.dp)
                        )

                        Spacer(modifier = Modifier.size(12.dp))

                        TextField(
                            value = monto,
                            label = { Text(text = "Monto") },
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {
                                monto = it
                            }
                        )

                        Spacer(modifier = Modifier.size(12.dp))

                        TextField(
                            value = descripcion,
                            label = { Text(text = "Descripcion") },
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {
                                descripcion = it
                            }
                        )

                        Spacer(modifier = Modifier.size(12.dp))

                        Button(
                            onClick = {
                                guardarDatos(monto, descripcion, switchState)
                            }
                        ) {
                            Text("Guardar")
                        }

                    }
                }

            }

        }
    }

    private fun guardarDatos(monto: String, descripcion: String, ingreso: Boolean) {

        if (monto != "" || descripcion != "") {
            var miVariableString: String = "Hola, Mundo!"
            val datos = Datos(this, miVariableString, null, 0)

            var tipo: Int = 0;

            if (ingreso) {
                tipo = 1
            } else {
                tipo = -1
            }


            val response = datos.registrarMovimientos(datos, descripcion, monto.toFloat(), tipo)




            Toast.makeText(this, "id:$response", Toast.LENGTH_SHORT).show();
            startActivity(Intent(this@CajaInsert, CajaActivity::class.java))
        } else {
            Toast.makeText(this, "Debe ingresar un monto y descripción", Toast.LENGTH_LONG).show();
        }

    }

}