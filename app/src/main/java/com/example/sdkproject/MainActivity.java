package com.example.sdkproject;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.analyticslibrary.AnalyticsSDK;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "UUIDTest";
    private static final String PREFS_NAME = "AnalyticsPrefs";
    private static final String KEY_USER_ID = "user_id";



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            // Find the button in the layout
            Button showRatingDialogButton = findViewById(R.id.button_rating);
            Button button_create_user = findViewById(R.id.button_create_user);
            // Set an OnClickListener for the button

            //register user
            showRatingDialogButton.setOnClickListener(v -> AnalyticsSDK.showRatingDialog(this));
           // button_create_user.setOnClickListener(v -> AnalyticsSDK.registerUser("userid2"  ,"2024-12-24T18:57:09.719Z",
            //        "2024-12-24T18:57:09.719Z"));

            //app crashes check the log crash is sent
            Button buttonCauseCrash = findViewById(R.id.button_cause_crash);
            buttonCauseCrash.setOnClickListener(v -> {
                // Simulate an unhandled exception to trigger crash logging
                throw new RuntimeException("bdika");
            });


            //send log with different log type
            Button button_send_log = findViewById(R.id.button_send_log);
            button_send_log.setOnClickListener(v -> {
                AnalyticsSDK.createLog("Other" , "jajaja");
            });


        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String storedUuid = prefs.getString(KEY_USER_ID, null);

        if (storedUuid == null) {
            Log.d(TAG, "UUID not found. Generating a new one...");
            storedUuid = java.util.UUID.randomUUID().toString();
            prefs.edit().putString(KEY_USER_ID, storedUuid).apply();
        }

        // Log the UUID to check if it was saved successfully
        Log.d(TAG, "Stored UUID: " + storedUuid);

        }
}