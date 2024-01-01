package com.example.assignment2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import android.content.SharedPreferences;
import android.widget.Toast;

public class prayertime extends AppCompatActivity {

    private EditText citytxt, countrytxt;
    private TextView resultTextView;
    private RequestQueue requestQueue; // Volley request queue system work Request if more in real data the volly Organizing work
    private SharedPreferences Pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayertime);

        citytxt = findViewById(R.id.scityText);
        countrytxt = findViewById(R.id.scountryText);
        resultTextView = findViewById(R.id.sresultTextView);
        Pref = getPreferences(Context.MODE_PRIVATE);
        Button submitButton = findViewById(R.id.ssubmitBtn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(prayertime.this, "Wait five seconds", Toast.LENGTH_SHORT).show();
                getPrayerTimes();
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        String savedText = Pref.getString("text", "");
        resultTextView.setText(savedText);
    }

    private void getPrayerTimes() {
        String city = citytxt.getText().toString();
        String country = countrytxt.getText().toString();
        String url = "https://api.aladhan.com/v1/timingsByCity?city=" + city + "&country=" + country + "&method=2";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parsePrayerTimes(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null) {
                    resultTextView.setText("Error");
                } else {
                    resultTextView.setText("Error");
                }
            }
        });

        requestQueue.add(stringRequest);
    }

    private void parsePrayerTimes(String response) {
        try {
            //
            // 20240101075549
            // https://api.aladhan.com/v1/timingsByCity/31-12-2023?
            JSONObject jsonObject = new JSONObject(response);
            JSONObject data = jsonObject.getJSONObject("data");
            //JSONObject date = jsonObject.getJSONObject("date");
            JSONObject timings = data.getJSONObject("timings");
            JSONObject date = data.getJSONObject("date");
            JSONObject hijri = date.getJSONObject("hijri");
            JSONObject weekday = hijri.getJSONObject("weekday");
            String enday = weekday.getString("ar");
            String datehijri = hijri.getString("date");

            String readable = date.getString("readable");
            String fajr = timings.getString("Fajr");

            String dhuhr = timings.getString("Dhuhr");
            String asr = timings.getString("Asr");
            String maghrib = timings.getString("Maghrib");
            String isha = timings.getString("Isha");
            String Sunrise = timings.getString("Sunrise");

            String prayerTimes ="city: "+citytxt.getText()+" .and country: "+countrytxt.getText()+"\nreadable: "+readable+ "\ndate hijri: "+datehijri+"\nar day: "+enday+
                    "\n-------\nFajr: " + fajr +"\nSunrise: "+Sunrise+ "\nDhuhr: " + dhuhr + "\nAsr: " + asr + "\nMaghrib: " + maghrib + "\nIsha: " + isha;
            resultTextView.setText(prayerTimes);
        } catch (Exception e) {
            resultTextView.setText("Error in the call JSON");
        }
    }
/**
 * If the user does Home in the application, the program saves the information that the user entered
 * */
    @Override
    protected void onPause() {
        super.onPause();
        String city = citytxt.getText().toString().trim();
        String country = countrytxt.getText().toString().trim();

        SharedPreferences.Editor editor = Pref.edit();
        //editor.putString("text",city);
        //editor.putString("text",country);
        editor.putString("text", resultTextView.getText().toString());
        editor.apply();
    }

/**
 *If the user exits the application completely, the data will be deleted
 *  */
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = Pref.edit();
        editor.remove("text");
        editor.apply();
    }
    /***
     * If the user goes back in the application, the program saves the information that the user entered
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        String city = citytxt.getText().toString().trim();
        String country = countrytxt.getText().toString().trim();

        SharedPreferences.Editor editor = Pref.edit();
        //editor.putString("text",city);
        //editor.putString("text",country);
        editor.putString("text", resultTextView.getText().toString());
        editor.apply();
    }

}
