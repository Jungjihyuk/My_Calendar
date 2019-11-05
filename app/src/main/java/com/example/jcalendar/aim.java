package com.example.jcalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class aim extends Activity {

    Button btnBack, btnCal;
    ImageButton check;
    EditText one, two, three;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aim);

//        btnBack = (Button) findViewById(R.id.btnBack);
//        btnBack.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                finish();
//            }
//        });

        // Go to calendar
//        btnCal = (Button) findViewById(R.id.btnCal);
//        btnCal.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Intent intent = new Intent(getApplicationContext(), schedule.class);
//                startActivity(intent);
//            }
//        });

        check = (ImageButton) findViewById(R.id.check);
        one = (EditText) findViewById(R.id.one);
        two = (EditText) findViewById(R.id.two);
        three = (EditText) findViewById(R.id.three);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), schedule.class);
                String aim = one.getText().toString();
                String aim2 = two.getText().toString();
                String aim3 = three.getText().toString();
                intent.putExtra("aim",aim);
                intent.putExtra("aim2",aim2);
                intent.putExtra("aim3",aim3);
                startActivity(intent);
            }
        });
    }
}
