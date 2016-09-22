package com.example.nazmul.hospitalfinder;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class HospitalList extends ActionBarActivity {

    TextView hospitallist;

    String Hospital_name,Hospital_address,Hospital_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);
        hospitallist = (TextView)findViewById(R.id.hospital_list);

        ShowData();
    }


    //showing the data from database
    public void ShowData(){

        CallData();

        //open the data base
        SQLiteDatabase db2 = openOrCreateDatabase("Hospital", MODE_PRIVATE, null);

        //searching from the table using query by the Cursor class obj
        final Cursor cursor = db2.rawQuery("SELECT * FROM HOSPITAL_LIST", null);

        //checking for the data from table in 1st row
        if(cursor.moveToFirst())
        {

            do{
                //fetching out data from column
                Hospital_name = cursor.getString(cursor.getColumnIndex("NAME"));
                Hospital_address = cursor.getString(cursor.getColumnIndex("ADDRESS"));
                Hospital_phone = cursor.getString(cursor.getColumnIndex("PHONE"));

                //print all the same type data in append()
                hospitallist.append("Hospital Name : "+Hospital_name+"\nAddress : "+Hospital_address+"\nPhone : "+Hospital_phone+"\n\n");

                //color the Text View
                hospitallist.setTextColor(Color.parseColor("#0000cc"));


            }while(cursor.moveToNext()!=false);
        }
        db2.close();

    }
    public void CallData(){

        //create database
        SQLiteDatabase db = openOrCreateDatabase("Hospital", MODE_PRIVATE, null);

        //removing all the data previously inserted
        db.execSQL("DROP TABLE IF EXISTS HOSPITAL_LIST");

        //create table with column
        db.execSQL("CREATE TABLE IF NOT EXISTS HOSPITAL_LIST (NAME VARCHAR,ADDRESS VARCHAR,PHONE VARCHAR);");


        //inserting data into the table
        db.execSQL("INSERT INTO HOSPITAL_LIST VALUES ('Dhaka Medical College','Fuller Road,Ramna,Dhaka','02-9663429, 8616744')");
        db.execSQL("INSERT INTO HOSPITAL_LIST VALUES ('Bangabandhu Sheikh Mujib Medical University','Shahbag,Dhaka-1000','02-55165760-94')");
        db.execSQL("INSERT INTO HOSPITAL_LIST VALUES ('Shaheed Shuhrawardy Medical College Hospital','Sher-E-Bangla Nagar,Dhaka 1207','02-9130800-19')");
        db.execSQL("INSERT INTO HOSPITAL_LIST VALUES ('Sir Salimullah Medical College & Hospital','Bangshal Road,Dhaka','02-7319002-5, 7312398')");
        db.execSQL("INSERT INTO HOSPITAL_LIST VALUES ('Bangladesh Medical College','Dhanmondi,Rd No.14A,Dhaka','02-8115843')");
        db.execSQL("INSERT INTO HOSPITAL_LIST VALUES ('Dhaka National Medical College','53/1,Johnson Road,Dhaka-1100','02-7118272, 7121023-4')");
        db.execSQL("INSERT INTO HOSPITAL_LIST VALUES ('Z. H. Sikder Womens Medical College','Monica Estate, Western Dhanmondi, Dhaka-1209','02-811-3313')");
        db.execSQL("INSERT INTO HOSPITAL_LIST VALUES ('Labaid Cardiac Hospital','House- 01 & 03, Road-04, Dhanmondi.Dhaka 1205','02-9676356')");
        db.execSQL("INSERT INTO HOSPITAL_LIST VALUES ('United Hospital Limited','Plot 15, Road 71, Gulshan,Dhaka 1212','02-8836444')");

        db.execSQL("INSERT INTO HOSPITAL_LIST VALUES ('Apollo Hospital Dhaka','Plot:81,Block:E,Bashundhara R/A,Dhaka 1229','02-8845242')");
        db.execSQL("INSERT INTO HOSPITAL_LIST VALUES ('Ibrahim Medical College','122 Kazi Nazrul Islam Avenue,Dhaka-1000','02-9663560')");

        db.execSQL("INSERT INTO HOSPITAL_LIST VALUES ('Mymensingh Medical College Hospital','Dhaka-Mymensingh Highway, 2206 Mymensingh, Dhaka','5561055702')");
        db.execSQL("INSERT INTO HOSPITAL_LIST VALUES ('Chittagong Medical College Hospital','Chatteswari Road,Chittagong','88-31-21215')");
        db.execSQL("INSERT INTO HOSPITAL_LIST VALUES ('Sher-e-Bangla Medical College','Band Road ,Barishal','02-8845242')");

        db.close();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hospital_list, menu);
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
}
