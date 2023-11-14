package com.example.ep2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.lifecycleScope
import com.example.ep2.login.LoginActivity
import com.example.ep2.ui.theme.Color7
import com.example.ep2.utils.UserStore
import com.example.ep2.utils.Util
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.json.JSONArray

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(key1 = true) {
                delay(2000)

                lifecycleScope.launch {
                    val userStore = UserStore(this@SplashActivity)
                    var dato = userStore.getDatosUsuario.first()

                    if(dato == ""){
                        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    } else {
                        Util.usuarioActivo = JSONArray(dato).getJSONObject(0)
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    }
                }

                finish()

            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color7),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.logo)
                )
            }

        }
    }
}