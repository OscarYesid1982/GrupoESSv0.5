package com.grupoess.grupoessv05

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getbase.floatingactionbutton.FloatingActionsMenu
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.grupoess.grupoessv05.adapters.LanguageAdaptersCategorias
import com.grupoess.grupoessv05.model.Categorias_object
import com.grupoess.grupoessv05.variables.Seleccion
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private var arrayList:ArrayList<Categorias_object> ? = null
    private var gridView:GridView ? = null
    private var languageAdapters: LanguageAdaptersCategorias? = null
    private var recyclerView: RecyclerView? = null

    var grupoBotones: FloatingActionsMenu? = null
    var fabActualizar: FloatingActionButton? = null
    var fabEliminar: FloatingActionButton? = null
    var fabConfirmar: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))

        idFabActualizar.setOnClickListener {
           // val i = Intent(this, registrarse::class.java)
           // startActivity(i)
            Toast.makeText(this, "Prueba fab", Toast.LENGTH_SHORT).show()
        }

        idFabConfirmar.setOnClickListener{
            Toast.makeText(this, "Mensaje de Confirmar", Toast.LENGTH_SHORT).show()
        }




        var context = this;
        var arrayList_2:ArrayList<Categorias_object> = ArrayList()
        //se declara la variable de firbases y se llama a categorias
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("categorias")

        //se toma el grid_view_contet_main

        //Set the Layout Manager
        //recyclerView = findViewById(R.id.recyclerContentMain)
        gridView = findViewById(R.id.grid_view_contet_main)

        //se llama el resultado de la consulta
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (array in dataSnapshot.children) {
                    var id = array.key.toString().toInt();
                    var Nombre = "";
                    var icon = "";
                    //se recorre el nombre y la categoria
                    for (categoria in array.children) {
                        if (categoria.key == "Nombre") {
                            Nombre = categoria.value.toString()
                        }
                        if (categoria.key == "Imagen") {
                            icon = categoria.value.toString()
                        }
                    }
                    //se guarda el resultado en el array a publicar
                    arrayList_2.add(Categorias_object(icon, Nombre, id))
                }

                //se llena el array list
                arrayList = arrayList_2;
                languageAdapters = LanguageAdaptersCategorias(applicationContext, arrayList_2!!)
                gridView?.adapter = languageAdapters
                gridView?.onItemClickListener = context


            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Alerta", "Failed to read value.", error.toException())
            }
        });


    }

/*
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

 */

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var items: Categorias_object = arrayList!!.get(position)
        var cat = Seleccion();
        cat.set_id_categoria(items.id!!)

        val intent = Intent(this, productos::class.java)
        startActivityForResult(intent, 0)
    }


}