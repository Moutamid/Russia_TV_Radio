package com.moutamid.russiatvradio;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fxn.stash.Stash;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Adapter_List_Channel extends RecyclerView.Adapter<Adapter_List_Channel.HolderAndroid> {

    private Context context;
    private ArrayList<Model_Channel> androidArrayList;

    public Adapter_List_Channel(Context context, ArrayList<Model_Channel> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Stash.init(context);

        View view = LayoutInflater.from(context).inflate(R.layout.row_channel_list, parent, false);
        return new HolderAndroid(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAndroid holder, int position) {
        Model_Channel modelAndroid = androidArrayList.get(position);

        String name_channel = modelAndroid.getName();
        String des_channel = modelAndroid.getDes();
        String cast_channel = modelAndroid.getCast();
        String time_channel = modelAndroid.getTime();
        String link_channel = modelAndroid.getLink();
        String id_channel = modelAndroid.getId();

        holder.name.setText(name_channel);
        holder.des.setText(des_channel);
        holder.cast.setText(cast_channel);
        holder.time.setText(time_channel);
        holder.link.setText(link_channel);
        holder.id.setText(id_channel);
        Glide.with(context).load(androidArrayList.get(position).getImage1()).placeholder(R.drawable.logo).into(holder.image1);

        holder.card_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String linkText = holder.link.getText().toString().trim();
                String nameText = holder.name.getText().toString().trim();
                String desText = holder.des.getText().toString().trim();
                String castText = holder.cast.getText().toString().trim();
                String timeText = holder.time.getText().toString().trim();
                String idText = holder.id.getText().toString().trim();
                Intent intent = new Intent(context , Edit_Channel.class);
                intent.putExtra("link" , linkText);
                intent.putExtra("name" , nameText);
                intent.putExtra("des" , desText);
                intent.putExtra("cast" , castText);
                intent.putExtra("time" , timeText);
                intent.putExtra("id" , idText);
                context.startActivity(intent);
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

                databaseReference.child("Channels_app3").child(modelAndroid.getId()).setValue(null);

                androidArrayList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Item Removed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return androidArrayList.size();
    }

    class HolderAndroid extends RecyclerView.ViewHolder {
        private ImageView image1 ;
        private TextView id;
        private ImageView btn_delete ;
        private TextView name , des , cast , time , link;
        private CardView card_channel;

        HolderAndroid(@NonNull View itemView) {
            super(itemView);

            image1 = itemView.findViewById(R.id.channel_img_list);
            btn_delete = itemView.findViewById(R.id.delete_btn);
            name = itemView.findViewById(R.id.title_channel_list);
            id = itemView.findViewById(R.id.id_channel_list);
            des = itemView.findViewById(R.id.description_channel_list);
            cast = itemView.findViewById(R.id.cast_channel_list);
            time = itemView.findViewById(R.id.time_channel_list);
            link = itemView.findViewById(R.id.link_channel_list);
            card_channel = itemView.findViewById(R.id.card_channel_list);

        }
    }
}
