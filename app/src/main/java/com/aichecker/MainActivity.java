package com.aichecker;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.Python;
import com.chaquo.python.PyObject;

public class MainActivity extends AppCompatActivity {

    private Button selectImageBtn, analyzeBtn;
    private ImageView imageView;
    private TextView resultTextView;
    private Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        resultTextView = findViewById(R.id.resultTextView);
        selectImageBtn = findViewById(R.id.selectImageBtn);
        analyzeBtn = findViewById(R.id.analyzeBtn);

        selectImageBtn.setOnClickListener(v -> openGallery());
        analyzeBtn.setOnClickListener(v -> analyzeImage());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    private void analyzeImage() {
        if (imageUri != null) {
            Python py = Python.getInstance();
            PyObject pyObj = py.getModule("main");
            PyObject obj = pyObj.callAttr("analyze_image", imageUri.getPath());
            resultTextView.setText(obj.toString());
        } else {
            Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            resultTextView.setText("");
        }
    }
}
