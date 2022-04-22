package com.example.vetfootprint.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vetfootprint.MainActivity;
import com.example.vetfootprint.R;
import com.example.vetfootprint.controller.CadastroInstituicaoController;

public class Login extends AppCompatActivity {

    private EditText edtLogin, edtSenha;
    private Button btnEnter, btnRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        onInitView();


    }

    private void onInitView() {
        edtLogin = findViewById(R.id.edittext_user_email);
        edtSenha = findViewById(R.id.edittext_user_password);
        btnEnter =  findViewById(R.id.btn_enter);
        btnRegister = findViewById(R.id.btn_register_institution);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_enter:
                autenticacao();
                break;
            case R.id.btn_register_institution:
                registerInstitution();
                break;
        }
    }

    private void registerInstitution() {
        Intent intent = new Intent(Login.this, CadastrarInstituicao.class);
        startActivity(intent);
        finish();
    }

    public void autenticacao(){
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}