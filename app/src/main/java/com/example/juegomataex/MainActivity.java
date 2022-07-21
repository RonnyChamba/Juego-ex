package com.example.juegomataex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    Button btLogin, btRegistro;
    ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLogin = findViewById(R.id.btnLogin);
        btRegistro = findViewById(R.id.btnRegistro);
        imagen = findViewById(R.id.imageGif);

        btLogin.setOnClickListener( (view) -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });

        btRegistro.setOnClickListener( (view) -> {
            Intent intent = new Intent(this, Registro.class);
            startActivity(intent);
        });

       Typeface typeface = Typeface.createFromAsset(MainActivity.this.getAssets(), "fuentes/zombie.TTF");
        btLogin.setTypeface(typeface);
        btRegistro.setTypeface(typeface);

        String url = "https://i.pinimg.com/originals/c0/85/a9/c085a987f6a56f064454ce0ef4524552.gif";
        Uri urlParse = Uri.parse(url);
        Glide.with(getApplicationContext()).load(urlParse).into(imagen);

    }

}