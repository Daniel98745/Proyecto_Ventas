package com.example.proy_ventas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.primeropasoskotlin.db.AdminSQLiteOpenHelper

class Home : AppCompatActivity() {
    lateinit var cardProducto: CardView
    lateinit var cardIVA: CardView
    lateinit var cardSalir: CardView
    lateinit var cardVenta: CardView

    lateinit var btnlimpiarbd : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarR()
        estadoBoton()
    }

    fun cargarR(){
        cardProducto = findViewById(R.id.card_Producto)
        cardIVA = findViewById(R.id.card_IVA)
        cardSalir = findViewById(R.id.card_Salir)
        cardVenta = findViewById(R.id.card_Ventas)

        btnlimpiarbd = findViewById(R.id.btnLimpiarBD)
    }

    fun estadoBoton(){
        cardIVA.setOnClickListener{
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
        cardProducto.setOnClickListener{
            val i = Intent(this,Productos::class.java)
            startActivity(i)
        }
        cardSalir.setOnClickListener{
            val i = Intent(this,Login::class.java)
            startActivity(i)
        }
        cardVenta.setOnClickListener{
            val i = Intent(this,Ventas::class.java)
            startActivity(i)
        }

        btnlimpiarbd.setOnClickListener{
            val adminDBHelper = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val db = adminDBHelper.writableDatabase

            db.close()  // Cerrar la base de datos antes de eliminarla

            val dbName = "administracion"
            val result = this.deleteDatabase(dbName)

            if (result) {
                Toast.makeText(this, "Base de datos eliminada correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error al eliminar la base de datos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}