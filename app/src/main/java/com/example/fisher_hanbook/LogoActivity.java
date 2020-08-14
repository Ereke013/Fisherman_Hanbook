package com.example.fisher_hanbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class LogoActivity extends Activity {
    private Animation logoAnim,buttonLogoAnim ;
    private Button bAnim;

    private ImageView logoImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_activity);
        init();
        startMainActivity();
    }

    private void init()
    {
// Загружаем анимации в переменные
        logoAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.logo_anim);
        buttonLogoAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_anim);

        logoImage = findViewById(R.id.LogoView);
        bAnim = findViewById(R.id.buttonAnim);
//Запускаем анимацию
        logoImage.startAnimation(logoAnim);
        bAnim.startAnimation(buttonLogoAnim);

    }

    public void onClickStart(View view){
        Intent i = new Intent(LogoActivity.this,MainActivity.class);
        startActivity(i);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void startMainActivity(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(LogoActivity.this,MainActivity.class);
                startActivity(i);
            }
        }).start();
    }
}
