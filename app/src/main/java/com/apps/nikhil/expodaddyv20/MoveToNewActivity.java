package com.apps.nikhil.expodaddyv20;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import com.apps.nikhil.expodaddyv20.Activities.LoginActivity;
import com.apps.nikhil.expodaddyv20.Activities.MainActivity;

/**
 * Created by Nikhil on 30-08-2015.
 */
public class MoveToNewActivity {

    public void moveToHomeScreen(Context context) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(homeIntent);
    }

    public void moveToMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);
    }

    public void moveToLoginActivity(Context context) {
        Intent intent = new Intent(context,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);
    }

    public void moveToGPSSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);
    }
}

