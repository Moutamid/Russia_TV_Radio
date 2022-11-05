package com.moutamid.russiatvradio;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import static com.moutamid.russiatvradio.Settings_Activity.SHARED_PREFS;
import static com.moutamid.russiatvradio.Settings_Activity.TEXT1_1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.NativeAdsManager;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Channel_Activity extends AppCompatActivity {

    FloatingActionButton fab_channel;

    RecyclerView mOnline_Recycler;
    ArrayList<Model_Channel> modelOnlines_list;
    private DatabaseReference databaseReference;
    ProgressDialog pd;

    TextView title_main;
    TextView title_lang;

    Adapter_Channel adapter_online;

    NativeAdsManager nativeAdsManager;

    InterstitialAd mInterstitialAd;
    AdRequest adRequest;

    Context context;
    Resources resources;
    private String text1_1;

    @Override
    protected void onStart() {
        loadData();
        String lang = title_lang.getText().toString().trim();
        if (lang.equals("English")){
            context = LocaleHelper.setLocale(Channel_Activity.this, "en");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_tv_channels));
        }
        if (lang.equals("French")){
            context = LocaleHelper.setLocale(Channel_Activity.this, "fr");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_tv_channels));
        }
        if (lang.equals("German")){
            context = LocaleHelper.setLocale(Channel_Activity.this, "de");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_tv_channels));
        }
        if (lang.equals("Arabic")){
            context = LocaleHelper.setLocale(Channel_Activity.this, "ar");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_tv_channels));
        }
        if (lang.equals("Turkish")){
            context = LocaleHelper.setLocale(Channel_Activity.this, "tr");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_tv_channels));
        }
        if (lang.equals("Russian")){
            context = LocaleHelper.setLocale(Channel_Activity.this, "ru");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_tv_channels));
        }
        if (lang.equals("Spanish")){
            context = LocaleHelper.setLocale(Channel_Activity.this, "es");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_tv_channels));
        }
        if (lang.equals("Urdu")){
            context = LocaleHelper.setLocale(Channel_Activity.this, "ur");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_tv_channels));
        }

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        AudienceNetworkAds.initialize(this);

        pd = new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.setMessage("Fetching data please wait or check your internet");
        pd.setCanceledOnTouchOutside(true);
        mOnline_Recycler = findViewById(R.id.recyclerView_channel);

        title_main = findViewById(R.id.title_main);
        title_lang = findViewById(R.id.title_lang);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        fab_channel = findViewById(R.id.fab_channel);
        fab_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Channel_Activity.this , Channel_List.class);
                startActivity(intent);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Channel_Activity.this , 1);
        mOnline_Recycler.setLayoutManager(gridLayoutManager);

        modelOnlines_list = new ArrayList<>();
        adapter_online = new Adapter_Channel(Channel_Activity.this , modelOnlines_list);
        mOnline_Recycler.setAdapter(adapter_online);

        databaseReference = FirebaseDatabase.getInstance().getReference("Channels_app3");
        pd.show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                modelOnlines_list.clear();

                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Model_Channel modelOnline = itemSnapshot.getValue(Model_Channel.class);
                    modelOnlines_list.add(modelOnline);
                }
                adapter_online.notifyDataSetChanged();
                pd.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                pd.dismiss();
            }
        });

        loadIntersAd();
    }

    private void loadIntersAd() {
        InterstitialAd.load(this,getResources().getString(R.string.ad_interst_id), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdClicked() {
                                // Called when a click is recorded for an ad.
                                Log.d(TAG, "Ad was clicked.");
                            }

                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when ad is dismissed.
                                // Set the ad reference to null so you don't show the ad a second time.
                                Log.d(TAG, "Ad dismissed fullscreen content.");
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when ad fails to show.
                                Log.e(TAG, "Ad failed to show fullscreen content.");
                                mInterstitialAd = null;
                            }

                            @Override
                            public void onAdImpression() {
                                // Called when an impression is recorded for an ad.
                                Log.d(TAG, "Ad recorded an impression.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when ad is shown.
                                Log.d(TAG, "Ad showed fullscreen content.");
                            }
                        });

                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(Channel_Activity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
            }
        }, 5000);
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        text1_1 = sharedPreferences.getString(TEXT1_1 , "English");
        title_lang.setText(text1_1);
    }
}