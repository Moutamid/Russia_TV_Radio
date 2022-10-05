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

public class Adapter_Fav_Radio extends RecyclerView.Adapter<Adapter_Fav_Radio.HolderAndroid> {

    private Context context;
    private ArrayList<Model_Radio> androidArrayList;

    public Adapter_Fav_Radio(Context context, ArrayList<Model_Radio> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Stash.init(context);

        View view = LayoutInflater.from(context).inflate(R.layout.row_fav_radio, parent, false);
        return new HolderAndroid(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAndroid holder, int position) {
        Model_Radio modelAndroid = androidArrayList.get(position);

        String name_channel = modelAndroid.getName();
        String link_channel = modelAndroid.getLink();

        holder.name2.setText(name_channel);
        holder.link2.setText(link_channel);
        Glide.with(context).load(androidArrayList.get(position).getImage1()).placeholder(R.drawable.logo).into(holder.image12);

        if (position % 2 == 0){
            holder.view_top2.setVisibility(View.VISIBLE);
        }
        else {
            holder.view_top2.setVisibility(View.GONE);
        }

        holder.card_channel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Item Clicked", Toast.LENGTH_SHORT).show();
                String linkText = holder.link2.getText().toString().trim();
                String nameText = holder.name2.getText().toString().trim();
                Intent intent = new Intent(context , MusicActivity.class);
                intent.putExtra("link" , linkText);
                intent.putExtra("name" , nameText);
                context.startActivity(intent);
            }
        });

        holder.btn_fav22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Model_Radio> our_arraylist = Stash.getArrayList("name_of_radio", Model_Radio.class);

                for (int i=0 ; i<our_arraylist.size(); i++) {
                    if (our_arraylist.get(i).name.equals(modelAndroid.name)) {
                        our_arraylist.remove(i);
                    }
                }
                Stash.put("name_of_radio",our_arraylist);

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

        private ImageView image12 ;
        private TextView name2, link2;
        private ImageView btn_fav22 ;
        private CardView card_channel2;
        private View view_top2;

        HolderAndroid(@NonNull View itemView) {
            super(itemView);

            image12 = itemView.findViewById(R.id.radio_img2);
            btn_fav22 = itemView.findViewById(R.id.btn_fav22);
            name2 = itemView.findViewById(R.id.title_radio2);
            link2 = itemView.findViewById(R.id.link_radio2);
            card_channel2 = itemView.findViewById(R.id.card_radio2);
            view_top2 = itemView.findViewById(R.id.view_top2);

        }
    }
}
