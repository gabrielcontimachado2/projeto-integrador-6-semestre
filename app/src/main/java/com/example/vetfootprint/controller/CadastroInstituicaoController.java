package com.example.vetfootprint.controller;

import android.text.BoringLayout;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.vetfootprint.MainActivity;
import com.example.vetfootprint.R;
import com.example.vetfootprint.activitys.CadastrarInstituicao;
import com.example.vetfootprint.activitys.Login;
import com.example.vetfootprint.model.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroInstituicaoController {

    public static Boolean operacao;

    private FirebaseAuth mAuth;
    FirebaseUser currentUser;

    public CadastroInstituicaoController() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    public void cadastrarInstituicao(@NonNull UserModel userModel) {

        Log.d("myTag", "entrei na função " + " "+ userModel.getUserEmailInstituion());

        mAuth.createUserWithEmailAndPassword(userModel.getUserEmailInstituion(), userModel.getUserPasswordIstitution())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                           FirebaseDatabase.getInstance().getReference("usuarios")
                                   .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                   .setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {

                                   if (task.isSuccessful()){
                                       operacao = true;
                                       /*TODO transformar operação para string com mensagem do toast*/
                                   }
                                   else{
                                       operacao = false;
                                   }
                               }
                           });
                        }
                    }
                });
    }


}
