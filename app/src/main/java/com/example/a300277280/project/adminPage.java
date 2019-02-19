package com.example.a300277280.project;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class adminPage extends AppCompatActivity {

    private DB db;
    private TextView text;

    RadioButton customersRadio;
    RadioButton roomsRadio;
    RadioButton portsRadio;
    RadioButton activitiesRadio;

    String tableName;

    EditText eName;
    EditText eEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        db = new DB(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();

        eName = (EditText) findViewById(R.id.etName);
        eEmail = (EditText) findViewById(R.id.etEmail);

        customersRadio = (RadioButton) findViewById(R.id.rdbCustomers);
        roomsRadio = (RadioButton) findViewById(R.id.rdbRooms);
        portsRadio = (RadioButton) findViewById(R.id.rdbPorts);
        activitiesRadio = (RadioButton) findViewById(R.id.rdbActivities);

        try
        {
            Button add = (Button) findViewById(R.id.btnAdd);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ContentValues values = new ContentValues();
                    values.put(Customers.COLUMN_NAME_NAME, eName.getText().toString());
                    values.put(Customers.COLUMN_NAME_EMAIL, eEmail.getText().toString());
                    values.put(Customers.COLUMN_NAME_PRICE, 0.0);
                    long newRowId = wdb.insert(Customers.TABLE_NAME, null, values);
                }
            });

            Button show = (Button) findViewById(R.id.btnShow);
            text = (TextView) findViewById(R.id.text);
            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (customersRadio.isChecked())
                    {
                        tableName = "customers";
                    }
                    else if (roomsRadio.isChecked())
                    {
                        tableName = "room";
                    }
                    else if (portsRadio.isChecked())
                    {
                        tableName = "ports";
                    }
                    else if (activitiesRadio.isChecked())
                    {
                        tableName = "activities";
                    }

                    if (tableName.equals("customers"))
                    {
                        String selectQuery = "SELECT  DISTINCT customerName, email, price FROM " + tableName + " ";
                        Cursor cursor = rdb.rawQuery(selectQuery, null); // 2nd arg is for where clause
                        text.setText("");
                        while (cursor.moveToNext()) {
                            for (int x = 0; x < cursor.getColumnCount(); x++) {
                                text.append(cursor.getString(x) + " ");
                            }
                            text.append("\n");
                        }
                    }

                    else
                        {
                        String selectQuery = "SELECT  * FROM " + tableName + " ";
                        Cursor cursor = rdb.rawQuery(selectQuery, null); // 2nd arg is for where clause
                        text.setText("");
                        while (cursor.moveToNext()) {
                            for (int x = 0; x < cursor.getColumnCount(); x++) {
                                text.append(cursor.getString(x) + " ");
                            }
                            text.append("\n");
                        }
                    }
                }
            });

            Button update = (Button) findViewById(R.id.btnUpdate);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //final EditText eID = (EditText) findViewById(R.id.etId);

                    try {
                        String selectQuery = "SELECT * FROM " + Customers.TABLE_NAME + " WHERE " + Customers.COLUMN_NAME_NAME + " = '" + eName.getText().toString() + "' ";
                        Cursor cursor = rdb.rawQuery(selectQuery, null);
                        text.setText("");

                        while (cursor.moveToNext()) {
                            for (int x = 0; x < cursor.getColumnCount(); x++) {
                                String column1 = cursor.getString(1);
                                String column2 = cursor.getString(2);
                                eName.setText(column1);
                                eEmail.setText(column2);

                                final Button save = (Button) findViewById(R.id.btnSave);
                                save.setVisibility(View.VISIBLE);
                                save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ContentValues values = new ContentValues();
                                        values.put(Customers.COLUMN_NAME_NAME, eName.getText().toString());
                                        values.put(Customers.COLUMN_NAME_EMAIL, eEmail.getText().toString());
                                        wdb.update(Customers.TABLE_NAME, values, "customerName= '" + eName.getText().toString() + "'", null);
                                        save.setVisibility(View.GONE);
                                    }
                                });

                            }
                        }
                    } catch (Exception ex) {

                    }

                }
            });

            Button delete = (Button) findViewById(R.id.btnDelete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long newRowId = wdb.delete(tableName, null, null);
                    text.setText("");
                }
            });

            Button logs = (Button) findViewById(R.id.btnLogs);
            logs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String selectQuery = "SELECT * FROM customers";
                    Cursor cursor = rdb.rawQuery(selectQuery, null);
                    text.setText("");
                    while (cursor.moveToNext()) {
                        for (int x = 0; x < cursor.getColumnCount(); x++) {
                            text.append(cursor.getString(x) + " ");
                        }
                        text.append("\n");
                    }
                }
            });
        }
        catch (Exception ex)
        {
            Toast errorToast = Toast.makeText(adminPage.this, "Something went wrong", Toast.LENGTH_SHORT);
            errorToast.show();
        }
    }

        protected void onDestroy () {
            db.close();
            super.onDestroy();
            Log.d("MainActivity", "onDestroy");
        }
    }

