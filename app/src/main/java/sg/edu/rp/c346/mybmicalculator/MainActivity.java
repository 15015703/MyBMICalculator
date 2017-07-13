package sg.edu.rp.c346.mybmicalculator;

import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText weight;
    EditText height;
    Button cc;
    Button rd;
    EditText et1;
    EditText et2;
    String Datetime;
    Float BMI;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weight = (EditText) findViewById(R.id.weight);
        height = (EditText) findViewById(R.id.height);
        cc = (Button)findViewById(R.id.cc);
        rd = (Button)findViewById(R.id.rd);
        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);
        Calendar now = Calendar.getInstance(); //Create a Calendar object with current date/time
        final String datetime = now.get(Calendar.DAY_OF_MONTH)+ "/"+
                (now.get(Calendar.MONTH)+1) + "/" +
                now.get(Calendar.YEAR) + " " +
                now.get(Calendar.HOUR_OF_DAY)+":"+
                now.get(Calendar.MINUTE);
        cc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int w =  Integer.parseInt(weight.getText().toString());
                float h = Float.parseFloat(height.getText().toString());
                float bmi = w / (h*h);
                et1.setText(Float.toString(bmi));
                Datetime = datetime;
                et2.setText(datetime);
                weight.setText(null);
                height.setText(null);
                BMI = bmi;
            }
        });
        rd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1.setText(null);
                et2.setText(null);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();




        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putFloat("bmi",BMI);
        prefEdit.putString("now",Datetime);
        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        float bmi = prefs.getFloat("bmi", 0);
        String now= prefs.getString("now","");

        et1.setText(Float.toString(bmi));
        et2.setText(now);
    }

}
