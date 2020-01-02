package com.example.blockchn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String result="";

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mine mine=new Mine();
                mine.execute();
            }
        });

    }
    public native String stringFromJNI();

    private class Mine extends AsyncTask<String, String,String> {
        @Override
        protected void onPreExecute() {
            Context context = getApplicationContext();
            CharSequence text = "Mining! Do not Panic. App may appear frozen";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            Button btn = findViewById(R.id.button);
            btn.setClickable(false);
        }

        @Override
        protected void onProgressUpdate(String... text)
        {
            TextView tv = findViewById(R.id.sample_text);
            tv.setText(text[0]);
        }

        @Override
        protected String doInBackground(String... strings) {
            publishProgress("Mining under process*");
            result="";
            result=stringFromJNI();
            return result;
        }

        @Override
        protected void onPostExecute(String ans) {
            TextView tv = findViewById(R.id.sample_text);
            tv.setText(ans);
            Button btn = findViewById(R.id.button);
            btn.setClickable(true);
        }
    }
}

