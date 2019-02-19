package com.example.a300277280.project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class RoomPage extends AppCompatActivity {
    public static final CharSequence[] ROOMS_OPTIONS = {"Bella Inside", "Bella OceanView", "Wellness", "Fantasy"};

    private DB db;

    String cleaning = "No", breakfast = "No";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_page);

        Spinner spinner1 = (Spinner) findViewById(R.id.spCategory);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, ROOMS_OPTIONS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Specify the layout to use when the list of choices appears
        spinner1.setAdapter(adapter); // Apply the adapter to the spinner

        db = new DB(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();

        Button book = (Button) findViewById(R.id.btnBook);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText roomNumber = (EditText) findViewById(R.id.etRoomNumber);
                String roomValue = roomNumber.getText().toString();
                int roomInt = Integer.parseInt(roomValue);

                Spinner spinner = (Spinner) findViewById(R.id.spCategory);
                String category = spinner.getSelectedItem().toString();

                RadioButton cleaningYes = (RadioButton) findViewById(R.id.rdbCleaningYes);
                RadioButton cleaningNo = (RadioButton) findViewById(R.id.rdbCleaningNo);

                RadioButton breackfastYes = (RadioButton) findViewById(R.id.rdbBreackfastYes);
                RadioButton breackfastNo = (RadioButton) findViewById(R.id.rdbBreackfastNo);

                if(cleaningYes.isChecked())
                {
                    cleaning = "Yes";
                }

                if(breackfastYes.isChecked())
                {
                    breakfast = "Yes";
                }

                Bundle extras = getIntent().getExtras();
                String nameValue = extras.getString("Name");

                if(roomInt >= 5001 && roomInt <= 16137)
                {

                    String selectPortsQuery = "SELECT * FROM " + Room.TABLE_NAME + " WHERE roomNumber = '" + roomInt + "'";
                    Cursor cursor = rdb.rawQuery(selectPortsQuery, null);
                    if(cursor.getCount() > 0)
                    {
                        Toast errorToast = Toast.makeText(RoomPage.this, "Sorry this room is already occupied. Please choose another one", Toast.LENGTH_SHORT);
                        errorToast.show();
                    }
                    else
                    {
                        ContentValues values = new ContentValues();
                        values.put(Room.COLUMN_NAME_CustomerName, nameValue);
                        values.put(Room.COLUMN_NAME_RoomNumber, roomValue);
                        values.put(Room.COLUMN_NAME_RoomType, category);
                        values.put(Room.COLUMN_NAME_Cleaning, cleaning);
                        values.put(Room.COLUMN_NAME_Breackfast, breakfast);
                        long newRowId = wdb.insert(Room.TABLE_NAME, null, values);

                        Toast.makeText(getApplicationContext(),"You reserved room #" + roomValue, Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast errorToast = Toast.makeText(RoomPage.this, "Plase enter valid room number", Toast.LENGTH_SHORT);
                    errorToast.show();
                }
            }

        });
    }
}
