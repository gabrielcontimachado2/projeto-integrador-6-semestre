package com.example.vetfootprint.controller;

import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.vetfootprint.activitys.CadastroAnimal;
import com.example.vetfootprint.activitys.PerfilAnimal;
import com.example.vetfootprint.model.AnimalModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class AnimalController {
    //Referencia e instancia do database
    public static Boolean operacao;
    private StorageReference storageReference;

    public AnimalController() {
        //Construtor do animal
        //AnimalModel animal = new AnimalModel();

    }


    /*Metodos que precisam realizar operações
    de recuperação de dados
    */
    public void recuperaAnimal(String idAnimal, PerfilAnimal perfilAnimal) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("animal").child(idAnimal);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                AnimalModel animalModel = snapshot.getValue(AnimalModel.class);

                perfilAnimal.animalName.setText(animalModel.getAnimalName());
                perfilAnimal.animalBreed.setText(animalModel.getAnimelBreed());
                perfilAnimal.animalAge.setText(animalModel.getAnimalAge());
                perfilAnimal.animalSize.setText(animalModel.getAnimalSize());
                perfilAnimal.animalMedicine.setText(animalModel.getAnimalMedicine());
                perfilAnimal.animalMedicineTime.setText(animalModel.getAnimalTimeMedicine());
                perfilAnimal.animalObs.setText(animalModel.getAnimalObs());
                String sUrlImage = animalModel.getUrlImageDog();

                Glide.with(perfilAnimal.animalImageView.getContext()).load(sUrlImage).into(perfilAnimal.animalImageView);


                //String sAnimalName = snapshot.child("animalName").getValue().toString();
                //String sAnimalBreed = snapshot.child("animalBreed").getValue().toString();
                //String sAnimalAge = snapshot.child("animalAge").getValue().toString();
                //String sAnimalSize = snapshot.child("animalSize").getValue().toString();
                //String sAnimalMedicine = snapshot.child("animalMedicine").getValue().toString();
                //String sAnimalTimeMedicine = snapshot.child("animalTimeMedicine").getValue().toString();
                //String sAnimalObs = snapshot.child("animalObs").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void cadastrarAnimal(String sNomeDoAnimal, String sRacaDoAnimal, String sIdadeDoAnimal, String sPorteDoAnimal, String sMedicamentoAnimal, String sHorarioMedicamento,
                                String sObservacoesDoAnimal, CadastroAnimal cadastroAnimal, Uri imageUri) {

        //Gerar um UUID random para salvar um novo nó de animal no banco
        String idAnimal = UUID.randomUUID().toString();

        //Referenciar em qual nó vai ser salvo, baseado no id do animal
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("fotos");
        StorageReference referenceImage = storageReference.child("animal/" + idAnimal);

        //Salvar imagem no Storage do firebase
        referenceImage.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                referenceImage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //String para url do animal
                        String urlImageDog = uri.toString();

                        //Instancia um modelo de animal para salvar no realtime
                        AnimalModel animalModel = new AnimalModel(idAnimal, sNomeDoAnimal, sRacaDoAnimal, sIdadeDoAnimal, sPorteDoAnimal, sMedicamentoAnimal,
                                sHorarioMedicamento, sObservacoesDoAnimal, urlImageDog);

                        //Salvar no realtime o animal
                        FirebaseDatabase.getInstance().getReference("animal")
                                .child(idAnimal)
                                .setValue(animalModel)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(cadastroAnimal, "Animal foi cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(cadastroAnimal, "Não foi possivel realizar o cadastro tente novamente!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
            }
        });

    }

    /*Metodos que precisam realizar operações
    de editar o animal
    */
    public void editarAnimal(String idAnimal, String sNomeDoAnimal, String sRacaDoAnimal, String sIdadeDoAnimal, String sPorteDoAnimal, String sMedicamentoAnimal, String sHorarioMedicamento,
                             String sObservacoesDoAnimal, PerfilAnimal perfilAnimal, Uri imageUri) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("animal").child(idAnimal);

        //Referenciar em qual nó vai ser salvo, baseado no id do animal
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("fotos");
        StorageReference referenceImage = storageReference.child("animal/" + idAnimal);

        //Salvar imagem no Storage do firebase
        referenceImage.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                referenceImage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //String para url do animal
                        String urlImageDog = uri.toString();

                        //Instancia um hash map
                        HashMap animalMap = new HashMap();
                        animalMap.put("animalName", sNomeDoAnimal);
                        animalMap.put("animalBreed", sRacaDoAnimal);
                        animalMap.put("animalAge", sIdadeDoAnimal);
                        animalMap.put("animalSize", sPorteDoAnimal);
                        animalMap.put("animalMedicine", sMedicamentoAnimal);
                        animalMap.put("animalTimeMedicine", sHorarioMedicamento);
                        animalMap.put("animalObs", sObservacoesDoAnimal);
                        animalMap.put("urlImageDog", urlImageDog);



                        ref.updateChildren(animalMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(perfilAnimal, "Animal foi atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(perfilAnimal, "Não foi possivel foi atualizado o animal, tente novamente"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });


    }


}



