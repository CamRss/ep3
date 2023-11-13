package com.example.ep2.utils

import org.json.JSONObject

class Util {
        companion object{
            const val rutaServicio = "https://servicios.campus.pe/"
//            const val rutaServicio = "https://localhost/servicios.campus.pe/" Noooooooo https://127.0.0.1/servicio/ TAMPOCO
//            const val rutaServicio = "https://10.0.2.2/servicio/"
//            const val rutaServicio = "https://1192.168.18.8/servicio/" El ip de su pc en la red local
            var usuarioActivo: JSONObject = JSONObject()
        }
}