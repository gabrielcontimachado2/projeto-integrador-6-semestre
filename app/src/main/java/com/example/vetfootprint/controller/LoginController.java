package com.example.vetfootprint.controller;

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

    public boolean singInUser(LoginModel loginModel) {

        mAuth.signInWithEmailAndPassword(loginModel.getsEmail(), loginModel.getsPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            if(user.isEmailVerified()){
                               operacao = true;
                            }
                            else{
                                user.sendEmailVerification();
                                operacao = false;
                            }
                        }
                    }
                });

        return operacao;
    }
}
