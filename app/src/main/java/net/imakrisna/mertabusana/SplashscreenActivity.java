package net.imakrisna.mertabusana;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import net.imakrisna.mertabusana.Utilities.MySharedPreference;

/**
 * Created by Mustika on 28/12/2017.
 */

public class SplashscreenActivity extends AppCompatActivity{
    MySharedPreference sharedPreferences;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >=19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        sharedPreferences= new MySharedPreference(this);
        if (!sharedPreferences.getIsLogin()){
            intent = new Intent(this,LoginActivity.class);
        }else{
            intent= new Intent(this,MainActivity.class);
        }
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                        finish();
                    }
                },3000
        );
    }
}

