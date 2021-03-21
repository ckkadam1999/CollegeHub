package in.co.electronicshub.recursion.collegehub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import in.co.electronicshub.recursion.collegehub.R;
import in.co.electronicshub.recursion.collegehub.utils.SessionManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SessionManager sessionManager = new SessionManager(this);
        Class activity;
        if(sessionManager.isLoggedIn()){
            activity = MainActivity.class;
        }else{
            activity = LoginActivity.class;
        }
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        finish();


    }
}