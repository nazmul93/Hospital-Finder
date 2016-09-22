package com.example.nazmul.hospitalfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPageActivity extends Activity {

    Button search,hospital_map ,list,about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        search=(Button)findViewById(R.id.button_Search);
        hospital_map=(Button)findViewById(R.id.button_hospital_map);
        list=(Button)findViewById(R.id.button_list);
        about=(Button)findViewById(R.id.button_about);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPageActivity.this,MapActivity.class);

                startActivity(intent);
            }
        });

        hospital_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPageActivity.this,HospitalMarker.class);

                startActivity(intent);
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPageActivity.this,HospitalList.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPageActivity.this,About.class);

                startActivity(intent);
            }
        });
    }
}
