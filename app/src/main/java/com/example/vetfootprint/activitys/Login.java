package com.example.vetfootprint.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vetfootprint.MainActivity;
import com.example.vetfootprint.R;
import com.example.vetfootprint.controller.CadastroInstituicaoController;
import com.example.vetfootprint.controller.LoginController;
import com.example.vetfootprint.model.LoginModel;
import com.example.vetfootprint.model.UserModel;

public class Login extends AppCompatActivity {

    private EditText edtLogin, edtSenha;
    private Button btnEnter, btnRegister;
    Boolean operacao;



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
                autenticacao(testFields());
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

    public void autenticacao(Boolean testeFields){

        if (testeFields) {
            String sEmail    = edtLogin.getText().toString();
            String sPassword = edtSenha.getText().toString();

            LoginModel loginModel = new LoginModel(sEmail, sPassword);

            LoginController loginController = new LoginController();

            operacao = loginController.singInUser(loginModel);

            if(operacao){
                login();
            }
            else{
                Toast.makeText(this, "Mandamos um email de verificação para ativar sua conta, após verificar tente novamente", Toast.LENGTH_SHORT).show();
            }


        } else {
            // Deu merda, retorna uma exceção
            Toast.makeText(this, "Não foi possível prosseguir, verifique os campos de dados e tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }

    private void login() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean testFields() {
        String sEmail    = edtLogin.getText().toString();
        String sPassword = edtSenha.getText().toString();


        if (sEmail.isEmpty() || sPassword.isEmpty()) {
            return false;
        }

        return true;
    }
}