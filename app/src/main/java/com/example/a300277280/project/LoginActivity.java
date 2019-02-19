package com.example.a300277280.project;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button login, cancel;
    EditText name, email;


    //TextView tx1;
    //int counter = 3;
    String admin;

    private DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d("MainActivity", "onCreate");
        db = new DB(this);
        final SQLiteDatabase wdb = db.getWritableDatabase();
        final SQLiteDatabase rdb = db.getReadableDatabase();

        login = (Button)findViewById(R.id.btnLogin);
        name = (EditText)findViewById(R.id.etName);
        email = (EditText)findViewById(R.id.etEmail);

        //final String email = ema.getText().toString();

        cancel = (Button)findViewById(R.id.btnCancel);
        //tx1 = (TextView)findViewById(R.id.textView3);
        //tx1.setVisibility(View.GONE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.length() > 0 && email.toString().length() > 0)
                {
                    if (email.getText().toString().contains("@") && email.getText().toString().contains("."))
                    {

                        //startActivity(new Intent(LoginActivity.this, MainPage.class));
                        Intent i = new Intent(LoginActivity.this, MainPage.class);
                        i.putExtra("Name", name.getText().toString());
                        i.putExtra("Email", email.getText().toString());

                        startActivity(i);

                        ContentValues values = new ContentValues();
                        values.put(Customers.COLUMN_NAME_NAME, name.getText().toString());
                        values.put(Customers.COLUMN_NAME_EMAIL, email.getText().toString());
                        values.put(Customers.COLUMN_NAME_PRICE, 0.0);
                        long newRowId = wdb.insert(Customers.TABLE_NAME, null, values);

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
