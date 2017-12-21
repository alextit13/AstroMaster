package com.acherniakovich.android.astrology.classesCalculationPrognoz;

import android.content.Context;
import android.util.Log;

import com.acherniakovich.android.astrology.DatabaseGetter.Tables;
import com.acherniakovich.android.astrology.MainActivity;

import io.realm.Realm;

public class CalculationPrognozDay{

    private int year;
    private int month;
    private int day;
    private Context context;

    private Realm mRealm;


    private void init() {
        mRealm = Realm.getInstance(context);
    }

    public String getPrognozDay(int myYear, int myMonth, int myDay, Context c) {
        context = c;

        init();

        year = myYear;
        month = myMonth;
        day = myDay;

        String text_date = year + "." + month + "." + day; // дата в формате 12.8.2017


        testCreateDatabase(text_date);

        //Log.d(MainActivity.LOG_TAG,"y = " + year + " , m = " + month + " , d = " + day);

        return "";
    }

    private void testCreateDatabase(String date) {
        mRealm.beginTransaction();
        Tables table = mRealm.createObject(Tables.class);
        table.setDate(date);
        mRealm.commitTransaction();
        Log.d(MainActivity.LOG_TAG,"База содана");
    }
}
