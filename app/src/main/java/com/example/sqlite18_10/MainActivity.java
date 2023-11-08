package com.example.sqlite18_10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ID, Usuario, AreaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ID = findViewById(R.id.txtID);
        Usuario = findViewById(R.id.txtNombreUsuario);
        AreaUsuario = findViewById(R.id.txtAreaUsuario);
    }
    public void RegistrarUsuario(View view){
        //siempre sera version 1 y hay que colocar estos parametros
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "produccion", null,1);

        //abre la base de dato de escritura y lectura
        SQLiteDatabase baseDatos = admin.getWritableDatabase();

        //Se crean variables para recibir los  datos
        String IDUsuario = ID.getText().toString();
        String NombreUsuario = Usuario.getText().toString();
        String Areausuario = AreaUsuario.getText().toString();

        //validacion
        if(!IDUsuario.isEmpty() && !NombreUsuario.isEmpty() && !Areausuario.isEmpty()){
            //paquete de datos para mostrarlo en la otra ventana
            ContentValues DatosUsuario = new ContentValues();
            //le agrega los datos
            DatosUsuario.put("ID_Usuario", IDUsuario);
            DatosUsuario.put("NombreUsuario",NombreUsuario);
            DatosUsuario.put("AreaUsuario",Areausuario);
            baseDatos.insert("Usuarios",null,DatosUsuario);
            //cierra base de datos
            baseDatos.close();
            //limpia
            ID.setText("");
            Usuario.setText("");
            AreaUsuario.setText("");
            Toast.makeText(this, "Se ha registrado correctamente", Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this, "No puede haber campos vacios", Toast.LENGTH_SHORT).show();
        }
    }
    public void BuscarUsuario(View view){
        //se copia los primeros 3 codigos de arriba
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "produccion", null,1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        String IDUsuario = ID.getText().toString();
        //creamos una funcion
        if(!IDUsuario.isEmpty()){
            Cursor fila = baseDatos.rawQuery("Select NombreUsuario,AreaUsuario  from Usuarios where ID_Usuario =" + IDUsuario,null);
            //si se mueve y encunetra datos es porque hay datos en la abse de datos
            if(fila.moveToFirst()){
                Usuario.setText(fila.getString(0));
                AreaUsuario.setText(fila.getString(1));
                baseDatos.close();
            }else{
                Toast.makeText(this, "El ID ingresado no existe", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "El campo ID no puede estar vacio ", Toast.LENGTH_SHORT).show();
        }
    }
    public void EliminarUsuario(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "produccion", null,1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        String IDUsuario = ID.getText().toString();

        if (!IDUsuario.isEmpty()){
            int Eliminar = baseDatos.delete("Usuarios","ID_Usuario=" + IDUsuario,null);
            baseDatos.close();
            ID.setText("");
            AreaUsuario.setText("");
            Usuario.setText("");
            if (Eliminar == 1){
                Toast.makeText(this, "Se elimino  el Usuario Correctamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "No se  encontro el ID del usuario ", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "No pueden haber campos vacios ", Toast.LENGTH_SHORT).show();
        }
    }
    public void ActualizarUsuario(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "produccion", null,1);
        SQLiteDatabase baseDatos = admin.getWritableDatabase();
        String IDUsuario = ID.getText().toString();
        String NombreUsuario = Usuario.getText().toString();
        String Areausuario = AreaUsuario.getText().toString();


        if (!IDUsuario.isEmpty() && !NombreUsuario.isEmpty() && Areausuario.isEmpty()){
            ContentValues DatosUsuarios = new ContentValues();
            DatosUsuarios.put("NombreUsuario",NombreUsuario);
            DatosUsuarios.put("AreaUsuario",Areausuario);
        int Modificar = baseDatos.update("Usuario",DatosUsuarios,"ID_Usuario=" + IDUsuario,null);
        if (Modificar ==1){
            Toast.makeText(this, "Se a modificado correctamente", Toast.LENGTH_SHORT).show();
            baseDatos.close();
            ID.setText("");
            AreaUsuario.setText("");
            Usuario.setText("");
        }else{
            Toast.makeText(this, "No existe el id a modificar", Toast.LENGTH_SHORT).show();
        }

        }else{
            Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
        }
    }
}