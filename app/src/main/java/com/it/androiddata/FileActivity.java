package com.it.androiddata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtInput;
    private Button btnSave, btnRead;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        initUI();
        initOnClicklistener();

    }

    private void initOnClicklistener() {
        btnSave.setOnClickListener(this);
        btnRead.setOnClickListener(this);
    }

    private void initUI() {
        edtInput=findViewById(R.id.edt_input);
        btnSave = findViewById(R.id.btn_save);
        btnRead = findViewById(R.id.btn_read);
    }

    @Override
    public void onClick(View v) {
        String path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/yxd.txt";
        Log.e("TAG",path);
//        Environment.getExternalStorageState().equals("mounted");
        switch (v.getId()) {
            case R.id.btn_save:

                int permission_write= ContextCompat.checkSelfPermission(FileActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                int permission_read=ContextCompat.checkSelfPermission(FileActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE);
                if(permission_write!= PackageManager.PERMISSION_GRANTED
                        || permission_read!=PackageManager.PERMISSION_GRANTED){

                    Toast.makeText(this, "正在请求权限", Toast.LENGTH_SHORT).show();
                    //申请权限，特征码自定义为1，可在回调时进行相关判断
                    ActivityCompat.requestPermissions(FileActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }else {
                    File file=new File(path);
                    try {
                        if (!file.exists()){
                            file.createNewFile();
                        }
                        FileOutputStream fos=new FileOutputStream(path,true);
                        String str=edtInput.getText().toString();
                        fos.write(str.getBytes());
                        Log.e("TAG","========写文件成功======");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                break;
            case R.id.btn_read:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_DENIED){
//                    权限申请成功
                }else {
//                    用户拒绝权限
//                    Toast.makeText(this,"无法获取SD卡读写权限",Toast.LENGTH_LONG).show();
//                    finish();
                    break;
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }







}
