package com.example.jcalendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class aim extends Activity {

    Button btnBack, btnCal;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aim);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });

    }
}
