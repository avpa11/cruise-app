package com.example.a300277280.project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Ports extends AppCompatActivity {

    private Button sitka;
    private Button juneau;
    private DB db;

    String nameValue;

    private void startMyActivity(Intent intent) {
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ports);

        db = new DB(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();

        sitka = (Button) findViewById(R.id.btnSitkaPT);
        juneau = (Button) findViewById(R.id.btnJuneauPT);

        Bundle extras = getIntent().getExtras();
        nameValue = extras.getString("Name");

        String selectQuery = "SELECT * FROM room WHERE customerName = '" + nameValue + "'";
        Cursor cursor = rdb.rawQuery(selectQuery, null); // 2nd arg is for where clause
        if (cursor.getCount() > 0) {

            sitka.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(Ports.this, Sitka.class);
                    i.putExtra("Name", nameValue);
                    startActivity(i);

                }
            });

            juneau.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(Ports.this, Juneau.class);
                    i.putExtra("Name", nameValue);
                    startActivity(i);

                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please assign the room first", Toast.LENGTH_SHORT).show();
        }
    }

}