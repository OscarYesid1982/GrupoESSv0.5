package com.grupoess.grupoessv05

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.grupoess.grupoessv05.R
import com.grupoess.grupoessv05.model.Productos_object
import com.grupoess.grupoessv05.variables.Seleccion
import com.grupoess.grupoessv05.adapters.LanguageAdaptersProductos
import com.grupoess.grupoessv05.seleccion_producto


class productos : AppCompatActivity(), AdapterView.OnItemClickListener {

    private var arrayList:ArrayList<Productos_object> ? = null
    private var gridView:GridView ? = null
    private var languageAdapters: LanguageAdaptersProductos? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(findViewById(R.id.toolbar))

        var context = this;
        var arrayList_2:ArrayList<Productos_object> = ArrayList()
        //se declara la variable de firbases y se llama a categorias
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("productos")
        //se trae la clase que guardo la seleccion de la categoria
        var cat = Seleccion();


        gridView = findViewById(R.id.grid_view_contet_main)
        arrayList = ArrayList()

        //se llama el resultado de la consulta
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (array in dataSnapshot.children) {
                    var icons = "";
                    var name = "";
                    var descripcion = "";
                    var id= array.key.toString().toInt();
                    var id_categoria = 0;

                    //se recorre el nombre y la categoria
                    for (categoria in array.children) {
                        if(categoria.key == "Descripcion"){descripcion =  categoria.value.toString()}
                        if(categoria.key == "Id_categoria"){id_categoria =  categoria.value.toString().toInt()}
                        if(categoria.key == "Nombre"){name =  categoria.value.toString()}
                        if(categoria.key == "Imagen"){icons =  categoria.value.toString()}
                    }
                    //se hace el filtro de la categoria seleccionada
                    if(cat.get_id_categoria() == id_categoria){
                        arrayList_2.add(Productos_object(id, id_categoria, icons, name, descripcion))
                    }
                }

                //se llena el array list
                arrayList = arrayList_2;
                languageAdapters = LanguageAdaptersProductos(applicationContext, arrayList_2!!)
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
        var items: Productos_object = arrayList!!.get(position)
        var cat = Seleccion();
        cat.set_id_producto(items.id!!)

        val intent = Intent(this, seleccion_producto::class.java)
        startActivityForResult(intent, 0)
    }
}