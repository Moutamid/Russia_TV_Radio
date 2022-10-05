package com.moutamid.russiatvradio;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

        test_spinner1 = findViewById(R.id.test_spinner1);
        test_spinner2 = findViewById(R.id.test_spinner2);
        test_spinner3 = findViewById(R.id.test_spinner3);
        test_spinner4 = findViewById(R.id.test_spinner4);
        test_spinner6 = findViewById(R.id.test_spinner6);

        loadData();
        updateData();

        String myCurrent_dateTime = SimpleDateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        text5.setText(myCurrent_dateTime);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
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
                        "Deutsch",
                        "Turkish",
                        "Russian",
                        "Spanish",
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
    }

    public void updateData() {
        text1.setText(text1_1);
        text2.setText(text1_2);
        text3.setText(text1_3);
        text4.setText(text1_4);
        text6.setText(text1_6);
    }
}