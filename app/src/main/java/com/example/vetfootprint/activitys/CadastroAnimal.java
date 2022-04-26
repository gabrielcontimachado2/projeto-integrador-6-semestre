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
import com.example.vetfootprint.controller.AnimalController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CadastroAnimal extends AppCompatActivity implements View.OnClickListener {
    private AnimalController animalController = new AnimalController();//Instancia da controller no listenner
    private Button btnFecharCadastroAnimal;
    private FloatingActionButton btnConfirmarCadastroAnimal;
    private EditText edtNomeAnimal,
                     edtRacaAnimal,
                     edtIdadeAnimal,
                     edtPorteAnimal,
                     edtMedicamentoAnimal,
                     edtHorarioMedicamento,
                     edtObservacoesAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_animal);

        onInitView();
    }

    private void onInitView() {
        btnFecharCadastroAnimal = findViewById(R.id.btn_fechar_cadastro_animal);
        btnConfirmarCadastroAnimal = findViewById(R.id.floating_btn_cadastrar_animal_tela);
        edtNomeAnimal = findViewById(R.id.edttext_animal_name);
        edtRacaAnimal = findViewById(R.id.edttext_raca_animal);
        edtIdadeAnimal = findViewById(R.id.edttext_idade_animal);
        edtPorteAnimal = findViewById(R.id.edttext_porte_animal);
        edtMedicamentoAnimal = findViewById(R.id.edttext_medicamento_animal);
        edtHorarioMedicamento = findViewById(R.id.edttext_horario_medicamento);
        edtObservacoesAnimal = findViewById(R.id.edttext_observacoes_animal);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_fechar_cadastro_animal:
                finish();
                break;
            case R.id.floating_btn_cadastrar_animal_tela:
                saveData(testFields());
        }
    }


    private void saveData(boolean isSuccess) {

        // Tudo dentro de um try
        if (isSuccess){
            Toast.makeText(this, "Salvado com sucesso! Parabéns!", Toast.LENGTH_SHORT).show();
            hideKeyboardFrom(this, edtObservacoesAnimal);
            limparCampos();
        } else {
            // Deu merda, retorna uma exceção
            Toast.makeText(this, "Não foi possível prosseguir, verifique os campos de dados e tente novamente.", Toast.LENGTH_SHORT).show();
        }
    }


    private void limparCampos() {
        edtNomeAnimal.setText("");
        edtRacaAnimal.setText("");
        edtIdadeAnimal.setText("");
        edtPorteAnimal.setText("");
        edtMedicamentoAnimal.setText("");
        edtHorarioMedicamento.setText("");
        edtObservacoesAnimal.setText("");
    }

    public boolean testFields() {
        String sNomeDoAnimal = edtNomeAnimal.getText().toString();
        String sRacaDoAnimal = edtRacaAnimal.getText().toString();
        String sIdadeDoAnimal = edtIdadeAnimal.getText().toString();
        String sPorteDoAnimal = edtPorteAnimal.getText().toString();
        String sMedicamentoAnimal = edtMedicamentoAnimal.getText().toString();
        String sHorarioMedicamento = edtHorarioMedicamento.getText().toString();
        String sObservacoesDoAnimal = edtObservacoesAnimal.getText().toString();

        if (sNomeDoAnimal.isEmpty() || sRacaDoAnimal.isEmpty() || sIdadeDoAnimal.isEmpty() || sPorteDoAnimal.isEmpty() || sMedicamentoAnimal.isEmpty()|| sHorarioMedicamento.isEmpty() || sObservacoesDoAnimal.isEmpty()) {
            return false;
        }
            animalController.cadastrarAnimal(
                    sNomeDoAnimal,
                    sObservacoesDoAnimal,
                    sPorteDoAnimal,
                    sRacaDoAnimal,
                    sMedicamentoAnimal,
                    sHorarioMedicamento,
                    sIdadeDoAnimal
                    ); //Cadastrar o bixim
        return true;
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(CadastroAnimal.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}