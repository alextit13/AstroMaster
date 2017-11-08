package com.acherniakovich.android.astrology;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Prognoz extends Activity {

    private Spinner dateOfPrognoz;
    private ArrayList <Integer> list;
    private Button buttonMap,dateBirdthParthner,sp_1;
    private int DIALOG_DATE = 1;
    private int DIALOG_DATE_DAY = 2;

    int myYear = 2017;
    int myMonth = 1;
    int myDay = 1;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prognoz);

        init();

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        // инициализация
        tabHost.setup();

        TabHost.TabSpec tabSpec;

        // создаем вкладку и указываем тег
        tabSpec = tabHost.newTabSpec("tag1");
        // название вкладки
        tabSpec.setIndicator("Прогноз на день");
        // указываем id компонента из FrameLayout, он и станет содержимым
        tabSpec.setContent(R.id.tvTab1);
        // добавляем в корневой элемент

        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        // указываем название и картинку
        // в нашем случае вместо картинки идет xml-файл,
        // который определяет картинку по состоянию вкладки
        tabSpec.setIndicator("Прогноз на год");
        tabSpec.setContent(R.id.tvTab2);
        tabHost.addTab(tabSpec);

        // первая вкладка будет выбрана по умолчанию
        tabHost.setCurrentTabByTag("tag1");

        TextView tv1 = (TextView)tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        TextView tv2 = (TextView)tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        tv1.setTextColor(Color.parseColor("#FFFFFF"));
        tv2.setTextColor(Color.parseColor("#FFFFFF"));

        // обработчик переключения вкладок
        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                //Toast.makeText(getBaseContext(), "tabId = " + tabId, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        sp_1 = (Button)findViewById(R.id.et_1);
        sp_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_DATE_DAY);
            }
        });
        buttonMap = (Button)findViewById(R.id.buttonMap);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prognoz.this,MapsActivity.class);
                startActivityForResult(intent,1);
            }
        });
        dateOfPrognoz = (Spinner) findViewById(R.id.dateOfPrognoz);
        ArrayList <String>listOfData = new ArrayList<>();
        for (int i = 2017; i>=1900;i--){
            listOfData.add(i+"");
        }
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(Prognoz.this,android.R.layout.simple_list_item_1,listOfData);
        dateOfPrognoz.setAdapter(adapter);

        dateBirdthParthner = (Button)findViewById(R.id.dateBirdthParthner);
        dateBirdthParthner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_DATE);
            }
        });

    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }else if (id == DIALOG_DATE_DAY){
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack_day, myYear, myMonth, myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            dateBirdthParthner.setText(myDay+"."+myMonth+1+"."+myYear);
        }
    };

    DatePickerDialog.OnDateSetListener myCallBack_day = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear + 1;
            myDay = dayOfMonth;
            sp_1.setText(myDay+"."+myMonth+"."+myYear);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data==null){
            return;
        }
        double [] listCoordinates;
        listCoordinates = data.getDoubleArrayExtra("array");

        double latitude = listCoordinates[0];
        double longitude = listCoordinates[1];

        NumberFormat formatter = new DecimalFormat("#0.00");
        String x = formatter.format(latitude);
        String y = formatter.format(longitude);

        buttonMap.setText("Координаты рождения = : " + x+" ; "+y);
    }
}