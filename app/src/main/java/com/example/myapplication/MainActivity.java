package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn,btn_dialog;
    BroadcastReceiver connectivityReceiver = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //check network broadcast reciever
        GeneralTools tools = GeneralTools.getInstance();
        connectivityReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                tools.doCheckNetwork(MainActivity.this, findViewById(R.id.rl_root));
            }
        };



        btn=findViewById(R.id.gotoHtml);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                goToHtmlActivity("http://pollerws.rahbarbazaar.com:2296/poller/v2/support/videos/en" ,true);
//                goToHtmlActivity("http://pollerws.rahbarbazaar.com:2296/poller/v2/support/videos/"
//                        + LocaleManager.getLocale(getResources()).getLanguage(), true);
            }
        });


        //dialog factory
        btn_dialog = findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(v -> new DialogFactory(MainActivity.this).create_testdialog());

    }


    private void goToHtmlActivity(String url, boolean shouldBeLoadUrl) {

        Intent intent = new Intent(MainActivity.this, HtmlLoaderActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("surveyDetails", false);
        intent.putExtra("isShopping", shouldBeLoadUrl);
        startActivity(intent);
        MainActivity.this.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }





    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(connectivityReceiver);
        super.onDestroy();
    }
}
