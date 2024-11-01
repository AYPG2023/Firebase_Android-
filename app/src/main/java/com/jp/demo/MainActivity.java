package com.jp.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class MainActivity extends AppCompatActivity {

    private Button btnPersona;
    private Button btnProducto;
    private Button btnOrdenes;
    private Button btnInformes;
    private Button btnConfigurciones;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();
        eventos();

        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void iniciarSesion(String email, String password){
        try {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                    MainActivity.this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();

                            // Si el inicio de sesión es exitoso, abrir ProductosActivity
                            Intent intent = new Intent(MainActivity.this, ProductosActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(MainActivity.this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
        }
    }

    private void bindUI() {
        btnPersona = findViewById(R.id.btnPersona);
        btnProducto = findViewById(R.id.btnProducto);
        btnOrdenes = findViewById(R.id.btnOrdenes);
        btnInformes = findViewById(R.id.btnInformes);
        btnConfigurciones = findViewById(R.id.btnConfiguraciones);
    }

    private void eventos() {
        btnPersona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PersonaActivity.class);
                startActivity(intent);
            }
        });

        btnProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Primero intenta iniciar sesión antes de abrir ProductosActivity
                iniciarSesion("andersonperdomo525@gmail.com", "123456");
            }
        });
    }
}