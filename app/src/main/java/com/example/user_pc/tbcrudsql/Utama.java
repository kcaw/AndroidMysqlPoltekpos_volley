package com.example.user_pc.tbcrudsql;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.user_pc.tbcrudsql.ortu.HalamanOrtu;
import com.example.user_pc.tbcrudsql.pembayaran.HalamanPembayaran;
import com.example.user_pc.tbcrudsql.pendaftaran.MainActivity;

public class Utama extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WebView webView = (WebView) findViewById(R.id.webPolpos);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);

        // Tiga baris di bawah ini agar laman yang dimuat dapat
        // melakukan zoom.
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        // Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://if.poltekpos.ac.id");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private Boolean exit = false;
    @Override
// Detect when the back button is pressed

    public void onBackPressed() {
        mWebView = (WebView) findViewById(R.id.webPolpos);
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        }
        else {
            if (exit)
                this.finish();
            else {
                Toast.makeText(this, "PRESS AGAIN TO CLOSE !!!",
                        Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 3 * 1000);
            }

            // Let the system handle the back button

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.utama, menu);
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

            Toast.makeText(this, "Created by : "+ "\n Imron Sumadireja"+ "\n Yusniar Nur Syarif Sidiq", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_daftar) {
            Intent i = (new Intent(Utama.this, MainActivity.class));
            startActivity(i);

            Toast.makeText(this, "Pendaftaran", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_ortu) {
            Intent ii = (new Intent(Utama.this, HalamanOrtu.class));
            startActivity(ii);

            Toast.makeText(this, "Input Data Orang Tua", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_pembayaran) {
            Intent in = (new Intent(Utama.this, HalamanPembayaran.class));
            startActivity(in);

            Toast.makeText(this, "Pembayaran", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
