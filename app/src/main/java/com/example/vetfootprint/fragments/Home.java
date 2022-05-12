package com.example.vetfootprint.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vetfootprint.R;
import com.example.vetfootprint.activitys.CadastroAnimal;
import com.example.vetfootprint.controller.adapterCardDog;
import com.example.vetfootprint.model.modelRecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Home extends Fragment  {

    adapterCardDog adapterCardDog;
    DatabaseReference mBase;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        mBase = FirebaseDatabase.getInstance().getReference().child("animal");

        FloatingActionButton floatingBtnAdicionarAnimal = view.findViewById(R.id.floating_btn_add_dog_home_fragment);
        RecyclerView recyclerViewAnimal = view.findViewById(R.id.recyclerviewAnimal);

        recyclerViewAnimal.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<modelRecyclerView> options =
                new FirebaseRecyclerOptions.Builder<modelRecyclerView>()
                        .setQuery(mBase, modelRecyclerView.class)
                        .build();


        adapterCardDog = new adapterCardDog(options, getContext());
        recyclerViewAnimal.setAdapter(adapterCardDog);
        recyclerViewAnimal.setItemAnimator(null);




        floatingBtnAdicionarAnimal.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), CadastroAnimal.class);
            startActivity(intent);
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterCardDog.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterCardDog.stopListening();
    }


}