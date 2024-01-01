package com.example.assignment2;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ViewDataAccountActivity extends AppCompatActivity {
//class activity view data account
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data_account);
        //dif
        ImageView img = findViewById(R.id.image);
        TextView name = findViewById(R.id.textstringname);
        TextView email = findViewById(R.id.textstringEmail);
        //SharedPreferences view info
        /**
         * You can use this option if you want multiple files to cooperate and be identified by name, which can be specified by the first parameter. You can call this number from any context in the country
         * */
        SharedPreferences preferences = getSharedPreferences("Prefs_useraccount", MODE_PRIVATE);
        /**
         * Retrieves the default shared preference file that belongs to the activity
         * */
        String stsusername = preferences.getString("username", "us");
        String stsemaill = preferences.getString("email", "");
        String stsimageUri = preferences.getString("img", "");
        //set in the data
        name.setText(stsusername);//name
        email.setText(stsemaill);//email
        if (!stsimageUri.equals("")) {//imagess
            img.setImageURI(Uri.parse(stsimageUri));
        }
    }
}
