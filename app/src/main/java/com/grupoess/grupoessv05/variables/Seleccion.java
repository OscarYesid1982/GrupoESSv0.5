package com.grupoess.grupoessv05.variables;

public class Seleccion {
    private static int id_categoria;
    private static int id_producto;

    public void set_id_categoria(int v_id){
        id_categoria = v_id;
    }

    public int get_id_categoria(){
        return id_categoria;
    }


    public void set_id_producto(int v_id){
        id_producto = v_id;
    }

    public int get_id_producto(){
        return id_producto;
    }
}
