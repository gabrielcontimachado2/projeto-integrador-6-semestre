package com.example.vetfootprint.controller;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.vetfootprint.activitys.CadastroAnimal;
import com.example.vetfootprint.model.AnimalModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class AnimalController {
    //Referencia e instancia do database
    public static Boolean operacao;
    public AnimalController() {
        //Construtor do animal
        //AnimalModel animal = new AnimalModel();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    /*Metodos que precisam realizar operações
    de persistencia e recuperação de dados
    */
    public void recuperaAnimal() {

    }

    public void cadastrarAnimal(AnimalModel animal, CadastroAnimal cadastroAnimal) {
        //TODO -> Rotina de persistencia dos dados no firebase
        Map<String,Object> objectMap = new HashMap<>(); //mapeamento de objetos


        FirebaseDatabase.getInstance().getReference("animal")
                .child(UUID.randomUUID().toString())
                .setValue(animal).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(cadastroAnimal, "Animal foi cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(cadastroAnimal, "Não foi possivel realizar o cadastro tente novamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}



