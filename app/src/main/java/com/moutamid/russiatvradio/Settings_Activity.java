package com.moutamid.russiatvradio;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Settings_Activity extends AppCompatActivity {

    TextView text1 , text2 , text3 , text4 , text5 , text6;
    TextView btn_update;
    Spinner test_spinner1 , test_spinner2 , test_spinner3 , test_spinner4 ,test_spinner6;

    String[] listItems1;
    String[] listItems2;
    String[] listItems3;
    String[] listItems4;
    String[] listItems6;

    InterstitialAd mInterstitialAd;
    AdRequest adRequest;

    TextView text_setting_1;
    TextView text_setting_2;
    TextView text_setting_3;
    TextView text_setting_4;
    TextView text_setting_5;
    TextView text_setting_6;

    TextView title_main;
    TextView title_lang;

    Context context;
    Resources resources;

    public static final String SHARED_PREFS = "sharedPrefs";

    public static final String TEXT1_1 = "text1_1";
    private String text1_1;

    public static final String TEXT1_2 = "text1_2";
    private String text1_2;

    public static final String TEXT1_3 = "text1_3";
    private String text1_3;

    public static final String TEXT1_4 = "text1_4";
    private String text1_4;

    public static final String TEXT1_6 = "text1_6";
    private String text1_6;

    @Override
    protected void onStart() {
        current_data();
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text_2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        btn_update = findViewById(R.id.btn_update);

        title_main = findViewById(R.id.title_main);
        title_lang = findViewById(R.id.title_lang);

        test_spinner1 = findViewById(R.id.test_spinner1);
        test_spinner2 = findViewById(R.id.test_spinner2);
        test_spinner3 = findViewById(R.id.test_spinner3);
        test_spinner4 = findViewById(R.id.test_spinner4);
        test_spinner6 = findViewById(R.id.test_spinner6);

        text_setting_1 = findViewById(R.id.text_setting_1);
        text_setting_2 = findViewById(R.id.text_setting_2);
        text_setting_3 = findViewById(R.id.text_setting_3);
        text_setting_4 = findViewById(R.id.text_setting_4);
        text_setting_5 = findViewById(R.id.text_setting_5);
        text_setting_6 = findViewById(R.id.text_setting_6);

        loadData();
        updateData();

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

        String myCurrent_dateTime = SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        text5.setText(myCurrent_dateTime);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                current_data();
                Toast.makeText(Settings_Activity.this, "Saved Cahnges", Toast.LENGTH_SHORT).show();
            }
        });

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems1 = new String[]{
                        "English",
                        "French",
                        "German",
                        "Arabic",
                        "Turkish",
                        "Russian",
                        "Spanish",
                        "Urdu",
                };
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Settings_Activity.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setSingleChoiceItems(listItems1, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        text1.setText(listItems1[i]);
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems2 = new String[]{
                        "ON",
                        "OFF"};
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Settings_Activity.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setSingleChoiceItems(listItems2, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        text2.setText(listItems2[i]);
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems3 = new String[]{
                        "OFF",
                        "30 Minutes",
                        "1 Hour",
                        "1.5 Hour",
                        "2 Hour",
                        "2.5 Hour",
                        "3 Hour",
                };
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Settings_Activity.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setSingleChoiceItems(listItems3, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        text3.setText(listItems3[i]);
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems4 = new String[]{
                        "UTC+05:00, Asia",
                        "UTC+05:00, India",
                        "UTC+05:00, Australia",
                        "UTC+05:00, Hongkong",
                        "UTC+05:00, Singapore",
                        "UTC+05:00, Europe",
                        "UTC+05:00, Africa",
                };
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Settings_Activity.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setSingleChoiceItems(listItems4, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        text4.setText(listItems4[i]);
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        text6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItems6 = new String[]{
                        "24-Hour",
                        "AM/PM"};
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Settings_Activity.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setSingleChoiceItems(listItems6, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        text6.setText(listItems6[i]);
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
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
                    mInterstitialAd.show(Settings_Activity.this);
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                }
            }
        }, 5000);
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT1_1 , text1.getText().toString());
        editor.putString(TEXT1_2 , text2.getText().toString());
        editor.putString(TEXT1_3 , text3.getText().toString());
        editor.putString(TEXT1_4 , text4.getText().toString());
        editor.putString(TEXT1_6 , text6.getText().toString());

        editor.apply();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        text1_1 = sharedPreferences.getString(TEXT1_1 , "English");
        text1_2 = sharedPreferences.getString(TEXT1_2 , "OFF");
        text1_3 = sharedPreferences.getString(TEXT1_3 , "OFF");
        text1_4 = sharedPreferences.getString(TEXT1_4 , "UTC+05:00, Europe");
        text1_6 = sharedPreferences.getString(TEXT1_6 , "AM/PM");
        title_lang.setText(text1_1);
    }

    public void updateData() {
        text1.setText(text1_1);
        text2.setText(text1_2);
        text3.setText(text1_3);
        text4.setText(text1_4);
        text6.setText(text1_6);
    }

    public void current_data(){
        loadData();
        String lang = title_lang.getText().toString().trim();
        if (lang.equals("English")){
            context = LocaleHelper.setLocale(Settings_Activity.this, "en");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.settings));
            text_setting_1.setText(resources.getString(R.string.application_language));
            text_setting_2.setText(resources.getString(R.string.button_help_text));
            text_setting_3.setText(resources.getString(R.string.my_sleep_timer));
            text_setting_4.setText(resources.getString(R.string.my_time_zone));
            text_setting_5.setText(resources.getString(R.string.my_current_time));
            text_setting_6.setText(resources.getString(R.string.time_format));
            btn_update.setText(resources.getString(R.string.save_changes));
        }
        if (lang.equals("French")){
            context = LocaleHelper.setLocale(Settings_Activity.this, "fr");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.settings));
            text_setting_1.setText(resources.getString(R.string.application_language));
            text_setting_2.setText(resources.getString(R.string.button_help_text));
            text_setting_3.setText(resources.getString(R.string.my_sleep_timer));
            text_setting_4.setText(resources.getString(R.string.my_time_zone));
            text_setting_5.setText(resources.getString(R.string.my_current_time));
            text_setting_6.setText(resources.getString(R.string.time_format));
            btn_update.setText(resources.getString(R.string.save_changes));
        }
        if (lang.equals("German")){
            context = LocaleHelper.setLocale(Settings_Activity.this, "de");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.settings));
            text_setting_1.setText(resources.getString(R.string.application_language));
            text_setting_2.setText(resources.getString(R.string.button_help_text));
            text_setting_3.setText(resources.getString(R.string.my_sleep_timer));
            text_setting_4.setText(resources.getString(R.string.my_time_zone));
            text_setting_5.setText(resources.getString(R.string.my_current_time));
            text_setting_6.setText(resources.getString(R.string.time_format));
            btn_update.setText(resources.getString(R.string.save_changes));
        }
        if (lang.equals("Arabic")){
            context = LocaleHelper.setLocale(Settings_Activity.this, "ar");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.settings));
            text_setting_1.setText(resources.getString(R.string.application_language));
            text_setting_2.setText(resources.getString(R.string.button_help_text));
            text_setting_3.setText(resources.getString(R.string.my_sleep_timer));
            text_setting_4.setText(resources.getString(R.string.my_time_zone));
            text_setting_5.setText(resources.getString(R.string.my_current_time));
            text_setting_6.setText(resources.getString(R.string.time_format));
            btn_update.setText(resources.getString(R.string.save_changes));
        }
        if (lang.equals("Turkish")){
            context = LocaleHelper.setLocale(Settings_Activity.this, "tr");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.settings));
            text_setting_1.setText(resources.getString(R.string.application_language));
            text_setting_2.setText(resources.getString(R.string.button_help_text));
            text_setting_3.setText(resources.getString(R.string.my_sleep_timer));
            text_setting_4.setText(resources.getString(R.string.my_time_zone));
            text_setting_5.setText(resources.getString(R.string.my_current_time));
            text_setting_6.setText(resources.getString(R.string.time_format));
            btn_update.setText(resources.getString(R.string.save_changes));
        }
        if (lang.equals("Russian")){
            context = LocaleHelper.setLocale(Settings_Activity.this, "ru");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.settings));
            text_setting_1.setText(resources.getString(R.string.application_language));
            text_setting_2.setText(resources.getString(R.string.button_help_text));
            text_setting_3.setText(resources.getString(R.string.my_sleep_timer));
            text_setting_4.setText(resources.getString(R.string.my_time_zone));
            text_setting_5.setText(resources.getString(R.string.my_current_time));
            text_setting_6.setText(resources.getString(R.string.time_format));
            btn_update.setText(resources.getString(R.string.save_changes));
        }
        if (lang.equals("Spanish")){
            context = LocaleHelper.setLocale(Settings_Activity.this, "es");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.settings));
            text_setting_1.setText(resources.getString(R.string.application_language));
            text_setting_2.setText(resources.getString(R.string.button_help_text));
            text_setting_3.setText(resources.getString(R.string.my_sleep_timer));
            text_setting_4.setText(resources.getString(R.string.my_time_zone));
            text_setting_5.setText(resources.getString(R.string.my_current_time));
            text_setting_6.setText(resources.getString(R.string.time_format));
            btn_update.setText(resources.getString(R.string.save_changes));
        }
        if (lang.equals("Urdu")){
            context = LocaleHelper.setLocale(Settings_Activity.this, "ur");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.settings));
            text_setting_1.setText(resources.getString(R.string.application_language));
            text_setting_2.setText(resources.getString(R.string.button_help_text));
            text_setting_3.setText(resources.getString(R.string.my_sleep_timer));
            text_setting_4.setText(resources.getString(R.string.my_time_zone));
            text_setting_5.setText(resources.getString(R.string.my_current_time));
            text_setting_6.setText(resources.getString(R.string.time_format));
            btn_update.setText(resources.getString(R.string.save_changes));
        }
    }

}