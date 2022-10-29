package com.moutamid.russiatvradio;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.webilisim.webplayer.WEBPlayer;
import net.webilisim.webplayer.WEBPlayerMInterface;
import net.webilisim.webplayer.WEBPlayerMediaSystem;
import net.webilisim.webplayer.WEBPlayerStd;
import net.webilisim.webplayer.WEBPlayerUtils;

public class VedioActivity extends AppCompatActivity {
    WEBPlayerStd webPlayerStd;
    RelativeLayout layout_options;

    TextView link , name;

    ImageView dec_brightness , inc_brightness;
    ImageView dec_sound , inc_sound;
    ImageView cast, startBtn;
    ImageView stop;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedio);

        link = findViewById(R.id.link);
        name = findViewById(R.id.name);

        dec_brightness = findViewById(R.id.dec_brightness);
        inc_brightness = findViewById(R.id.inc_brightness);
        dec_sound = findViewById(R.id.dec_sound);
        inc_sound = findViewById(R.id.inc_sound);
        cast = findViewById(R.id.cast);
        startBtn = findViewById(R.id.startBtn);
        stop = findViewById(R.id.stop);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            link.setText(bundle.getString("link"));
            name.setText(bundle.getString("name"));
        }

        String link_text = link.getText().toString().trim();
        String name_text = name.getText().toString().trim();

        webPlayerStd = findViewById(R.id.webplayer);
        layout_options = findViewById(R.id.layout_buttons);

        webPlayerStd.setUp(link_text , name_text);

        startBtn.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setDataAndType(Uri.parse(link_text),"video/*");
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(i, "Choose Your Player"));
        });

        AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        if(!Settings.System.canWrite(this)){
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + this.getPackageName()));
            startActivity(intent);
        }

        dec_brightness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver contentResolver = getApplicationContext().getContentResolver();
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, 100);
            }
        });

        inc_brightness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver contentResolver = getApplicationContext().getContentResolver();
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, 225);
            }
        });

        dec_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND);
            }
        });

        inc_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
            }
        });

        cast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("android.settings.CAST_SETTINGS"));
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VedioActivity.this , MainActivity.class);
                startActivity(intent);
                Toast.makeText(VedioActivity.this, "Stop", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (WEBPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        WEBPlayer.releaseAllVideos();
    }
}