package com.example.assignment2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private EditText stringLoginUsername;
    private CheckBox check_BoxRememberMe;
    private EditText stringLoginPassword;
    private Button buttonLogin;
    private TextView textViewSign_up;
    SharedPreferences.Editor editor;
    public static final String DATA = "DATA";
    private Uri img_path;
    private TextView txtResults;
    Button but_select_img;
    private ArrayList<users> users_list;
    private users users;
    private SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        stringLoginUsername = findViewById(R.id.stsLoginUsername);
        stringLoginPassword = findViewById(R.id.stsLoginPassword);
        check_BoxRememberMe = findViewById(R.id.checkBoxRememberMe);
        //textViewSign_up=findViewById(R.id.Sign_up);
        buttonLogin = findViewById(R.id.buttonLogin);
        SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
        String savedUsername = prefs.getString("stringLoginUsername", "");
        stringLoginUsername.setText(savedUsername);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stusername = stringLoginUsername.getText().toString().trim();
                // Log.d("Login", stusername);
                String stpassword = stringLoginPassword.getText().toString().trim();
                //Log.d("Login", stpassword);
                //click user back and home

                if (Valedation(stusername, stpassword)) {
                    check(stusername, stpassword);
                }
                /*textViewSign_up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(intent);
                    }
                });*/
            }

        });
    }
    //click user back and home
    //use live lifecycle
    /*
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Register", "onPause");
        String username = stringLoginUsername.getText().toString().trim();
        Log.d("Login", username);
        String password = stringLoginPassword.getText().toString().trim();
        Log.d("Login", password);
        saveUserData(username, password);
        Log.d("Login", "the save data in onPause");
    }
    //click user back and home
    //use live lifecycle
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Register", "onStop ");
    }*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (stringLoginUsername != null) {
            prefs = getSharedPreferences("data", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("stringLoginUsername", stringLoginUsername.getText().toString());
            editor.apply();
        }

/*
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("stringLoginUsername", stringLoginUsername.getText().toString());
        editor.apply();*/
    }
    /**
     *If the user exits the application completely, the data will be deleted
     *  */
    @Override
    protected void onStop() {
        super.onStop();

    }
    protected void onPause() {
        super.onPause();
    }
    private void saveUserData(String pusername, String ppassword) {
        //System.out.println(pemail);
        //System.out.println(ppassword);
        //System.out.println(pimg);
        //System.out.println(pusername);
        //users newusers=new users(pemail,ppassword,pusername,ppassword);
        //users_list.add(newusers);
        // Update an existing tile SharedPreferences
        //'TilesPrefs' to work save name data
        //SharedPreferences.Editor editor = getSharedPreferences("Prefs", MODE_PRIVATE).edit();
        /*editor.putString(DATA, tit);
        editor.commit();*/
        Gson gson =new Gson();
        //String st_Data=gson.toJson(users_list);
        SharedPreferences pref = getSharedPreferences("Prefs_useraccount", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //String info=SharedPreferences.getString("Prefs_useraccount",null);
        //users user=gson.fromJson(info,users.class);

        editor.putString("username", pusername);
        editor.putString("password", ppassword);

        editor.apply();
        Log.d("Login", "Data saved in SharedPreferences");
    }
    private boolean Valedation(String pusername, String ppassword) {
        if (pusername.isEmpty()) {
            stringLoginUsername.requestFocus();
            Log.d("Login", "pusername.isEmpty");
            return false;
        }
        if (ppassword.isEmpty()) {
            stringLoginPassword.requestFocus();
            Log.d("Login", "pusername.isEmpty");
            return false;
        }
        return true;
    }
    private void check(String Stringusername, String Stringpassword) {
        SharedPreferences preferences = getSharedPreferences("Prefs_useraccount", MODE_PRIVATE);
        Gson gson = new Gson();
        /*prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        String user = gson.toJson(new TypeToken<ArrayList<users>>(){}.getType());

        editor.putString(DATA, tit);
        editor.commit();
        String str = prefs.getString(DATA, "");
        if(str.equals("")) {
            tilesList = new ArrayList<>();
        }*/
        String registeredUsername = preferences.getString("username", "");
        String registeredPassword = preferences.getString("password", "");
        //        String json_data = prefs.getString("users", null);
        //System.out.println(json_data);
        if (users_list == null) {
            //arry_L is Empty
            users_list = new ArrayList<>();
            //Log.d("halholaym_add",tiles_list.toString());
            //editor = prefs.edit();
        }
        if (Stringusername.equals(registeredUsername) &&
                Stringpassword.equals(registeredPassword)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isLogIn", true);
            //cheking in Preference
            if (check_BoxRememberMe.isChecked()) {
                //cheking in Preference
                editor.putString("savedUsername", Stringusername);
                editor.putString("savedPassword", Stringpassword);
            }
            editor.apply();


            //Done go to home page
            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
            startActivity(intent);


        } else {
            Toast.makeText(LoginActivity.this, "Error in usesname or password", Toast.LENGTH_SHORT).show();
        }
    }
}
