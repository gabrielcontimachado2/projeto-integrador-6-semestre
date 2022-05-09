package com.example.vetfootprint.controller;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vetfootprint.R;
import com.example.vetfootprint.activitys.PerfilAnimal;
import com.example.vetfootprint.model.modelRecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class adapterCardDog extends FirebaseRecyclerAdapter<modelRecyclerView, adapterCardDog.myviewhodler> {

    Context context;

    public adapterCardDog(@NonNull FirebaseRecyclerOptions<modelRecyclerView> options, Context context) {
        super(options);

        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewhodler holder, int position, @NonNull modelRecyclerView model) {
        holder.sDogName.setText(model.getAnimalName());
        holder.sDogAge.setText(model.getAnimalAge());
        Glide.with(holder.imageDog.getContext()).load(model.getUrlImageDog()).into(holder.imageDog);



        holder.deleteDog.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.deleteDog.getContext());
            builder.setTitle("Deletar Cachorro");
            builder.setMessage("Você tem certeza que deseja deletar o animal?");

            builder.setPositiveButton("SIM", (dialogInterface, i) -> FirebaseDatabase.getInstance().getReference().child("animal")
                    .child(Objects.requireNonNull(getRef(position).getKey())).removeValue());

            builder.setNegativeButton("NÃO", (dialogInterface, i) -> {

            });

            builder.show();

        });

       holder.cardViewDog.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                Intent intent = new Intent(context, PerfilAnimal.class);
                intent.putExtra("idAnimal", model.getIdAnimal());
                intent.putExtra("animalName", model.getAnimalName());
                intent.putExtra("image", model.getUrlImageDog());
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
           }
       });


    }

    @NonNull
    @Override
    public myviewhodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cachorro,parent,false);
        return new myviewhodler(view);
    }

    public static class myviewhodler extends RecyclerView.ViewHolder{

        ImageView imageDog, deleteDog;
        TextView sDogName, sDogAge;
        CardView cardViewDog;


        public myviewhodler(@NonNull View itemView) {
            super(itemView);

            cardViewDog = itemView.findViewById(R.id.card_view_dog);
            imageDog = itemView.findViewById(R.id.card_image_dog_photo);
            sDogName = itemView.findViewById(R.id.card_text_nome_do_cachorro_card);
            sDogAge = itemView.findViewById(R.id.card_text_idade_do_cachorro_card);
            deleteDog = itemView.findViewById(R.id.card_image_button_excluir_card_cachorro);

        }
    }

}
