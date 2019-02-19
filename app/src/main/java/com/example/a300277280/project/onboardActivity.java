package com.example.a300277280.project;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.Dialog;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class onboardActivity extends AppCompatActivity {

    //private TextView reservation;
    private TextView reservation;
    final int DAYS = 10;

    private DB db;

    String activityName;
    private String date;
    //String c;
    int i;
    int i1;
    int i2;

    Calendar c = Calendar.getInstance();
    DateFormat fmtDate = DateFormat.getInstance();

    DatePickerDialog datePickerDialog;

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            c.set(Calendar.YEAR, i);
            c.set(Calendar.MONTH, i1);
            c.set(Calendar.DAY_OF_MONTH, i2);
            date = fmtDate.format(c.getTime());
            reservation.setText("Reservation set for " + fmtDate.format(c.getTime()));
            //year = Calendar.YEAR + i;
            //month = Calendar.MONTH + i1;
            //day = Calendar.DAY_OF_MONTH + i2;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        db = new DB(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();

        reservation = (TextView) findViewById(R.id.reservation);
        Button jazz = (Button) findViewById(R.id.btnJazz);
        Button pop = (Button) findViewById(R.id.btnPop);
        Button classical = (Button) findViewById(R.id.btnClassical);
        Button cartoon = (Button) findViewById(R.id.btnCartoons);
        Button plays = (Button) findViewById(R.id.btnPlays);
        Button party = (Button) findViewById(R.id.btnParties);

        Bundle extras = getIntent().getExtras();
        String nameValue = extras.getString("Name");

        String selectQuery = "SELECT * FROM room WHERE customerName = '" + nameValue + "'";
        Cursor cursor = rdb.rawQuery(selectQuery, null); // 2nd arg is for where clause
        if (cursor.getCount() > 0) {

            jazz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    datePickerDialog = new DatePickerDialog(onboardActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                    activityName = "Jazz";

                                    Bundle extras = getIntent().getExtras();
                                    String nameValue = extras.getString("Name");

                                    ContentValues values = new ContentValues();
                                    values.put(Activities.COLUMN_NAME_CustomerName, nameValue);
                                    values.put(Activities.COLUMN_NAME_ActivityName, activityName);
                                    values.put(Activities.COLUMN_NAME_DATE, dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);
                                    long newRowId = wdb.insert(Activities.TABLE_NAME, null, values);

                                    reservation.setText("Reservation set for " + dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1);
                    datePickerDialog.show();
                }

            });

            pop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    datePickerDialog = new DatePickerDialog(onboardActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                    activityName = "Pop";

                                    Bundle extras = getIntent().getExtras();
                                    String nameValue = extras.getString("Name");

                                    ContentValues values = new ContentValues();
                                    values.put(Activities.COLUMN_NAME_CustomerName, nameValue);
                                    values.put(Activities.COLUMN_NAME_ActivityName, activityName);
                                    values.put(Activities.COLUMN_NAME_DATE, dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);
                                    long newRowId = wdb.insert(Activities.TABLE_NAME, null, values);

                                    reservation.setText("Reservation set for " + dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1);
                    datePickerDialog.show();
                }
            });

            classical.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    datePickerDialog = new DatePickerDialog(onboardActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                    activityName = "Classical";

                                    Bundle extras = getIntent().getExtras();
                                    String nameValue = extras.getString("Name");

                                    ContentValues values = new ContentValues();
                                    values.put(Activities.COLUMN_NAME_CustomerName, nameValue);
                                    values.put(Activities.COLUMN_NAME_ActivityName, activityName);
                                    values.put(Activities.COLUMN_NAME_DATE, dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);
                                    long newRowId = wdb.insert(Activities.TABLE_NAME, null, values);

                                    reservation.setText("Reservation set for " + dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1);
                    datePickerDialog.show();

                }
            });

            cartoon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    datePickerDialog = new DatePickerDialog(onboardActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    activityName = "Cartoon";

                                    Bundle extras = getIntent().getExtras();
                                    String nameValue = extras.getString("Name");

                                    ContentValues values = new ContentValues();
                                    values.put(Activities.COLUMN_NAME_CustomerName, nameValue);
                                    values.put(Activities.COLUMN_NAME_ActivityName, activityName);
                                    values.put(Activities.COLUMN_NAME_DATE, dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);
                                    long newRowId = wdb.insert(Activities.TABLE_NAME, null, values);

                                    reservation.setText("Reservation set for " + dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1);
                    datePickerDialog.show();

                }
            });

            plays.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    datePickerDialog = new DatePickerDialog(onboardActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                    activityName = "Plays";

                                    Bundle extras = getIntent().getExtras();
                                    String nameValue = extras.getString("Name");

                                    ContentValues values = new ContentValues();
                                    values.put(Activities.COLUMN_NAME_CustomerName, nameValue);
                                    values.put(Activities.COLUMN_NAME_ActivityName, activityName);
                                    values.put(Activities.COLUMN_NAME_DATE, dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);
                                    long newRowId = wdb.insert(Activities.TABLE_NAME, null, values);

                                    reservation.setText("Reservation set for " + dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1);
                    datePickerDialog.show();
                }
            });

            party.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Calendar c = Calendar.getInstance();
                    int mYear = c.get(Calendar.YEAR);
                    int mMonth = c.get(Calendar.MONTH);
                    int mDay = c.get(Calendar.DAY_OF_MONTH);

                    datePickerDialog = new DatePickerDialog(onboardActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                    activityName = "Party";

                                    Bundle extras = getIntent().getExtras();
                                    String nameValue = extras.getString("Name");

                                    ContentValues values = new ContentValues();
                                    values.put(Activities.COLUMN_NAME_CustomerName, nameValue);
                                    values.put(Activities.COLUMN_NAME_ActivityName, activityName);
                                    values.put(Activities.COLUMN_NAME_DATE, dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);
                                    long newRowId = wdb.insert(Activities.TABLE_NAME, null, values);

                                    reservation.setText("Reservation set for " + dayOfMonth + "/"
                                            + (monthOfYear + 1) + "/" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1);
                    datePickerDialog.show();

                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please assign the room first", Toast.LENGTH_SHORT).show();
        }
    }
}
