package com.example.assignment2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {
    private Button butLogout;
    private Button time_t;
    private SharedPreferences prefe;
    private TextView textViewWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        butLogout = findViewById(R.id.buttLogout);
        time_t = findViewById(R.id.timee);
        textViewWelcome = findViewById(R.id.stsViewWelcome);
        /**
         * You can use this option if you want multiple files to cooperate and be identified by name, which can be specified by the first parameter. You can call this number from any context in the country
         * Retrieves the default shared preference file that belongs to the activity
         * */
        prefe = getSharedPreferences("Prefs_useraccount", MODE_PRIVATE);
        String username = prefe.getString("username", "us");
        textViewWelcome.setText("Hello " + username + " ..........");

        butLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //login on SharedPreferences
                SharedPreferences.Editor editor = prefe.edit();
                editor.putBoolean("isLogIn", false);//Disable SharedPreferences that feature isLogIn
                editor.apply();
                // go to MainActivity
                Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //go to view prayer time activity
        time_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, prayertime.class);
                startActivity(intent);
            }
        });
      //go to view data account
        Button butViewinfo = findViewById(R.id.Viewinfo);
        butViewinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, ViewDataAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
