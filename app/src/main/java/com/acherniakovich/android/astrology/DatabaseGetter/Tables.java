package com.acherniakovich.android.astrology.DatabaseGetter;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Tables extends RealmObject{

    @Required
    protected String date;

    protected String result;


    public Tables(String date, String result) {
        this.date = date;
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
