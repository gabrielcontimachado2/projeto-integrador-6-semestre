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

public class CadastroIntegrante extends AppCompatActivity {

    private Button btnFecharCadastroIntegrante;
    private FloatingActionButton btnConfirmarCadastroIntegrante;
    private EditText edtNomeIntegrante,
            edtEmailIntegrante,
            edtSenhaIntegrante,
            edtConfirmaSenhaIntegrante,
            edtFuncaoIntegrante,
            edtCpfIntegrante,
            edtRgIntegrante,
            edtTelefoneIntegrante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_integrante);


        onInitView();
    }

    private void onInitView() {
        btnFecharCadastroIntegrante = findViewById(R.id.btn_fechar_cadastro_integrante);
        btnConfirmarCadastroIntegrante = findViewById(R.id.floating_btn_cadastrar_integrante_tela);
        edtNomeIntegrante = findViewById(R.id.edttext_nome_integrante);
        edtEmailIntegrante = findViewById(R.id.edttext_email_integrante);
        edtSenhaIntegrante = findViewById(R.id.edttext_senha_integrante);
        edtConfirmaSenhaIntegrante = findViewById(R.id.edttext_confirma_senha_integrante);
        edtFuncaoIntegrante = findViewById(R.id.edttext_funcao_integrante);
        edtCpfIntegrante = findViewById(R.id.edttext_cpf_integrante);
        edtRgIntegrante = findViewById(R.id.edttext_rg_integrante);
        edtTelefoneIntegrante = findViewById(R.id.edttext_telefone_integrante);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fechar_cadastro_integrante:
                finish();
                break;
            case R.id.floating_btn_cadastrar_integrante_tela:
                saveData(testFields());
        }
    }

    private void saveData(boolean isSuccess) {
        // Tudo dentro de um try
        if (isSuccess) {
            Toast.makeText(this, "Salvado com sucesso! Parabéns!", Toast.LENGTH_SHORT).show();
            hideKeyboardFrom(this, edtTelefoneIntegrante);
            limparCampos();
        } else {
            // Deu merda, retorna uma exceção
            Toast.makeText(this, "Não foi possível prosseguir, verifique os campos de dados e tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }

    private void limparCampos() {
        edtNomeIntegrante.setText("");
        edtEmailIntegrante.setText("");
        edtSenhaIntegrante.setText("");
        edtConfirmaSenhaIntegrante.setText("");
        edtFuncaoIntegrante.setText("");
        edtCpfIntegrante.setText("");
        edtRgIntegrante.setText("");
        edtTelefoneIntegrante.setText("");
    }

    public boolean testFields() {
        String sNomeIntegrante = edtNomeIntegrante.getText().toString();
        String sEmailIntegrante = edtEmailIntegrante.getText().toString();
        String sSenhaIntegrante = edtSenhaIntegrante.getText().toString();
        String sConfirmaSenhaIntegrante = edtConfirmaSenhaIntegrante.getText().toString();
        String sFuncaoIntegrante = edtFuncaoIntegrante.getText().toString();
        String sCpfIntegrante = edtCpfIntegrante.getText().toString();
        String sRgIntegrante = edtRgIntegrante.getText().toString();
        String sTelefoneIntegrante = edtTelefoneIntegrante.getText().toString();

        if (sNomeIntegrante.isEmpty() || sEmailIntegrante.isEmpty() || sSenhaIntegrante.isEmpty() || sConfirmaSenhaIntegrante.isEmpty() || sFuncaoIntegrante.isEmpty()|| sCpfIntegrante.isEmpty() || sRgIntegrante.isEmpty() || sTelefoneIntegrante.isEmpty()) {
            return false;
        }

        return true;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(CadastroAnimal.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}