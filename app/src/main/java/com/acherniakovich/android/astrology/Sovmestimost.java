package com.acherniakovich.android.astrology;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.ArrayList;

public class Sovmestimost extends AppCompatActivity {

    private Button btn,dateOfBirdthParthner;
    private int DIALOG_DATE = 1;

    int myYear = 2017;
    int myMonth = 1;
    int myDay = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sovmestimost);
        init();
    }

    private void init() {
        btn = (Button)findViewById(R.id.button_1);
        dateOfBirdthParthner = (Button)findViewById(R.id.dateOfBirdthParthner);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_1:
                Intent intent = new Intent(Sovmestimost.this,SovmResult.class);
                startActivity(intent);
                break;
            case R.id.dateOfBirdthParthner:
                showDialog(DIALOG_DATE);
                break;
            default:
                break;
        }
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myYear = year;
            myMonth = monthOfYear;
            myDay = dayOfMonth;
            dateOfBirdthParthner.setText(myDay+"."+myMonth+1+"."+myYear);
        }
    };

}
