package com.grupoess.grupoessv05

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.grupoess.grupoessv05.R
import kotlinx.android.synthetic.main.registrarse.*

class registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrarse)

        //se llama la funcion para la conxion a FireBase
        val mAuth: FirebaseAuth
        mAuth = FirebaseAuth.getInstance()

        //se llama la clase que contiene las alertas
        var v_alertas = alertas();

        //se escucha el evento click del boton registrar
        id_registrate_boton.setOnClickListener {

            if(id_registrate_clave.length() < 7){
                //valicacion
                v_alertas.mensaje("Alerta","La clave debe tener mínimo 7 caracteres","Aceptar",this)
            }
            else if(id_registrate_clave.text.toString() != id_registrate_clave2.text.toString()){
                //valicacion
                v_alertas.mensaje("Alerta","Las claves no coinciden","Aceptar",this)
            }
            else if(id_registrate_clave.text.toString() == "" || id_registrate_correo.text.toString() == "" ){
                //valicacion
                v_alertas.mensaje("Alerta","Debe llenar todos los campos","Aceptar",this)
            }
            else{
                //Se guarda el usuario
                mAuth.createUserWithEmailAndPassword(id_registrate_correo.text.toString(), id_registrate_clave.text.toString())
                    .addOnCompleteListener(
                        (this as Activity?)!!
                    ) { task ->
                        if (task.isSuccessful) {
                            val user = mAuth.currentUser
                            v_alertas.mensaje("Alerta","Usuario creado correctamente","Aceptar",this)

                            val i = Intent(this, login::class.java)
                            startActivity(i)
                        } else {
                            val e = task.exception
                            v_alertas.mensaje("alerta","Fallo al crear el usuario: " + e.toString(),"Aceptar" ,this)
                        }
                    }
            }
        }

        //se escucha el evento click del boton cancelar
        id_registrate_ingresar.setOnClickListener {
            val i = Intent(this, login::class.java)
            startActivity(i)
        }
    }
}