package com.moutamid.russiatvradio;

import static com.moutamid.russiatvradio.Settings_Activity.SHARED_PREFS;
import static com.moutamid.russiatvradio.Settings_Activity.TEXT1_1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Radio_Activity extends AppCompatActivity {

    FloatingActionButton fab_channel;

    RecyclerView mOnline_Recycler;
    ArrayList<Model_Radio> modelOnlines_list;
    private DatabaseReference databaseReference;
    ProgressDialog pd;

    TextView title_main;
    TextView title_lang;

    Context context;
    Resources resources;
    private String text1_1;

    @Override
    protected void onStart() {
        loadData();
        String lang = title_lang.getText().toString().trim();
        if (lang.equals("English")){
            context = LocaleHelper.setLocale(Radio_Activity.this, "en");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_radio));
        }
        if (lang.equals("French")){
            context = LocaleHelper.setLocale(Radio_Activity.this, "fr");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_radio));
        }
        if (lang.equals("German")){
            context = LocaleHelper.setLocale(Radio_Activity.this, "de");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_radio));
        }
        if (lang.equals("Arabic")){
            context = LocaleHelper.setLocale(Radio_Activity.this, "ar");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_radio));
        }
        if (lang.equals("Turkish")){
            context = LocaleHelper.setLocale(Radio_Activity.this, "tr");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_radio));
        }
        if (lang.equals("Russian")){
            context = LocaleHelper.setLocale(Radio_Activity.this, "ru");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_radio));
        }
        if (lang.equals("Spanish")){
            context = LocaleHelper.setLocale(Radio_Activity.this, "es");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_radio));
        }
        if (lang.equals("Urdu")){
            context = LocaleHelper.setLocale(Radio_Activity.this, "ur");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.live_radio));
        }

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);

        pd = new ProgressDialog(this);
        pd.setTitle("Loading...");
        pd.setMessage("Fetching data please wait or check your internet");
        pd.setCanceledOnTouchOutside(true);
        mOnline_Recycler = findViewById(R.id.recyclerView_radio);

        title_main = findViewById(R.id.title_main);
        title_lang = findViewById(R.id.title_lang);

        fab_channel = findViewById(R.id.fab_channel2);
        fab_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Radio_Activity.this , Radio_List.class);
                startActivity(intent);
            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(Radio_Activity.this , 2);
        mOnline_Recycler.setLayoutManager(gridLayoutManager);

        modelOnlines_list = new ArrayList<>();
        Adapter_Radio adapter_online = new Adapter_Radio(Radio_Activity.this , modelOnlines_list);
        mOnline_Recycler.setAdapter(adapter_online);

        databaseReference = FirebaseDatabase.getInstance().getReference("Radio_app3");
        pd.show();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                modelOnlines_list.clear();

                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Model_Radio modelOnline = itemSnapshot.getValue(Model_Radio.class);
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

    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        text1_1 = sharedPreferences.getString(TEXT1_1 , "English");
        title_lang.setText(text1_1);
    }
}