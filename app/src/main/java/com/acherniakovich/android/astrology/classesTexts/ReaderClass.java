package com.acherniakovich.android.astrology.classesTexts;

import android.app.Activity;
import android.util.Log;
import com.acherniakovich.android.astrology.MainActivity;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReaderClass extends Activity{
    final String FILENAME = "file";
    String readFile() {
        String str = "";
        try {
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(FILENAME)));
            // читаем содержимое
            while ((str = br.readLine()) != null) {
                Log.d(MainActivity.LOG_TAG, str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
