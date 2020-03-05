package com.it.androiddata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtInput;
    private Button btnSave, btnRead;
    private TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        initUI();
        initOnClicklistener();
    }

    private void initOnClicklistener() {
        btnSave.setOnClickListener(this);
        btnRead.setOnClickListener(this);

    }

    private void initUI() {
        edtInput = findViewById(R.id.edt_input);
        btnSave = findViewById(R.id.btn_save);
        btnRead = findViewById(R.id.btn_read);
        tv_show = findViewById(R.id.tv_show);
    }

    @Override
    public void onClick(View v) {
        File file= new File(getFilesDir(),"getFilesDir.txt");
        switch (v.getId()) {
            case R.id.btn_save:

                if (!file.exists()){
                    try {
                        file.createNewFile();
                        FileOutputStream fos=new FileOutputStream(file); // 不增量添加
                        fos.write(edtInput.getText().toString().getBytes());
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btn_read:
                try {
                    FileInputStream fis=new FileInputStream(file);
                    byte[] b=new byte[1024];
                    int len=fis.read(b);
                    String str2=new String(b,0,len);
                    tv_show.setText(str2);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
        }

    }
}
