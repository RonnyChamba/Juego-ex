package com.example.juegomataex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {

    EditText txtEmail,txtPassword, txtNombre;
    TextView txtFecha, txtTitulo;;
    Button btnRegistrar;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtTitulo = findViewById(R.id.txtTituloRegistro);

        txtEmail = findViewById(R.id.txtCorreo);
        txtNombre = findViewById(R.id.txtNombre);
        txtPassword = findViewById(R.id.txtPassword);
        txtFecha = findViewById(R.id.txtFecha);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        auth = FirebaseAuth.getInstance();

        Typeface typeface = Typeface.createFromAsset(Registro.this.getAssets(), "fuentes/zombie.TTF");
        txtTitulo.setTypeface(typeface);
        txtEmail.setTypeface(typeface);
        txtPassword.setTypeface(typeface);
        txtNombre.setTypeface(typeface);
        txtFecha.setTypeface(typeface);
        btnRegistrar.setTypeface(typeface);

        Date date = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("d 'de' MMMM 'del'  yyyy");
        String stringFecha = fecha.format(date);
        txtFecha.setText(stringFecha);

        btnRegistrar.setOnClickListener( (event)->{

            String email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                txtEmail.setError("Email invalido");
                txtEmail.setFocusable(true);

            } else if (password.trim().length() < 6) {
                txtPassword.setError("Constraseña debe ser mayor a 6");
                txtPassword.setFocusable(true);
            }else {
                registrarJugador(email, password);
            }
        });
    }
    private void registrarJugador(String email, String password){

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    FirebaseUser user = auth.getCurrentUser();
                    int contador = 0;
                    assert  user !=null;

                    String uidString = user.getUid();
                    String nombreString = txtNombre.getText().toString();
                    String fechaString = txtFecha.getText().toString();
                    String emailString = txtEmail.getText().toString();
                    String passwordString = txtPassword.getText().toString();

                    Map<Object, Object> datosJugador = new HashMap<>();

                    datosJugador.put("Uid", uidString);
                    datosJugador.put("Email", emailString);
                    datosJugador.put("Password", passwordString);
                    datosJugador.put("Nombres", nombreString);
                    datosJugador.put("Fecha", fechaString);
                    datosJugador.put("Zombies", contador);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                    DatabaseReference reference = database.getReference("MI DATA BASE JUGADORES");
                    reference.child(uidString).setValue(datosJugador);
                    startActivity(new Intent(Registro.this, Menu.class));

                    Toast.makeText(Registro.this, "Jugador creado", Toast.LENGTH_LONG).show();
                    finish();

                }else
                    Toast.makeText(Registro.this, "Ha ocurrido un error " + task.getException().getMessage(), Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Registro.this, e.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }
}