package com.example.vetfootprint.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.vetfootprint.activitys.CadastroIntegrante;
import com.example.vetfootprint.activitys.PerfilIntegrante;
import com.example.vetfootprint.fragments.Integrante;
import com.example.vetfootprint.model.AnimalModel;
import com.example.vetfootprint.model.IntegranteModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class IntegranteController {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    public IntegranteController() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void recuperarIntegrante(String idIntegrante, PerfilIntegrante perfilIntegrante){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("usuario").child("integrante").child(idIntegrante);

        ValueEventListener integranteListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                IntegranteModel integranteModel = dataSnapshot.getValue(IntegranteModel.class);

                perfilIntegrante.nameIntegrante.setText(integranteModel.getNameIntegrante());
                perfilIntegrante.functionIntegrante.setText(integranteModel.getFunctionIntegrante());
                perfilIntegrante.rgIntegrante.setText(integranteModel.getRgIntegrante());
                perfilIntegrante.emailIntegrante.setText(integranteModel.getEmailIntegrante());
                perfilIntegrante.cpfIntegrante.setText(integranteModel.getCpfIntegrante());
                String sUrlImage = integranteModel.getUrlImageIntegrante();

                //Pegar um context antes, dentro do glide."with" tava bugando(Não esquecer)
                Context context = perfilIntegrante.getApplicationContext();

                Glide.with(context).load(sUrlImage).into(perfilIntegrante.imageIntegrante);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("cancelado", "Erro ao atualizar", databaseError.toException());
            }
        };

        ref.addListenerForSingleValueEvent(integranteListener);

    }

    public void cadastrarIntegrante(String nameIntegrante, String passwordIntegrante, String emailIntegrante, String functionIntegrante, String cpfIntegrante, String rgIntegrante,
                                    String phoneIntegrate, Uri imageUri, CadastroIntegrante cadastroIntegrante) {


        SharedPreferences sharedPreferences = cadastroIntegrante.getSharedPreferences("idInstitutionCurrentUser", Context.MODE_PRIVATE);
        String idInstitution = sharedPreferences.getString("institutionId", "");

        mAuth.createUserWithEmailAndPassword(emailIntegrante, passwordIntegrante).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    cadastroIntegrante.progressBarIntegrate.setVisibility(View.VISIBLE);

                    currentUser = mAuth.getCurrentUser();

                    UserProfileChangeRequest userRoleSetUp = new UserProfileChangeRequest.Builder()
                            .setDisplayName("normal")
                            .build();

                    //Adicionar no auth qual o tipo de usuário
                    currentUser.updateProfile(userRoleSetUp).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Log.d("Teste", "role adicionada");
                            }
                        }
                    });

                    String idIntegrante = UUID.randomUUID().toString();

                    StorageReference refereImage = FirebaseStorage.getInstance().getReference("foto").child("usuario/" + idIntegrante);

                    refereImage.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            refereImage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String urlImageIntegrate = uri.toString();
                                    String userRole = "normal";

                                    IntegranteModel integranteModel = new IntegranteModel(nameIntegrante, emailIntegrante, functionIntegrante, cpfIntegrante,
                                                                                          rgIntegrante, phoneIntegrate, urlImageIntegrate, userRole, idInstitution, idIntegrante);

                                    DatabaseReference mbase = FirebaseDatabase.getInstance().getReference("usuario").child("integrante").child(idIntegrante);

                                    mbase.setValue(integranteModel)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    cadastroIntegrante.progressBarIntegrate.setVisibility(View.INVISIBLE);
                                                    Toast.makeText(cadastroIntegrante, "O integrante foi cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    cadastroIntegrante.progressBarIntegrate.setVisibility(View.INVISIBLE);
                                                    Toast.makeText(cadastroIntegrante, "Não foi possivel cadastrar o integrante!" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            });
                        }
                    });

                }
            }
        });

    }



}
