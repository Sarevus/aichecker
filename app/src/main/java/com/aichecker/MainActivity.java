package com.aichecker;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button selectImageBtn;
    private ImageView imageView;
    private Button analyzeBtn;
    private TextView resultTextView;

    private Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectImageBtn = findViewById(R.id.selectImageBtn);
        imageView = findViewById(R.id.imageView);
        analyzeBtn = findViewById(R.id.analyzeBtn);
        resultTextView = findViewById(R.id.resultTextView);

        selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        analyzeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null) {
                    // Передаем изображение в Python скрипт для анализа
                    // Получаем результаты и отображаем их
                    // Здесь предполагается вызов функции или метода, который отправит изображение в Python скрипт и получит результаты анализа
                    // В данном примере просто генерируем случайный текст
                    Random random = new Random();
                    String result = random.nextBoolean() ? "Text Result A" : "Text Result B";
                    resultTextView.setText(result);
                } else {
                    Toast.makeText(MainActivity.this, "Select an image first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            // Сбрасываем результат анализа, так как изображение обновилось
            resultTextView.setText("");
        }
    }
}
