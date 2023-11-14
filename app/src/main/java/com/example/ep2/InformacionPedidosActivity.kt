package com.example.ep2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ep2.ui.theme.EP2Theme

class InformacionPedidosActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EP2Theme {
                Column {
                    TopAppBar(
                        title = { Text(text = stringResource(id = R.string.title_activity_pedidos_informacion)) },
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
                }


                Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 60.dp)) {
                    var descripcion by remember { mutableStateOf("") }
                    var monto by remember { mutableStateOf("") }
                    var direccion by remember { mutableStateOf("") }
                    var telefono by remember { mutableStateOf("") }
                    Column(modifier = Modifier.padding(all = 32.dp)) {
                        TextField(value = descripcion,
                            label = { Text(text = "descripcion") },
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {
                                descripcion = it
                            })
                        Spacer(modifier = Modifier.size(16.dp))
                        TextField(value = monto,
                            label = { Text(text = "monto") },
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {
                                monto = it
                            })
                        Spacer(modifier = Modifier.size(16.dp))
                        TextField(value = direccion,
                            label = { Text(text = "direccion") },
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = {
                                direccion = it
                            })
                        Spacer(modifier = Modifier.size(16.dp))
                        TextField(
                            value = telefono,
                            label = { Text(text = "telefono") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            onValueChange = {
                                val onlyDigits = it.filter { it.isDigit() }
                                telefono = onlyDigits
                            }
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        Button(onClick = {
                        }) {
                            Text(text = "Enviar Información")
                        }
                    }
                }
            }

        }
    }
}

