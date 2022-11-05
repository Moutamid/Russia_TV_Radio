package com.moutamid.russiatvradio;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdsManager;
import com.fxn.stash.Stash;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Channel extends RecyclerView.Adapter<Adapter_Channel.HolderAndroid> {

    private Context context;
    private ArrayList<Model_Channel> androidArrayList;

    private LinearLayout adView;
    private NativeAd nativeAd;

    View containerView;


    public Adapter_Channel(Context context, ArrayList<Model_Channel> androidArrayList) {
        this.context = context;
        this.androidArrayList = androidArrayList;
    }

    @NonNull
    @Override
    public HolderAndroid onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Stash.init(context);
        containerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.nativefacebook_ads,parent,false);
        View view = LayoutInflater.from(context).inflate(R.layout.row_channels, parent, false);
        return new HolderAndroid(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HolderAndroid holder, @SuppressLint("RecyclerView") int position) {
        AudienceNetworkAds.initialize(context);

        nativeAd = new NativeAd(context, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID");

        NativeAdListener nativeAdListener = new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {
                // Native ad finished downloading all assets
                Log.e("ADSNATIVE", "Native ad finished downloading all assets.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Native ad failed to load
                Log.e("ADSNATIVE", "Native ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Native ad is loaded and ready to be displayed
                Log.d("ADSNATIVE", "Native ad is loaded and ready to be displayed!");


                if (nativeAd == null || nativeAd != ad) {
                    return;
                }
                nativeAd.unregisterView();

                // Add the Ad view into the ad container.
                LayoutInflater inflater = LayoutInflater.from(context);
                // Inflate the Ad view.  The layout referenced should be the one you created in the last step.
                adView = (LinearLayout) inflater.inflate(R.layout.nativefacebook_ads, holder.nativeAdLayout, false);
                holder.nativeAdLayout.addView(adView);

                // Add the AdOptionsView
                LinearLayout adChoicesContainer = containerView.findViewById(R.id.ad_choices_container);
                AdOptionsView adOptionsView = new AdOptionsView(context, nativeAd, holder.nativeAdLayout);
                adChoicesContainer.removeAllViews();
                adChoicesContainer.addView(adOptionsView, 0);

                // Create native UI using the ad metadata.
                MediaView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
                TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
                MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
                TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
                TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
                TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
                Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

                // Set the Text.
                nativeAdTitle.setText(nativeAd.getAdvertiserName());
                nativeAdBody.setText(nativeAd.getAdBodyText());
                nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
                nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
                nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
                sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

                // Create a list of clickable views
                List<View> clickableViews = new ArrayList<>();
                clickableViews.add(nativeAdTitle);
                clickableViews.add(nativeAdCallToAction);

                // Register the Title and CTA button to listen for clicks.
                nativeAd.registerViewForInteraction(
                        adView, nativeAdMedia, nativeAdIcon, clickableViews);


                holder.nativeAdLayout.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAdClicked(Ad ad) {
                // Native ad clicked
                Log.d("ADSNATIVE", "Native ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Native ad impression
                Log.d("ADSNATIVE", "Native ad impression logged!");
            }
        };

        // Request an ad
        nativeAd.loadAd(
                nativeAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());

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

        if (Stash.getBoolean(position+""))
            holder.btn_fav.setImageResource(R.drawable.ic_baseline_favorite_24);
        else holder.btn_fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);

        holder.btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Stash.getBoolean(position+"")){
                    // REMOVE FAVOURITE
                    holder.btn_fav.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    Stash.put(position+"", false);
                }else {
                    // SAVE FAVOURITE
                    holder.btn_fav.setImageResource(R.drawable.ic_baseline_favorite_24);
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

                holder.btn_fav_done.setVisibility(View.VISIBLE);
                holder.btn_fav.setVisibility(View.GONE);
                ArrayList<Model_Channel> our_arraylist = Stash.getArrayList("name_of_arraylist" ,Model_Channel.class);
                our_arraylist.add(modelAndroid);
                Stash.put("name_of_arraylist" , our_arraylist);
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
        private ImageView btn_fav ;
        private ImageView btn_fav_done ;
        private TextView name , des , cast , time , link;
        private CardView card_channel;
        NativeAdLayout nativeAdLayout;

        HolderAndroid(@NonNull View itemView) {
            super(itemView);

            image1 = itemView.findViewById(R.id.channel_img);
            btn_fav = itemView.findViewById(R.id.btn_fav);
            btn_fav_done = itemView.findViewById(R.id.btn_fav_done);
            name = itemView.findViewById(R.id.title_channel);
            des = itemView.findViewById(R.id.description_channel);
            cast = itemView.findViewById(R.id.cast_channel);
            time = itemView.findViewById(R.id.time_channel);
            link = itemView.findViewById(R.id.link_channel);
            card_channel = itemView.findViewById(R.id.card_channel);
            nativeAdLayout = itemView.findViewById(R.id.native_ad_container);
        }
    }
}
