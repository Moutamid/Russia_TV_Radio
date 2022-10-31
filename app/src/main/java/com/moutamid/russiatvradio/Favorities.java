package com.moutamid.russiatvradio;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

import static com.moutamid.russiatvradio.Settings_Activity.SHARED_PREFS;
import static com.moutamid.russiatvradio.Settings_Activity.TEXT1_1;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Favorities extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Fav_Channel fav_channel;
    private Fav_Radio fav_radio;

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
            context = LocaleHelper.setLocale(Favorities.this, "en");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.favorites));
        }
        if (lang.equals("French")){
            context = LocaleHelper.setLocale(Favorities.this, "fr");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.favorites));
        }
        if (lang.equals("German")){
            context = LocaleHelper.setLocale(Favorities.this, "de");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.favorites));
        }
        if (lang.equals("Arabic")){
            context = LocaleHelper.setLocale(Favorities.this, "ar");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.favorites));
        }
        if (lang.equals("Turkish")){
            context = LocaleHelper.setLocale(Favorities.this, "tr");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.favorites));
        }
        if (lang.equals("Russian")){
            context = LocaleHelper.setLocale(Favorities.this, "ru");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.favorites));
        }
        if (lang.equals("Spanish")){
            context = LocaleHelper.setLocale(Favorities.this, "es");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.favorites));
        }
        if (lang.equals("Urdu")){
            context = LocaleHelper.setLocale(Favorities.this, "ur");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.favorites));
        }

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorities);

        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);


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

        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager mViewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        fav_channel = new Fav_Channel();
        fav_radio = new Fav_Radio();

        adapter.addFragment(fav_channel, "Channels");
        adapter.addFragment(fav_radio, "Radio");

        title_main = findViewById(R.id.title_main);
        title_lang = findViewById(R.id.title_lang);

        mViewPager.setAdapter(adapter);
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm, int behaviour) {
            super(fm, behaviour);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }


    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        text1_1 = sharedPreferences.getString(TEXT1_1 , "English");
        title_lang.setText(text1_1);
    }
}