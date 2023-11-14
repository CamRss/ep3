package com.example.ep2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ep2.caja.CajaActivity
import com.example.ep2.ui.theme.Color2
import com.example.ep2.ui.theme.EP2Theme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            EP2Theme {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(color = Color2),
                    contentAlignment = Alignment.Center
                )
                {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Image(

                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = stringResource(id = R.string.logo)
                        )
                        Text(
                            text = stringResource(id = R.string.saludo),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(all = dimensionResource(id = (R.dimen.espacio1)))
                        )

                    }
                }

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                ) {

                    Button(modifier = Modifier
                        .padding(all = dimensionResource(id = (R.dimen.espacio3)))
                        .fillMaxWidth(),
                        onClick = {
                            startActivity(Intent(this@MainActivity, PedidosActivity::class.java))
                        }, shape = MaterialTheme.shapes.large
                    ) {
                        Text(text = stringResource(id = R.string.Boton_princi))
                    }

                    Button(modifier = Modifier
                        .padding(all = dimensionResource(id = (R.dimen.espacio3)))
                        .fillMaxWidth(),
                        onClick = {
                            startActivity(Intent(this@MainActivity, CajaActivity::class.java))
                        }, shape = MaterialTheme.shapes.large
                    ) {
                        Text(text = stringResource(id = R.string.boton_caja))
                    }


                    Button(
                        modifier = Modifier
                        .padding(all = dimensionResource(id = (R.dimen.espacio3)))
                        .fillMaxWidth(),
                        onClick = {
                            /*  startActivity(Intent(this@MainActivity, PedidosActivity::class.java))*/
                        }, shape = MaterialTheme.shapes.large
                    ) {
                        Text(text = stringResource(id = R.string.cerrar_sesion))
                    }



                }


            }

        }
    }
}
