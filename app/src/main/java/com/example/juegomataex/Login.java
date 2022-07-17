package com.example.juegomataex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
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

public class Login extends AppCompatActivity {

    EditText txtEmailLogin,txtPassLogin;
    TextView txtTituloLogin;
    Button btnLogin;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtTituloLogin = findViewById(R.id.txtTituloLogin);
        txtEmailLogin = findViewById(R.id.emailLogin);
        txtPassLogin = findViewById(R.id.passLogin);
        btnLogin = findViewById(R.id.btnIngresarLogin);
        auth = FirebaseAuth.getInstance();

        Typeface typeface = Typeface.createFromAsset(Login.this.getAssets(), "fuentes/zombie.TTF");
        btnLogin.setTypeface(typeface);
        txtEmailLogin.setTypeface(typeface);
        txtPassLogin.setTypeface(typeface);
        txtTituloLogin.setTypeface(typeface);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmailLogin.getText().toString();
                String password = txtPassLogin.getText().toString();


                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    txtEmailLogin.setError("Email invalido");
                    txtEmailLogin.setFocusable(true);

                } else if (password.trim().length() < 6) {
                    txtPassLogin.setError("Contraseña debe ser mayor a 6");
                    txtPassLogin.setFocusable(true);
                } else {
                    LogeoDeJugador(email, password);
                }
            }
        });
    }
    private void LogeoDeJugador(String email, String password) {


        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();
                    startActivity(new Intent(Login.this, Menu.class));
                    assert  user !=null;
                    Toast.makeText(Login.this, "BIENVENIDO(A) "+ user.getEmail(), Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "Jugador no registrado", Toast.LENGTH_LONG).show();
            }
        });


    }
}