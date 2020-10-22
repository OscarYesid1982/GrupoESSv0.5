package com.grupoess.grupoessv05.adapters

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.grupoess.grupoessv05.R
import com.grupoess.grupoessv05.model.Productos_object
import com.squareup.picasso.Picasso

class LanguageAdaptersProductos(var context: Context, var arrayList: ArrayList<Productos_object>):BaseAdapter() {

    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {


        var view: View = View.inflate(context, R.layout.item_productos, null)
        var icons: ImageView = view.findViewById(R.id.Imageview_Producto)
        var names: TextView = view.findViewById(R.id.DescripcionProducto)
        var precio: TextView = view.findViewById(R.id.PrecioVenta)

        var listItem: Productos_object = arrayList.get(position)

        Log.i("prueba",listItem.toString())

        Picasso.get().load(listItem.icons).into(icons);
        names.text = listItem.name
        //precio.text="$200"

        return view
    }
}