package com.example.jaiba.laboratorio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText mail;
    private  EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mail=(EditText) findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);

        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
        mail.setText(preferences.getString("mail",""));
        password.setText(preferences.getString("password",""));
    }

    public void Aceptar(View view){
        mail=(EditText)findViewById(R.id.editText2);
        password=(EditText)findViewById(R.id.editText);

        if (validarEmail(mail.getText().toString()) && (password.length()!=0)) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("result",mail.toString());
            setResult(Activity.RESULT_OK,returnIntent);

            SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor Obj_editor = preferences.edit();
            Obj_editor.putString("mail",mail.getText().toString());
            Obj_editor.putString("password",password.getText().toString());
            Obj_editor.commit();

            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();

            finish();
        }
        if (validarEmail(mail.getText().toString())!= true){
            Toast.makeText(this, "Email Invalido", Toast.LENGTH_SHORT).show();
        }
        if (password.length()==0) {
            Toast.makeText(this, "Ingrese Contraseña", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
