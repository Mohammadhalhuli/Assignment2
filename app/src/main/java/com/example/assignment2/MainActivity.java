package com.example.assignment2;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button buttRegister;
    private Button buttLogin;
    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * You can use this option if you want multiple files to cooperate and be identified by name, which can be specified by the first parameter. You can call this number from any context in the country
         * Retrieves the default shared preference file that belongs to the activity
         * */
        pref = getSharedPreferences("Prefs_useraccount", MODE_PRIVATE);
        boolean islogintrue = pref.getBoolean("isLogIn", false);//Disable SharedPreferences that feature isLogIn

        if (islogintrue) {// If logged in, the shared preferences that are distinguished by isLogIn
            //go to home page
            Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
            startActivity(intent);
        } else {

            setContentView(R.layout.activity_main);


            buttRegister = findViewById(R.id.butRegister);
            buttLogin = findViewById(R.id.butLogin);


            buttRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //go to Register page
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });


            buttLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //go to login page
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
