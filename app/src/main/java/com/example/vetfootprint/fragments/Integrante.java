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

import com.example.vetfootprint.R;
import com.example.vetfootprint.activitys.CadastroAnimal;
import com.example.vetfootprint.activitys.CadastroIntegrante;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Integrante extends Fragment {

    private IntegranteViewModel mViewModel;
    private FloatingActionButton floatingBtnAdicionarIntegrante;

    public static Integrante newInstance() {
        return new Integrante();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.integrante_fragment, container, false);


        floatingBtnAdicionarIntegrante = view.findViewById(R.id.floating_btn_add_integrantes_fragment);

        floatingBtnAdicionarIntegrante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CadastroIntegrante.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(IntegranteViewModel.class);
        // TODO: Use the ViewModel
    }

}