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

public class Adapter_Radio extends RecyclerView.Adapter<Adapter_Radio.HolderAndroid> {

    private Context context;
    private ArrayList<Model_Radio> androidArrayList;

    public Adapter_Radio(Context context, ArrayList<Model_Radio> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Stash.init(context);

        View view = LayoutInflater.from(context).inflate(R.layout.row_radio, parent, false);
        return new HolderAndroid(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAndroid holder, int position) {
        Model_Radio modelAndroid = androidArrayList.get(position);

        String name_channel = modelAndroid.getName();
        String link_channel = modelAndroid.getLink();

        holder.name.setText(name_channel);
        holder.link.setText(link_channel);
        Glide.with(context).load(androidArrayList.get(position).getImage1()).placeholder(R.drawable.logo).into(holder.image1);

        holder.card_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show();
                String linkText = holder.link.getText().toString().trim();
                String nameText = holder.name.getText().toString().trim();
                Intent intent = new Intent(context , MusicActivity.class);
                intent.putExtra("link2" , linkText);
                intent.putExtra("name2" , nameText);
                context.startActivity(intent);
            }
        });

        if (position % 2 == 0){
            holder.view_top.setVisibility(View.VISIBLE);
        }
        else {
            holder.view_top.setVisibility(View.GONE);
        }

        if (Stash.getBoolean(position+""))
            holder.btn_fav2.setImageResource(R.drawable.ic_baseline_favorite_24);
        else holder.btn_fav2.setImageResource(R.drawable.ic_baseline_favorite_border_24);

        holder.btn_fav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Stash.getBoolean(position+"")){
                    // REMOVE FAVOURITE
                    holder.btn_fav2.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    Stash.put(position+"", false);
                }else {
                    // SAVE FAVOURITE
                    holder.btn_fav2.setImageResource(R.drawable.ic_baseline_favorite_24);
                    Stash.put(position+"", true);
                }

                /*if (modelAndroid.isFavourite()){
                    // REMOVE  FAVOURITE

                    modelAndroid.setFavourite(false);
                    holder.btn_fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    Stash.put();// YE LIST JAHAN SE RETREIVE HUI HE WAHAN E SAVE KRNI HE YAHAN

                }else {
                    // ADD FAVOURITE
                    modelAndroid.setFavourite(true);
                    holder.btn_fav.setImageResource(R.drawable.ic_baseline_favorite_24);
                }*/

                holder.btn_fav_done2.setVisibility(View.VISIBLE);
                holder.btn_fav2.setVisibility(View.GONE);
                ArrayList<Model_Radio> our_arraylist = Stash.getArrayList("name_of_radio" , Model_Radio.class);
                our_arraylist.add(modelAndroid);
                Stash.put("name_of_radio" , our_arraylist);
                Toast.makeText(context, "Added to Favorities", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return androidArrayList.size();
    }

    class HolderAndroid extends RecyclerView.ViewHolder {

        private ImageView image1 ;
        private TextView name, link;
        private ImageView btn_fav2 ;
        private ImageView btn_fav_done2 ;
        private CardView card_channel;
        private View view_top;

        HolderAndroid(@NonNull View itemView) {
            super(itemView);

            image1 = itemView.findViewById(R.id.radio_img);
            btn_fav2 = itemView.findViewById(R.id.btn_fav2);
            btn_fav_done2 = itemView.findViewById(R.id.btn_fav_done2);
            name = itemView.findViewById(R.id.title_radio);
            link = itemView.findViewById(R.id.link_radio);
            card_channel = itemView.findViewById(R.id.card_radio);
            view_top = itemView.findViewById(R.id.view_top);
        }
    }
}
