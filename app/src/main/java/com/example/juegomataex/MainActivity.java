package com.example.juegomataex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btLogin;
    Button btRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLogin = findViewById(R.id.btnLogin);
        btRegistro = findViewById(R.id.btnRegistro);

        btLogin.setOnClickListener( (view) -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });


        btRegistro.setOnClickListener( (view) -> {
            Intent intent = new Intent(this, Registro.class);
            startActivity(intent);
        });



      //  Typeface typeface = Typeface.createFromAsset(MainActivity.this.getAssets(), "fuentes/zombie.TTF");
        //btLogin.setTypeface(typeface);
        //btRegistro.setTypeface(typeface);

    }

}