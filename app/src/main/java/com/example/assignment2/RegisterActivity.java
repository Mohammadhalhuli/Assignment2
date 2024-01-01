package com.example.assignment2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class RegisterActivity extends AppCompatActivity {
    private EditText usernamestring;
    private Button creataccount;
    private EditText txt_email;
    private SharedPreferences prefs;
    private EditText passwordstring;
    SharedPreferences.Editor editor;
    public static final String DATA = "DATA";
    private Uri img_path;
    private TextView txtResults;
    Button but_select_img;
    private ArrayList<users> users_list;
    private users users;
    private TextView textViewSign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        but_select_img = findViewById(R.id.addimage_reg);

        but_select_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_img_selector();
            }
        });
        txt_email = (EditText) findViewById(R.id.textemail);
        usernamestring = findViewById(R.id.editname);
        passwordstring = findViewById(R.id.editPass);
        creataccount = findViewById(R.id.register);
        loaddata();//save data in drive
        creataccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_username = usernamestring.getText().toString().trim();
                String str_password = passwordstring.getText().toString().trim();
                String str_email = txt_email.getText().toString().trim();
                Log.d("Register", String.valueOf(validateation(str_email,str_username, str_password)));
                if (validateation(str_email,str_username, str_password)) {
                    String img = String.valueOf(img_path);
                    saveUserData(str_email,str_username, str_password,img);
                    Intent intent = new Intent(RegisterActivity.this, HomePageActivity.class);
                    startActivity(intent);
                }
               /* textViewSign_up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });*/
            }

        });
    }
    //sharedprefs and Gson
    //https://www.youtube.com/watch?v=0IdzL0iuRSQ
    //save data with array list
    //https://www.youtube.com/watch?v=xjOyvwRinK8
    private void open_img_selector() {
        ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        img_path = data.getData();
        //Log.d("Register",img_path.toString());
    }

    //click user back and home
    //use live lifecycle
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Register", "onPause");
        String username = usernamestring.getText().toString().trim();
        Log.d("Register", username);
        String password = passwordstring.getText().toString().trim();
        Log.d("Register", password);
        String email = txt_email.getText().toString().trim();
        Log.d("Register", email);
        String img = String.valueOf(img_path);
        saveUserData(email,username, password,img);
        Log.d("Register", "the save data in onPause");
    }

    //click user back and home
    //use live lifecycle
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Register", "onStop ");
    }
    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
    private void loaddata() {
        SharedPreferences preferences = getSharedPreferences("Prefs_useraccount", MODE_PRIVATE);
        //Gson gson = new Gson();
        /*prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        String user = gson.toJson(new TypeToken<ArrayList<users>>(){}.getType());

        editor.putString(DATA, tit);
        editor.commit();
        String str = prefs.getString(DATA, "");
        if(str.equals("")) {
            tilesList = new ArrayList<>();
        }*/
        String str_email = preferences.getString("email", "");
        Log.d("Register", str_email);
        String str_username = preferences.getString("username", "");
        Log.d("Register", str_username);
        String str_password = preferences.getString("password", "");
        Log.d("Register", str_password);
        String str_url_img=preferences.getString("img", "");
        Log.d("Register", str_url_img);
        txt_email.setText(str_email);
        usernamestring.setText(str_username);
        passwordstring.setText(str_password);

        if (!String.valueOf(img_path).isEmpty()) {
            img_path = Uri.parse(String.valueOf(img_path));
        }
//        String json_data = prefs.getString("users", null);
        //System.out.println(json_data);
        //if (users_list == null) {
        //arry_L is Empty
        //users_list = new ArrayList<>();
        //Log.d("halholaym_add",tiles_list.toString());
        //editor = prefs.edit();
        //}
        Log.d("Register", "load onCreate");
    }

    private boolean validateation(String pEmail,String pusername, String ppassword) {

        //System.out.println(pemail);



        if (pEmail.isEmpty()) {
            txt_email.requestFocus();
            Log.d("RegisterActivity", "Email.isEmpty");
            return false;
        }
        //System.out.println(pusername);
        if (pusername.isEmpty()) {
            usernamestring.requestFocus();
            Log.d("RegisterActivity", "username.isEmpty");
            return false;
        }
        //System.out.println(ppassword);
        if (ppassword.isEmpty()) {
            passwordstring.requestFocus();
            Log.d("RegisterActivity", "password.isEmpty");
            return false;
        }
        //System.out.println(pimg);
        return true;
    }
    private void saveUserData(String pemail ,String pusername, String ppassword,String pimg) {
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
        //Gson gson =new Gson();
        //String st_Data=gson.toJson(users_list);
        SharedPreferences pref = getSharedPreferences("Prefs_useraccount", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        //String info=SharedPreferences.getString("useraccount",null);
        //users user=gson.fromJson(info,users.class);
        editor.putString("email", pemail);
        editor.putString("username", pusername);
        editor.putString("password", ppassword);
        editor.putString("img", pimg);
        editor.apply();
        Log.d("Register", "Data saved in SharedPreferences");
    }
}
