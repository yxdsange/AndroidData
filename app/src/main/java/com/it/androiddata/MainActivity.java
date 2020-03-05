package com.it.androiddata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_sp, bt_file, bt_sqlit, bt_contentprovider, bt_net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initOnClicklistener();
    }

    private void initOnClicklistener() {
        bt_sp.setOnClickListener(this);
        bt_file.setOnClickListener(this);
        bt_sqlit.setOnClickListener(this);
        bt_contentprovider.setOnClickListener(this);
        bt_net.setOnClickListener(this);
    }

    private void initUI() {
        bt_sp=findViewById(R.id.bt_sp);
        bt_file=findViewById(R.id.bt_file);
        bt_sqlit=findViewById(R.id.bt_sqlit);
        bt_contentprovider=findViewById(R.id.bt_contentprovider);
        bt_net=findViewById(R.id.bt_net);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_sp:
                startActivity(new Intent(MainActivity.this,SharedPreferencesActivity.class));
                break;

            case R.id.bt_file:
                startActivity(new Intent(MainActivity.this,FileActivity.class));
                break;

            case R.id.bt_contentprovider:
                break;

            case R.id.bt_sqlit:
                break;

            case R.id.bt_net:
                break;

        }
    }
}
