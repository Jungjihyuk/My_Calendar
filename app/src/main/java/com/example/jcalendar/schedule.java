package com.example.jcalendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class schedule extends Activity {

    Button btnBack2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        // Back to aim
        btnBack2 = (Button) findViewById(R.id.btnBack2);
        btnBack2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
    }
}
