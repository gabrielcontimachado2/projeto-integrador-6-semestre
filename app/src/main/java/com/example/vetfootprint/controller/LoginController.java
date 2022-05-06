package com.example.vetfootprint.controller;

import android.text.BoringLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.vetfootprint.activitys.Login;
import com.example.vetfootprint.model.LoginModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginController {

    private FirebaseAuth mAuth;
    private static boolean operacao;


    public LoginController() {
        mAuth = FirebaseAuth.getInstance();
    }

    public Boolean singInUser(LoginModel loginModel, Login login) {

        mAuth.signInWithEmailAndPassword(loginModel.getsEmail(), loginModel.getsPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            if(user.isEmailVerified()){
                               operacao = true;

                                Toast.makeText(login, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                            }
                            else if(!user.isEmailVerified()){
                                user.sendEmailVerification();
                                Toast.makeText(login, "Verifique seu e-mail para ter acesso a conta", Toast.LENGTH_SHORT).show();
                                operacao = false;
                            }
                        }
                        else {
                            Toast.makeText(login, "Erro na autenticação tente novamente", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return operacao;
    }
}
