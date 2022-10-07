package com.moutamid.russiatvradio;

import static com.moutamid.russiatvradio.Settings_Activity.SHARED_PREFS;
import static com.moutamid.russiatvradio.Settings_Activity.TEXT1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    LinearLayout fab_bottom_sheet;
    FloatingActionButton fab;
    ImageView tv , radio;

    TextView title_main;
    TextView title_lang;
    TextView text_channel;
    TextView text_radio;

    Context context;
    Resources resources;
    private String text1_1;

    @Override
    protected void onStart() {
        loadData();
        String lang = title_lang.getText().toString().trim();
        if (lang.equals("English")){
            context = LocaleHelper.setLocale(MainActivity.this, "en");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_channel.setText(resources.getString(R.string.live_tv_channels));
            text_radio.setText(resources.getString(R.string.live_radio));
        }
        if (lang.equals("French")){
            context = LocaleHelper.setLocale(MainActivity.this, "fr");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_channel.setText(resources.getString(R.string.live_tv_channels));
            text_radio.setText(resources.getString(R.string.live_radio));
        }
        if (lang.equals("German")){
            context = LocaleHelper.setLocale(MainActivity.this, "de");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_channel.setText(resources.getString(R.string.live_tv_channels));
            text_radio.setText(resources.getString(R.string.live_radio));
        }
        if (lang.equals("Arabic")){
            context = LocaleHelper.setLocale(MainActivity.this, "ar");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_channel.setText(resources.getString(R.string.live_tv_channels));
            text_radio.setText(resources.getString(R.string.live_radio));
        }
        if (lang.equals("Turkish")){
            context = LocaleHelper.setLocale(MainActivity.this, "tr");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_channel.setText(resources.getString(R.string.live_tv_channels));
            text_radio.setText(resources.getString(R.string.live_radio));
        }
        if (lang.equals("Russian")){
            context = LocaleHelper.setLocale(MainActivity.this, "ru");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_channel.setText(resources.getString(R.string.live_tv_channels));
            text_radio.setText(resources.getString(R.string.live_radio));
        }
        if (lang.equals("Spanish")){
            context = LocaleHelper.setLocale(MainActivity.this, "es");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_channel.setText(resources.getString(R.string.live_tv_channels));
            text_radio.setText(resources.getString(R.string.live_radio));
        }
        if (lang.equals("Urdu")){
            context = LocaleHelper.setLocale(MainActivity.this, "ur");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_channel.setText(resources.getString(R.string.live_tv_channels));
            text_radio.setText(resources.getString(R.string.live_radio));
        }

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_channel = findViewById(R.id.text_channel);
        text_radio = findViewById(R.id.text_radio);
        title_main = findViewById(R.id.title_main);
        title_lang = findViewById(R.id.title_lang);

        fab_bottom_sheet = findViewById(R.id.fab_bottom_sheet);
        fab_bottom_sheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheet = new BottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheet = new BottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
            }
        });

        tv = findViewById(R.id.img_tv_russia);
        radio = findViewById(R.id.img_radio_russia);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , Channel_Activity.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivity.this);
            }
        });

        radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , Radio_Activity.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivity.this);
            }
        });
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        text1_1 = sharedPreferences.getString(TEXT1_1 , "English");
        title_lang.setText(text1_1);
    }
}