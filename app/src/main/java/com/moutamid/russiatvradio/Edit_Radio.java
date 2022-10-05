package com.moutamid.russiatvradio;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class Edit_Radio extends AppCompatActivity {

    EditText radio_name , radio_link ,radio_id;
    Button radio_btn_add;
    ImageView raido_img;

    private static final int IMAGE_REQUEST = 1;
    Uri uri;
    ProgressDialog pd;
    String imageURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_radio);

        pd = new ProgressDialog(this);
        pd.setTitle("Wait...");
        pd.setMessage("Test Uploading...");

        radio_name = findViewById(R.id.radio_name2);
        radio_id = findViewById(R.id.radio_id2);
        radio_link = findViewById(R.id.radio_link2);
        raido_img = findViewById(R.id.radio_img_add2);
        radio_btn_add = findViewById(R.id.radio_btn_add2);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            radio_name.setText(bundle.getString("name"));
            radio_link.setText(bundle.getString("link"));
            radio_id.setText(bundle.getString("id"));
        }

        raido_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });

        // 3rd btn for submit all data using firebase
        radio_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = radio_id.getText().toString().trim();
                String name = radio_name.getText().toString().trim();
                String link = radio_link.getText().toString().trim();

                if (id.isEmpty()){
                    Toast.makeText(Edit_Radio.this, "Enter Unique Radio ID", Toast.LENGTH_SHORT).show();
                }
                else if (name.isEmpty()){
                    Toast.makeText(Edit_Radio.this, "Enter Radio Name", Toast.LENGTH_SHORT).show();
                }
                else if (link.isEmpty()){
                    Toast.makeText(Edit_Radio.this, "Enter Radio Link", Toast.LENGTH_SHORT).show();
                }
                else {
                    uploadImage();
                }
            }
        });
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            uri = data.getData();
            raido_img.setImageURI(uri);
        }
        else {
            Toast.makeText(this, "No Image is Selected", Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadImage(){
        pd.show();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Radio_Images").child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri uriImage = uriTask.getResult();
                imageURL = uriImage.toString();
                uploadTest();
            }
        });
    }

    private void uploadTest(){
        pd.show();
        String check = radio_id.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Radio_app3").child(check);
        HashMap<String, String> hashMap = new HashMap<>();
        String id = radio_id.getText().toString().trim();
        String name = radio_name.getText().toString();
        String link = radio_link.getText().toString();

        hashMap.put("id", id);
        hashMap.put("name", name);
        hashMap.put("link", link);
        hashMap.put("image1", imageURL);

        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    pd.dismiss();
                    Toast.makeText(Edit_Radio.this, "Test Uploaded", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Edit_Radio.this, "Failed to Upload..." , Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }
}