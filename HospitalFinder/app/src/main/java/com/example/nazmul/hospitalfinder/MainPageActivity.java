package com.example.nazmul.hospitalfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainPageActivity extends Activity {

    private final int SFLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(MainPageActivity.this,MenuPageActivity.class);
                MainPageActivity.this.startActivity(mainIntent);
                MainPageActivity.this.finish();
            }
        },  SFLASH_DISPLAY_LENGTH);

    }
}
