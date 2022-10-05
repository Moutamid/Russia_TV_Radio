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

import java.util.ArrayList;

public class Adapter_Fav_Channel extends RecyclerView.Adapter<Adapter_Fav_Channel.HolderAndroid> {

    private Context context;
    private ArrayList<Model_Channel> androidArrayList;

    public Adapter_Fav_Channel(Context context, ArrayList<Model_Channel> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_fav, parent, false);
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

        holder.name.setText(name_channel);
        holder.des.setText(des_channel);
        holder.cast.setText(cast_channel);
        holder.time.setText(time_channel);
        holder.link.setText(link_channel);
        Glide.with(context).load(androidArrayList.get(position).getImage1()).placeholder(R.drawable.logo).into(holder.image1);

        holder.card_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String linkText = holder.link.getText().toString().trim();
                String nameText = holder.name.getText().toString().trim();
                Intent intent = new Intent(context , VedioActivity.class);
                intent.putExtra("link" , linkText);
                intent.putExtra("name" , nameText);
                context.startActivity(intent);
            }
        });

        holder.btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Model_Channel> our_arraylist = Stash.getArrayList("name_of_arraylist", Model_Channel.class);

                for (int i=0 ; i<our_arraylist.size(); i++) {
                    if (our_arraylist.get(i).name.equals(modelAndroid.name)) {//Q K CURRENT MODEL YE H // AB SAVE KAHAN KAR RHY HO WO FILE OPEN KARO
                        our_arraylist.remove(i);
                    }
                }
                Stash.put("name_of_arraylist",our_arraylist);

                androidArrayList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
                Toast.makeText(context, "Removed From Favorities", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return androidArrayList.size();
    }

    class HolderAndroid extends RecyclerView.ViewHolder {

        private ImageView image1 ;
        private ImageView btn_fav ;
        private TextView name , des , cast , time , link;
        private CardView card_channel;

        HolderAndroid(@NonNull View itemView) {
            super(itemView);

            image1 = itemView.findViewById(R.id.channel_img2);
            btn_fav = itemView.findViewById(R.id.btn_fav2);
            name = itemView.findViewById(R.id.title_channel2);
            des = itemView.findViewById(R.id.description_channel2);
            cast = itemView.findViewById(R.id.cast_channel2);
            time = itemView.findViewById(R.id.time_channel2);
            link = itemView.findViewById(R.id.link_channel2);
            card_channel = itemView.findViewById(R.id.card_channel2);

        }
    }
}
