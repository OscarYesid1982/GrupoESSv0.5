package com.grupoess.grupoessv05;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class alertas {
    public void mensaje(String titulo, String mensaje, String boton, Context main) {
        AlertDialog.Builder builder = new AlertDialog.Builder(main);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setPositiveButton(boton, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
