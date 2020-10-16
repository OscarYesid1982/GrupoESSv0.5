package com.grupoess.grupoessv05

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.grupoess.grupoessv05.variables.Seleccion
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.seleccion_producto.*

class seleccion_producto : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.seleccion_producto)
        var cat = Seleccion();
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("productos/"+cat.get_id_producto())
        var context = this;

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (array in dataSnapshot.children) {
                    //se recorre el nombre y la categoria
                    if(array.key == "Nombre"){seleccion_producto_id_titulo.text =array.value.toString()}
                    if(array.key == "Descripcion"){seleccion_producto_id_descripcion.text = array.value.toString()}
                    if(array.key == "Imagen"){Picasso.get().load(array.value.toString()).into(seleccion_producto_id_imagen);}
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Alerta", "Failed to read value.", error.toException())
            }
        });

        seleccion_producto_id_compra.setOnClickListener {


        }
    }

}