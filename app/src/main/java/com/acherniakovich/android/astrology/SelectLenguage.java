package com.acherniakovich.android.astrology;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class SelectLenguage extends Activity {

    private Button eng;
    private Button rus;

    private String resultLeng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_lenguage);

        init();
    }

    private void init() {
        eng = (Button)findViewById(R.id.eng);
        rus = (Button)findViewById(R.id.rus);

        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultLeng = "english";
                license();
            }
        });

        rus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultLeng = "russian";
                license();
            }
        });
    }

    private void license(){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Лицензионное соглашение")
                .setMessage("Текст лицензионного соглашения. Принимаете ли Вы наши условия?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        if (resultLeng.equals("english")){
                            Intent intent = new Intent();
                            intent.putExtra("lenguage","english");
                            setResult(RESULT_OK,intent);
                            finish();
                        }else if (resultLeng.equals("russian")){
                            Intent intent = new Intent();
                            intent.putExtra("lenguage","russian");
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        Intent intent = new Intent();

                        setResult(0,intent);
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
