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

public class Adapter_List_Radio extends RecyclerView.Adapter<Adapter_List_Radio.HolderAndroid> {

    private Context context;
    private ArrayList<Model_Radio> androidArrayList;

    public Adapter_List_Radio(Context context, ArrayList<Model_Radio> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Stash.init(context);

        View view = LayoutInflater.from(context).inflate(R.layout.row_radio_list, parent, false);
        return new HolderAndroid(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAndroid holder, int position) {
        Model_Radio modelAndroid = androidArrayList.get(position);

        String name_channel = modelAndroid.getName();
        String link_channel = modelAndroid.getLink();
        String id_channel = modelAndroid.getId();

        holder.name.setText(name_channel);
        holder.link.setText(link_channel);
        holder.id.setText(id_channel);
        Glide.with(context).load(androidArrayList.get(position).getImage1()).placeholder(R.drawable.logo).into(holder.image1);

        holder.card_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String linkText = holder.link.getText().toString().trim();
                String nameText = holder.name.getText().toString().trim();
                String idText = holder.id.getText().toString().trim();
                Intent intent = new Intent(context , Edit_Radio.class);
                intent.putExtra("link" , linkText);
                intent.putExtra("name" , nameText);
                intent.putExtra("id" , idText);
                intent.putExtra("img" , androidArrayList.get(position).getImage1());
                context.startActivity(intent);
            }
        });

        if (position % 2 == 0){
            holder.view_top.setVisibility(View.VISIBLE);
        }
        else {
            holder.view_top.setVisibility(View.GONE);
        }

        holder.delete_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

                databaseReference.child("Radio_app3").child(modelAndroid.getId()).setValue(null);

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
        private TextView name, link;
        private ImageView delete_btn2 ;
        private CardView card_radio;
        private View view_top;
        private TextView id;

        HolderAndroid(@NonNull View itemView) {
            super(itemView);

            image1 = itemView.findViewById(R.id.radio_img2);
            delete_btn2 = itemView.findViewById(R.id.detete_btn2);
            name = itemView.findViewById(R.id.title_radio2);
            link = itemView.findViewById(R.id.link_radio2);
            card_radio = (CardView) itemView.findViewById(R.id.card_radio_list);
            view_top = itemView.findViewById(R.id.view_top2);
            id = itemView.findViewById(R.id.id_radio_list);

        }
    }
}
