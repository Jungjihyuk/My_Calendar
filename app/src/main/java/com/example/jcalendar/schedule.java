package com.example.jcalendar;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TabHost;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class schedule extends TabActivity{

    Button btnBack2, btnCal2;
    CalendarView calView;
    CheckBox first, second, third, fourth;
    LinearLayout first_view, second_view, third_view, fourth_view;

    TextView myDate, myDate2, myDate3;

    //To do list
    EditText editText;
    ListView listView;

    DBHelper dbHelper;
    SQLiteDatabase db = null;
    Cursor cursor;
    ArrayAdapter adapter;

    LinearLayout bottom_l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabCal = tabHost.newTabSpec("Calendar").setIndicator("달력");
        tabCal.setContent(R.id.tabCal);
        tabHost.addTab(tabCal);

        TabHost.TabSpec tabTodo = tabHost.newTabSpec("Todo").setIndicator("오늘 할 일");
        tabTodo.setContent(R.id.tabTodo);
        tabHost.addTab(tabTodo);

        tabHost.setCurrentTab(0);

        bottom_l = (LinearLayout) findViewById(R.id.bottom_l);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if(s.equals("Calendar")){
                    bottom_l.setVisibility(View.INVISIBLE);
                }else{
                    bottom_l.setVisibility(View.VISIBLE);
                }
            }
        });

        myDate = (TextView) findViewById(R.id.myDate);
        myDate2 = (TextView) findViewById(R.id.myDate2);
        myDate3 = (TextView) findViewById(R.id.myDate3);
        Intent intent = getIntent();
        String name = intent.getExtras().getString("aim");
        String name2 = intent.getExtras().getString("aim2");
        String name3 = intent.getExtras().getString("aim3");
        myDate.setText(name);
        myDate2.setText(name2);
        myDate3.setText(name3);


        //Todo list
        editText = findViewById(R.id.edt1);
        listView = findViewById(R.id.lv1);


        dbHelper = new DBHelper(this, 4);
        db = dbHelper.getWritableDatabase();

        // button
        // Back to aim
        btnBack2 = (Button) findViewById(R.id.btnBack2);
        btnBack2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });


        // Another Calendar
//        btnCal2 = (Button) findViewById(R.id.btnCal2);
//        btnCal2.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Intent intent = new Intent(getApplicationContext(), schedule2.class);
//                startActivity(intent);
//            }
//        });

        // 달력 날짜 클릭시 일정 등록하기
//        calView = (CalendarView) findViewById(R.id.calView);
//        calView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        // Todo list
//        first = (CheckBox) findViewById(R.id.first);
//        first_view = (LinearLayout) findViewById(R.id.first_view);
//        first.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){
//                    first_view.setVisibility(View.VISIBLE);
//                }else{
//                    first_view.setVisibility(View.INVISIBLE);
//                }
//            }
//        });
//
//        second = (CheckBox) findViewById(R.id.second);
//        second_view = (LinearLayout) findViewById(R.id.second_view);
//        second.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){
//                    second_view.setVisibility(View.VISIBLE);
//                }else{
//                    second_view.setVisibility(View.INVISIBLE);
//                }
//            }
//        });
//
//        third = (CheckBox) findViewById(R.id.third);
//        third_view = (LinearLayout) findViewById(R.id.third_view);
//        third.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){
//                    third_view.setVisibility(View.VISIBLE);
//                }else{
//                    third_view.setVisibility(View.INVISIBLE);
//                }
//            }
//        });
//
//        fourth = (CheckBox) findViewById(R.id.fourth);
//        fourth_view = (LinearLayout) findViewById(R.id.fourth_view);
//        fourth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){
//                    fourth_view.setVisibility(View.VISIBLE);
//                }else{
//                    fourth_view.setVisibility(View.INVISIBLE);
//                }
//            }
//        });
    }
    public void listUpdate(View v) {
        cursor = db.rawQuery("SELECT * FROM tableName", null);
        startManagingCursor(cursor);    //엑티비티의 생명주기와 커서의 생명주기를 같게 한다.

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1);


        while (cursor.moveToNext()) {
            adapter.add(cursor.getString(0));
        }

        /*
        cursor.moveToFirst();
        cursor.moveToPrevious();
        cursor.moveToPosition(2);
        */

        listView.setAdapter(adapter);
    }

    public void insert(View v) {
        String contents = editText.getText().toString();
        db.execSQL("INSERT INTO tableName VALUES ('" + contents + "');");

        Toast.makeText(getApplicationContext(), "추가 성공", Toast.LENGTH_SHORT).show();

        editText.setText("");
        listUpdate(v);
    }

    public void delete(View v) {
        String contents = editText.getText().toString();
        db.execSQL("DELETE FROM tableName WHERE contents = '" + contents + "';");
        listUpdate(v);
    }
}
