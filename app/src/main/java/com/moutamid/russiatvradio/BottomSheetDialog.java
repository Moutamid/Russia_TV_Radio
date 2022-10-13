package com.moutamid.russiatvradio;

import static android.content.Context.MODE_PRIVATE;
import static com.moutamid.russiatvradio.Settings_Activity.SHARED_PREFS;
import static com.moutamid.russiatvradio.Settings_Activity.TEXT1_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetDialog extends BottomSheetDialogFragment {

    CardView fav , settings ;
    CardView help , feed , rate ;
    CardView report ;

    TextView title_main;
    TextView title_lang;
    TextView text_fav;
    TextView text_setting;
    TextView text_help;
    TextView text_term;
    TextView text_rate;
    TextView text_report;

    Context context;
    Resources resources;
    private String text1_1;


    @Override
    public void onStart() {
        loadData();
        String lang = title_lang.getText().toString().trim();
        if (lang.equals("English")){
            context = LocaleHelper.setLocale(getContext(), "en");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_fav.setText(resources.getString(R.string.favorites));
            text_setting.setText(resources.getString(R.string.settings));
            text_help.setText(resources.getString(R.string.help));
            text_term.setText(resources.getString(R.string.policy_terms));
            text_rate.setText(resources.getString(R.string.rate_us));
            text_report.setText(resources.getString(R.string.report));
        }
        if (lang.equals("French")){
            context = LocaleHelper.setLocale(getContext(), "fr");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_fav.setText(resources.getString(R.string.favorites));
            text_setting.setText(resources.getString(R.string.settings));
            text_help.setText(resources.getString(R.string.help));
            text_term.setText(resources.getString(R.string.policy_terms));
            text_rate.setText(resources.getString(R.string.rate_us));
            text_report.setText(resources.getString(R.string.report));
        }
        if (lang.equals("German")){
            context = LocaleHelper.setLocale(getContext(), "de");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_fav.setText(resources.getString(R.string.favorites));
            text_setting.setText(resources.getString(R.string.settings));
            text_help.setText(resources.getString(R.string.help));
            text_term.setText(resources.getString(R.string.policy_terms));
            text_rate.setText(resources.getString(R.string.rate_us));
            text_report.setText(resources.getString(R.string.report));
        }
        if (lang.equals("Arabic")){
            context = LocaleHelper.setLocale(getContext(), "ar");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_fav.setText(resources.getString(R.string.favorites));
            text_setting.setText(resources.getString(R.string.settings));
            text_help.setText(resources.getString(R.string.help));
            text_term.setText(resources.getString(R.string.policy_terms));
            text_rate.setText(resources.getString(R.string.rate_us));
            text_report.setText(resources.getString(R.string.report));
        }
        if (lang.equals("Turkish")){
            context = LocaleHelper.setLocale(getContext(), "tr");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_fav.setText(resources.getString(R.string.favorites));
            text_setting.setText(resources.getString(R.string.settings));
            text_help.setText(resources.getString(R.string.help));
            text_term.setText(resources.getString(R.string.policy_terms));
            text_rate.setText(resources.getString(R.string.rate_us));
            text_report.setText(resources.getString(R.string.report));
        }
        if (lang.equals("Russian")){
            context = LocaleHelper.setLocale(getContext(), "ru");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_fav.setText(resources.getString(R.string.favorites));
            text_setting.setText(resources.getString(R.string.settings));
            text_help.setText(resources.getString(R.string.help));
            text_term.setText(resources.getString(R.string.policy_terms));
            text_rate.setText(resources.getString(R.string.rate_us));
            text_report.setText(resources.getString(R.string.report));
        }
        if (lang.equals("Spanish")){
            context = LocaleHelper.setLocale(getContext(), "es");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_fav.setText(resources.getString(R.string.favorites));
            text_setting.setText(resources.getString(R.string.settings));
            text_help.setText(resources.getString(R.string.help));
            text_term.setText(resources.getString(R.string.policy_terms));
            text_rate.setText(resources.getString(R.string.rate_us));
            text_report.setText(resources.getString(R.string.report));
        }
        if (lang.equals("Urdu")){
            context = LocaleHelper.setLocale(getContext(), "ur");
            resources = context.getResources();
            title_main.setText(resources.getString(R.string.russia_tv_radio));
            text_fav.setText(resources.getString(R.string.favorites));
            text_setting.setText(resources.getString(R.string.settings));
            text_help.setText(resources.getString(R.string.help));
            text_term.setText(resources.getString(R.string.policy_terms));
            text_rate.setText(resources.getString(R.string.rate_us));
            text_report.setText(resources.getString(R.string.report));
        }
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.activity_bottom_sheet_dialog, container, false);
        fav = v.findViewById(R.id.card_fav);
        settings = v.findViewById(R.id.card_setting);
        help = v.findViewById(R.id.card_help);
        feed = v.findViewById(R.id.card_feedback);
        rate = v.findViewById(R.id.card_rate);
        report = v.findViewById(R.id.card_report);

        title_main = v.findViewById(R.id.title_main);
        title_lang = v.findViewById(R.id.title_lang);
        text_fav = v.findViewById(R.id.text_fav);
        text_setting = v.findViewById(R.id.text_setting);
        text_help = v.findViewById(R.id.text_help);
        text_term = v.findViewById(R.id.text_term);
        text_rate = v.findViewById(R.id.text_rate);
        text_report = v.findViewById(R.id.text_report);

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , Favorities.class);
                startActivity(intent);
                Animatoo.animateCard(getContext());
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext() , Settings_Activity.class);
                startActivity(intent);
                Animatoo.animateCard(getContext());
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com"));
                startActivity(browserIntent);
            }
        });


        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://apps.apploadyou.net/application/privacypolicy?id=63457aa02704e"));
                startActivity(browserIntent);
            }
        });

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=$packageName")));
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:alfidasaldana@gmail.com")));
            }
        });
        return v;
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        text1_1 = sharedPreferences.getString(TEXT1_1 , "English");
        title_lang.setText(text1_1);
    }
}