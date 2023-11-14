package com.example.ep2.datos

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Datos (
    cnx: Context,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
): SQLiteOpenHelper (cnx, "ep3.db", null,1) {

    override fun onCreate(db: SQLiteDatabase) {

     db.execSQL("CREATE TABLE movimientos (idMovimiento INTEGER PRIMARY KEY AUTOINCREMENT, " +
             "fecha DATETIME DEFAULT CURRENT_TIMESTAMP," +
             "descripcion TEXT," +
             "monto FLOAT," +
             "tipo INTEGER)")
    }

    fun registrarMovimientos(datos: Datos, descripcion: String, monto: Float, tipo: Int): Long {
        val db = datos.writableDatabase
        val cnxValue = ContentValues().apply {
           put("descripcion",descripcion)
           put("monto",monto)
           put("tipo",tipo)
        }
        return db.insert("movimientos",null, cnxValue)
    }

    fun movimientosSelect(datos: Datos) : Cursor {
        val db = datos.readableDatabase
        val sql = "SELECT * FROM movimientos ORDER BY idMovimiento DESC "
        return db.rawQuery(sql,null)
    }

    fun movimientosTotal(datos: Datos) : Cursor {
        val db = datos.readableDatabase
        val sql = "SELECT SUM(monto * tipo) AS subtotal FROM movimientos"
        return db.rawQuery(sql,null)
    }

    fun movimientosTotalIngresos(datos: Datos) : Cursor {
        val db = datos.readableDatabase
        val sql = "SELECT SUM(monto) AS ingreso FROM movimientos WHERE tipo = 1"
        return db.rawQuery(sql,null)
    }

    fun movimientosTotalGastos(datos: Datos) : Cursor {
        val db = datos.readableDatabase
        val sql = "SELECT SUM(monto) AS gastos FROM movimientos WHERE tipo = -1"
        return db.rawQuery(sql,null)
    }






    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }


}