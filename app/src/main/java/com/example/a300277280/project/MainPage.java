package com.example.a300277280.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String nameValue;
    String emailValue;
    String adminValue;
    Button admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View v = navigationView.getHeaderView(0);
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView email = (TextView) v.findViewById(R.id.email);
        Bundle extras = getIntent().getExtras();
        nameValue = extras.getString("Name");
        emailValue = extras.getString("Email");
        //adminValue = extras.getString("Admin");
        name.setText(nameValue);
        email.setText(emailValue);
        admin = (Button) findViewById(R.id.btnAdmin);

        if(nameValue.equals("snowman"))
        {
            admin.setVisibility(View.VISIBLE);
        }

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainPage.this, adminPage.class));

            }
        });
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
        getMenuInflater().inflate(R.menu.main_page, menu);
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

        if (id == R.id.nav_itenerary) {

            startActivity(new Intent(MainPage.this, IteneraryPage.class));

        } else if (id == R.id.nav_activities) {

            Intent i = new Intent(MainPage.this, onboardActivity.class);
            i.putExtra("Name", nameValue);
            startActivity(i);

        } else if (id == R.id.nav_ports) {

            Intent i = new Intent(MainPage.this, Ports.class);
            i.putExtra("Name", nameValue);
            startActivity(i);

        } else if (id == R.id.nav_room) {

            Intent i = new Intent(MainPage.this, RoomPage.class);
            i.putExtra("Name", nameValue);
            startActivity(i);

        } else if (id == R.id.nav_invoice) {

            Intent i = new Intent(MainPage.this, InvoicePage.class);
            i.putExtra("Name", nameValue);
            i.putExtra("Email", emailValue);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
