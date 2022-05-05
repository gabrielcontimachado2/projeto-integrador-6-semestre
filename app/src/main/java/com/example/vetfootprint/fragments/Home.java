package com.example.vetfootprint.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import com.example.vetfootprint.R;
import com.example.vetfootprint.activitys.CadastroAnimal;
import com.example.vetfootprint.activitys.Login;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class Home extends Fragment  {

    private HomeViewModel mViewModel;
    private FloatingActionButton floatingBtnAdicionarAnimal;
    private Button btnLogout;
    TextView txtView;


    public static Home newInstance() {
        return new Home();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        floatingBtnAdicionarAnimal = view.findViewById(R.id.floating_btn_add_dog_home_fragment);

        btnLogout = view.findViewById(R.id.logout);
        txtView = view.findViewById(R.id.txt_email_teste_home);



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loggoutUser();
                Intent intent = new Intent(getActivity(), Login.class);
                startActivity(intent);

            }
        });


        floatingBtnAdicionarAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CadastroAnimal.class);
                startActivity(intent);
                String sEmail = Objects.requireNonNull(auth.getCurrentUser()).getEmail();

                txtView.setText(sEmail);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
    }

    public void loggoutUser(){
        FirebaseAuth.getInstance().signOut();
       //FirebaseAuth mAuth;
       //mAuth = FirebaseAuth.getInstance();
       //FirebaseUser currentUser = mAuth.getCurrentUser();
       //mAuth.signOut();
    }
}