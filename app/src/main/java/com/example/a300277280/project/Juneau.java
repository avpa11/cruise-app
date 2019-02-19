package com.example.a300277280.project;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Juneau extends Activity {

    private DB db;
    private Double bookPrice;
    String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_juneau);

        db = new DB(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.8));

        final String portName = "Juneau";

        Button book = (Button) findViewById(R.id.btnBookJuneau);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    EditText qtyValue = (EditText) findViewById(R.id.etQtyJuneau);
                    String qty = qtyValue.getText().toString();
                    int qtyInt = Integer.parseInt(qty);

                    RadioButton privateTour = (RadioButton) findViewById(R.id.rdbPrivate);
                    RadioButton groupTour = (RadioButton) findViewById(R.id.rdbGroup);

                    Double price = 250.0;
                    Double privateTourPrice = 1.5;
                    Double groupTourPrice = 1.0;
                    Bundle extras = getIntent().getExtras();
                    String nameValue = extras.getString("Name");

                    if (privateTour.isChecked()) {
                        bookPrice = price * privateTourPrice;
                        type = "Private Tour";
                    } else if (groupTour.isChecked()) {
                        bookPrice = price * groupTourPrice;
                        type = "Group Tour";
                    }

                    ContentValues values = new ContentValues();
                    values.put(PortsTable.COLUMN_NAME_CustomerName, nameValue);
                    values.put(PortsTable.COLUMN_NAME_NameOfPort, portName);
                    values.put(PortsTable.COLUMN_NAME_Type, type);
                    values.put(PortsTable.COLUMN_NAME_Qty, qtyInt);
                    values.put(PortsTable.COLUMN_NAME_Price, bookPrice * qtyInt);
                    long newRowId = wdb.insert(PortsTable.TABLE_NAME, null, values);

                    finish();

                    Toast.makeText(getApplicationContext(), "Your tour is reserved", Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(), "Please fill the qty pf persons on tour", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
