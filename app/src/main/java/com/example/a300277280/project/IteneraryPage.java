package com.example.a300277280.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

public class IteneraryPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itenerary_page);

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tabSpec;


        tabSpec = tabHost.newTabSpec("5 days");
        tabSpec.setIndicator("5 days");
        tabSpec.setContent(R.id.tab1);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("7 days");
        tabSpec.setIndicator("7 days");
        tabSpec.setContent(R.id.tab2);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("9 days");
        tabSpec.setIndicator("9 days");
        tabSpec.setContent(R.id.tab3);
        tabHost.addTab(tabSpec);

        tabHost.setCurrentTabByTag("5 days");

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                Toast.makeText(getBaseContext(), "option = " + tabId, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
