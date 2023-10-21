package com.example.smocheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;




public class MainActivity extends AppCompatActivity {
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private ImageView picture;
    private Intent intent1,intent2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //打开相册
        Button chooseFromAlbum = findViewById(R.id.choose_from_album);
        chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CHOOSE_PHOTO);
                } else {
                    openAlbum();//打开album的界面
                }
            }
        });


        //拍照
        Button takePhoto = findViewById(R.id.take_photo);
        picture = findViewById(R.id.picture);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 动态申请权限
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO);
                } else {
                    // 启动相机程序
                    startCamera();
                }
            }
        });
        intent1=new Intent(this,Albums.class);//创建跳转到Albums显示的窗口的Intent
        intent2=new Intent(this,Camera.class);//创建跳转到Camera显示的窗口的Intent

    }

    private void openAlbum() {
        startActivity(intent1);//进入album的窗口界面
    }
    private void startCamera() {
        startActivity(intent2);//进入camera的窗口界面
    }

}