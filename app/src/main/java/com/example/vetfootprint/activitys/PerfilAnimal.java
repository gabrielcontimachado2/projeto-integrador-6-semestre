package com.example.vetfootprint.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.vetfootprint.MainActivity;
import com.example.vetfootprint.R;
import com.example.vetfootprint.controller.AnimalController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PerfilAnimal extends AppCompatActivity {

    public ImageView backButton;
    public String name = "";
    public FloatingActionButton floatingEditMode, floatingBackNormalMode, floatingSaveEdit;
    public EditText animalName,animalBreed,animalAge, animalSize,animalMedicine, animalMedicineTime, animalObs;
    public ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_animal);

        onInit();

        lerDados();
    }

    private void lerDados() {

        AnimalController animalController = new AnimalController();
        animalController.recuperaAnimal(getIntent().getStringExtra("idAnimal"), this);

    }

    private void onInit() {
        backButton = findViewById(R.id.card_back_button);
        floatingEditMode = findViewById(R.id.floating_edt_perfil_animal_card);
        floatingBackNormalMode = findViewById(R.id.floating_back_edit_animal_card);
        floatingSaveEdit = findViewById(R.id.floating_done_edit_card);
        animalName = findViewById(R.id.edittext_name_animal_card_perfil);
        imageView = findViewById(R.id.image_view_card_photo_animal);
        animalBreed = findViewById(R.id.edttext_raca_animal_card_perfil);
        animalAge = findViewById(R.id.edttext_idade_animal_card_perfil);
        animalSize = findViewById(R.id.edttext_porte_animal_card_perfil);
        animalMedicine = findViewById(R.id.edt_medicamento_animal_card_perfil);
        animalMedicineTime = findViewById(R.id.edt_horario_medicamento_animal_card);
        animalObs = findViewById(R.id.edt_observacoes_animal_card);


    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_back_button:
                backHome();
                break;
            case R.id.floating_edt_perfil_animal_card:
                editMode();
                break;
            case R.id.floating_back_edit_animal_card:
                backNormalMode();
                break;
            case R.id.floating_done_edit_card:
                saveEdit();
                break;
        }
    }

    private void saveEdit() {
    }

    private void backNormalMode() {
        floatingEditMode.setVisibility(View.VISIBLE);
        floatingSaveEdit.setClickable(false);
        floatingSaveEdit.setVisibility(View.INVISIBLE);
        floatingBackNormalMode.setClickable(true);
        floatingBackNormalMode.setVisibility(View.INVISIBLE);
    }

    private void editMode() {


        animalAge.setEnabled(true);
        animalSize.setEnabled(true);
        animalMedicine.setEnabled(true);
        animalMedicineTime.setEnabled(true);
        animalObs.setEnabled(true);

        floatingEditMode.setVisibility(View.INVISIBLE);
        floatingSaveEdit.setClickable(true);
        floatingSaveEdit.setVisibility(View.VISIBLE);
        floatingBackNormalMode.setClickable(true);
        floatingBackNormalMode.setVisibility(View.VISIBLE);
    }


    private void backHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}