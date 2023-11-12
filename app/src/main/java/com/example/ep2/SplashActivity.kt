package com.example.ep2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.ep2.ui.theme.Color1
import com.example.ep2.ui.theme.Color2
import com.example.ep2.ui.theme.Color3
import com.example.ep2.ui.theme.Color7
import com.example.ep2.ui.theme.EP2Theme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchedEffect(key1 = true){
                delay(3000)
                startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                finish()
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color7),
                contentAlignment = Alignment.Center){
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.logo)
                )
            }

        }
    }
}