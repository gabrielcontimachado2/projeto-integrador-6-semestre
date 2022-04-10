package com.example.vetfootprint.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vetfootprint.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CadastrarInstituicao extends AppCompatActivity {

    private Button btnFecharCadastroInstituicao;
    private FloatingActionButton btnConfirmarCadastroInstituicao;
    private EditText edtNomeInstituicao,
                     edtEmailInstituicao,
                     edtSenhaInstituicao,
                     edtConfirmaSenhaInstituicao,
                     edtCnpjInstituicao,
                     edtTelefoneInstituicao,
                     edtEnderecoInstituicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_instituicao);

        onInitView();
    }

    private void onInitView() {
        btnFecharCadastroInstituicao = findViewById(R.id.btn_fechar_cadastro_instituicao);
        btnConfirmarCadastroInstituicao = findViewById(R.id.floating_btn_cadastrar_instituicao_tela);
        edtNomeInstituicao = findViewById(R.id.edttext_nome_instituicao);
        edtEmailInstituicao = findViewById(R.id.edttext_email_instituicao);
        edtSenhaInstituicao = findViewById(R.id.edttext_senha_instituicao);
        edtConfirmaSenhaInstituicao = findViewById(R.id.edttext_confirma_senha_instituicao);
        edtCnpjInstituicao = findViewById(R.id.edttext_cnpj_instituicao);
        edtTelefoneInstituicao = findViewById(R.id.edttext_telefone_instituicao);
        edtEnderecoInstituicao = findViewById(R.id.edttext_endereco_instituicao);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fechar_cadastro_instituicao:
                finish();
                break;
            case R.id.floating_btn_cadastrar_instituicao_tela:
                saveData(testFields());
        }
    }

    private void saveData(boolean isSuccess) {
        // Tudo dentro de um try
        if (isSuccess) {
            Toast.makeText(this, "Salvado com sucesso! Parabéns!", Toast.LENGTH_SHORT).show();
            hideKeyboardFrom(this, edtEnderecoInstituicao);
            limparCampos();
        } else {
            // Deu merda, retorna uma exceção
            Toast.makeText(this, "Não foi possível prosseguir, verifique os campos de dados e tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }

    private void limparCampos() {
        edtNomeInstituicao.setText("");
        edtEmailInstituicao.setText("");
        edtSenhaInstituicao.setText("");
        edtConfirmaSenhaInstituicao.setText("");
        edtCnpjInstituicao.setText("");
        edtTelefoneInstituicao.setText("");
        edtEnderecoInstituicao.setText("");
    }

    public boolean testFields() {
        String sNomeInstituicao = edtNomeInstituicao.getText().toString();
        String sEmailInstituicao = edtEmailInstituicao.getText().toString();
        String sSenhaInstituicao = edtSenhaInstituicao.getText().toString();
        String sConfirmarSenhaInstituicao = edtConfirmaSenhaInstituicao.getText().toString();
        String sCnpjInstituicao = edtCnpjInstituicao.getText().toString();
        String sTelefoneInstituicao = edtTelefoneInstituicao.getText().toString();
        String sEnderecoInstituicao = edtEnderecoInstituicao.getText().toString();

        if (sNomeInstituicao.isEmpty() || sEmailInstituicao.isEmpty() || sSenhaInstituicao.isEmpty() || sConfirmarSenhaInstituicao.isEmpty() || sCnpjInstituicao.isEmpty()|| sTelefoneInstituicao.isEmpty() || sEnderecoInstituicao.isEmpty()) {
            return false;
        }

        return true;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(CadastroAnimal.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}