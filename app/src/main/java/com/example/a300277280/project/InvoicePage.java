package com.example.a300277280.project;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InvoicePage extends AppCompatActivity {

    private DB db;

    Double RoomBasicPrice = 1000.0, roomPrice, finalRoomPrice = 0.0, servicePrice = 100.0, serviceCleaningPrice = 0.0, serviceBreackfastPrice = 0.0, servicePriceTotal = 0.0, finalServicePriceTotal = 0.0;
    Double ActivityBasicPrice = 10.0, activityPrice, finalActivityPrice = 0.0;
    Double basicTour = 0.0;
    Double grandTotal = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_page);

        try {

            db = new DB(this);
            final SQLiteDatabase wdb = db.getWritableDatabase();
            final SQLiteDatabase rdb = db.getReadableDatabase();

            Bundle extras = getIntent().getExtras();
            String nameValue = extras.getString("Name");
            String emailValue = extras.getString("Email");

            TextView name = (TextView) findViewById(R.id.textBillingName);
            TextView email = (TextView) findViewById(R.id.txtBillingEmail);
            TextView room = (TextView) findViewById(R.id.txtRoomInvoice);
            TextView service = (TextView) findViewById(R.id.txtRoomServiceInvoice);
            TextView onboard = (TextView) findViewById(R.id.txtOnboardInvoice);
            TextView ports = (TextView) findViewById(R.id.txtPortsInvoice);
            TextView total = (TextView) findViewById(R.id.txtTotalInvoice);
            TextView details = (TextView) findViewById(R.id.txtDetails);
            TextView date1 = (TextView) findViewById(R.id.txtDateView);

            String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());

            date1.setText(date_n);

            name.setText(nameValue);
            email.setText(emailValue);

            String selectRoomQuery = "SELECT  * FROM " + Room.TABLE_NAME + " WHERE customerName = '" + nameValue + "'";
            Cursor cursorRoom = rdb.rawQuery(selectRoomQuery, null);
            details.setText("");

            while (cursorRoom.moveToNext()) {
                //String id = cursorRoom.getString(0);
                //String customerName = cursorRoom.getString(1);
                String roomNumber = cursorRoom.getString(2);
                String roomType = cursorRoom.getString(3);
                String cleaning = cursorRoom.getString(4);
                String breackfast = cursorRoom.getString(5);

                switch (roomType) {
                    case ("Bella Inside"):
                        roomPrice = RoomBasicPrice;
                        break;
                    case ("Bella OceanView"):
                        roomPrice = RoomBasicPrice * 1.3;
                        break;
                    case ("Wellness"):
                        roomPrice = RoomBasicPrice * 1.8;
                        break;
                    case ("Fantasy"):
                        roomPrice = RoomBasicPrice * 2.1;
                        break;
                }

                switch (cleaning) {
                    case ("Yes"):
                        serviceCleaningPrice = servicePrice * 2;
                        break;
                    case ("No"):
                        serviceCleaningPrice = 0.0;
                        break;
                }

                switch (breackfast) {
                    case ("Yes"):
                        serviceBreackfastPrice = servicePrice * 3;
                        break;
                    case ("No"):
                        serviceBreackfastPrice = 0.0;
                        break;
                }

                servicePriceTotal = serviceCleaningPrice + serviceBreackfastPrice;

                finalRoomPrice += roomPrice;

                finalServicePriceTotal += servicePriceTotal;

                details.append("Your room number is " + roomNumber + " in " + roomType + ". Additional Cleaning? - " + cleaning + ". Additional Breackfast? - " + breackfast);
                details.append("\n");
            }

            room.setText(finalRoomPrice.toString());
            service.setText(finalServicePriceTotal.toString());


            String selectOnboardActivitiesQuery = "SELECT  * FROM " + Activities.TABLE_NAME + " WHERE customerName = '" + nameValue + "'";
            Cursor cursorActivities = rdb.rawQuery(selectOnboardActivitiesQuery, null);
            details.append("\n");

            while (cursorActivities.moveToNext()) {
                //String id = cursorActivities.getString(0);
                //String customerName = cursorActivities.getString(1);
                String musicType = cursorActivities.getString(2);
                String date = cursorActivities.getString(3);

                switch (musicType) {
                    case ("Jazz"):
                        activityPrice = ActivityBasicPrice;
                        break;
                    case ("Pop"):
                        activityPrice = ActivityBasicPrice * 1.1;
                        break;
                    case ("Classical"):
                        activityPrice = ActivityBasicPrice * 1.2;
                        break;
                    case ("Cartoon"):
                        activityPrice = ActivityBasicPrice;
                        break;
                    case ("Plays"):
                        activityPrice = ActivityBasicPrice * 2.0;
                        break;
                    case ("Party"):
                        activityPrice = ActivityBasicPrice * 3.0;
                        break;
                }

                finalActivityPrice += activityPrice;

                details.append("Onboard activity is " + musicType + " reserved on " + date);
                details.append("\n");
            }

            onboard.setText(finalActivityPrice.toString());

            String selectPortsQuery = "SELECT  * FROM " + PortsTable.TABLE_NAME + " WHERE customerName = '" + nameValue + "'";
            Cursor cursorPorts = rdb.rawQuery(selectPortsQuery, null);
            details.append("\n");

            while (cursorPorts.moveToNext()) {
                //String id = cursorPorts.getString(0);
                //String customerName = cursorPorts.getString(1);
                String portName = cursorPorts.getString(2);
                String type = cursorPorts.getString(3);
                String qty = cursorPorts.getString(4);
                String price = cursorPorts.getString(5);

                basicTour += Double.parseDouble(price);

                details.append("Port name is " + portName + " with " + type + " tour for " + qty);
                details.append("\n");
            }

            ports.setText(basicTour.toString());

            grandTotal = finalRoomPrice + finalServicePriceTotal + finalActivityPrice + basicTour;

            total.setText(grandTotal.toString());

            ContentValues values = new ContentValues();
            values.put(Customers.COLUMN_NAME_PRICE, grandTotal);
            wdb.update(Customers.TABLE_NAME, values, "customerName = '" + nameValue + "'", null);
        }
        catch (Exception ex)
        {
            Toast errorToast = Toast.makeText(InvoicePage.this, "Something went wrong", Toast.LENGTH_SHORT);
            errorToast.show();
        }
    }
}
