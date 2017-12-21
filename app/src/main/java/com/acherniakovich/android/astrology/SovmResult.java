package com.acherniakovich.android.astrology;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.acherniakovich.android.astrology.classesCalculationPrognoz.CalculationPlanetLocation;
import com.acherniakovich.android.astrology.classesTexts.VariablesForStringResult;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class SovmResult extends AppCompatActivity {

    final String FILENAME = "2018";
    private String dateBirdthUser;

    int myYear = 2017;
    int myMonth = 1;
    int myDay = 1;

    private ArrayList <String> planets;
    private ArrayList <String> planetsLocationPeople;
    private ArrayList <String> planetsLocationParthner;

    private String t1; // дата рождения юзера
    private String t2; // дата рождения пользователя

    private String T = "12:00"; // время рождения по Гринвичу

    private SharedPreferences sPref;
    private People people;

    private TextView positive_texts,negative_texts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sovm_result);
        init();
        calculatePlanetLocation();
    }

    private void calculatePlanetLocation() {
        planets = new ArrayList<>();
        planets.add("sol");
        planets.add("lun");
        planets.add("mer");
        planets.add("ven");
        planets.add("mar");
        planets.add("yup");
        planets.add("sat");

        planetsLocationPeople = new ArrayList<>();
        planetsLocationParthner = new ArrayList<>();

        CalculationPlanetLocation CPL = new CalculationPlanetLocation();

        for (int i = 0; i<planets.size();i++){
            planetsLocationPeople.add(CPL.getPlanetLocation(planets.get(i),t1)); // сюда добавляются все положения всех планет для people
        }

        for (int i = 0; i<planets.size();i++){
            planetsLocationParthner.add(CPL.getPlanetLocation(planets.get(i),t2));// сюда добавляются все положения всех планет для партнера
        }



    }

    private void init() {
        Intent intent = getIntent();
        myYear = intent.getIntExtra("myYear",2017);
        myMonth = intent.getIntExtra("myMonth",1);
        myDay = intent.getIntExtra("myDay",1);

        t2 = createDatePartner(myYear,myMonth,myDay);

        positive_texts = (TextView)findViewById(R.id.positive_texts);
        negative_texts = (TextView)findViewById(R.id.negative_texts);

        positive_texts.setText(VariablesForStringResult.sol_m_0_sol_w);
        negative_texts.setText(VariablesForStringResult.lun_m_0_lun_w);

        people = readObjectFromFile(this,AddInformation.SAVED_TEXT);

    }

    private String createDatePartner(int year, int month, int day) {
        /**
         * тут принимаем и приводим в человеческий вид переменную t2 - дату рождения партнера
         * */
        return day+"/"+month+"/"+year;
    }

    public People readObjectFromFile(Context context, String filename) {

        /**
        * отсюда берем данные пользователя, которые он сохранил с начального экрана
        * */

        ObjectInputStream objectIn = null;
        People people = null;
        try {

            FileInputStream fileIn = context.getApplicationContext().openFileInput(filename);
            objectIn = new ObjectInputStream(fileIn);
            people = (People) objectIn.readObject();

            if (people!=null){
                t1 = people.getDateOfBirdth(); // 6/5/1991
            }

            //Log.d(MainActivity.LOG_TAG,"peopel = " + people.getDateOfBirdth());

        } catch (FileNotFoundException e) {
            // Do nothing
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException e) {
                    // do nowt
                }
            }
        }

        return people;
    }

}
