package com.acherniakovich.android.astrology;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.q42.android.scrollingimageview.ScrollingImageView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String LOG_TAG = "MyLogs";
    private String resultFromSelectLenguage;
    private ViewPager viewPager;
    private CustomSwipeAdapter customSwipeAdapter;
    private ImageView back;
    private ImageView next;
    private FrameLayout frame_layout_container;
    private ScrollingImageView scrollingBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Boolean isFirstRun = getSharedPreferences("PREFERENCE",MODE_PRIVATE).getBoolean("isfirstrun",true);
        if (isFirstRun){
            Toast.makeText(this, "FirstRun", Toast.LENGTH_SHORT).show();
            getSharedPreferences("PREFERENCE",MODE_PRIVATE).edit().putBoolean("isfirstrun",false).commit();
            Intent intent = new Intent(MainActivity.this,SelectLenguage.class);
            startActivityForResult(intent,0);
        }

        scrollingBackground = (ScrollingImageView)findViewById(R.id.scrolling_background);

        frame_layout_container = (FrameLayout)findViewById(R.id.frame_layout_container);

        back = (ImageView)findViewById(R.id.back);
        next = (ImageView)findViewById(R.id.next);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        customSwipeAdapter = new CustomSwipeAdapter(this);
        viewPager.setAdapter(customSwipeAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {
                if (position==0){
                    back.setVisibility(View.INVISIBLE);
                    next.setVisibility(View.VISIBLE);
                }else if (position==1){
                    back.setVisibility(View.VISIBLE);
                    next.setVisibility(View.VISIBLE);
                }else if (position==2){
                    back.setVisibility(View.VISIBLE);
                    next.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddInformation.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode==0){
            finish();
            return;
        }

        if (data==null){
            return;
        }
        resultFromSelectLenguage = data.getStringExtra("lenguage");
        Log.d(LOG_TAG,resultFromSelectLenguage);

        if (resultFromSelectLenguage.equals("russian")){
            //all texts on russian

        }else if (resultFromSelectLenguage.equals("english")){
            //all texts on english
        }
        Toast.makeText(this, resultFromSelectLenguage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onClick(View view) {
        TextView t = (TextView)view.findViewById(R.id.image_count);
        if (t.getText().toString().equals("Прогноз")){
            Intent intent = new Intent(MainActivity.this,Prognoz.class);
            startActivity(intent);
        }else if (t.getText().toString().equals("Совместимость")){
            Intent intent = new Intent(MainActivity.this,Sovmestimost.class);
            startActivity(intent);
        }else if (t.getText().toString().equals("Периоды")){
            Intent intent = new Intent(MainActivity.this,Periodi.class);
            startActivity(intent);
        }
    }
}
