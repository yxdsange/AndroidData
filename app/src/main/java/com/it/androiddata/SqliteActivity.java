package com.it.androiddata;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SqliteActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_username, edt_age, edt_id;
    private Button btn_add, btn_check, btn_del, btn_update;
    private RadioButton rg_female, rg_man;
    private RadioGroup genderGp;
    private int i;
    private String  genderStr="男";
    private SQLiteDatabase db;
    private ListView stuList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_expter);
        initUI();
        initOnClickListener();

    }

    private void initOnClickListener() {
        btn_add.setOnClickListener(this);
        btn_check.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        rg_man.setOnClickListener(this);
        rg_female.setOnClickListener(this);
        genderGp.setOnClickListener(this);
    }

    private void initUI() {
        edt_username = findViewById(R.id.edt_username);
        edt_age = findViewById(R.id.edt_age);
        edt_id = findViewById(R.id.edt_id);
        btn_add = findViewById(R.id.btn_add);
        btn_check = findViewById(R.id.btn_check);
        btn_update = findViewById(R.id.btn_update);
        btn_del = findViewById(R.id.btn_del);
        rg_man = findViewById(R.id.rg_man);
        rg_female = findViewById(R.id.rg_female);
        genderGp=findViewById(R.id.genderGp);
        stuList=findViewById(R.id.stu_list);
    }
//  1、SQLiteOpenHelper
//  2、
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
//                添加操作；
//                参数二，数据库名称；
//                如果只有一个数据库名称，那么这个数据库的位置会是在私有目录中；
//                如果带SD卡路径，数据库位置则在指定的路径下
                String path=Environment.getExternalStorageState()+"text.db";
                SQLiteOpenHelper helper=new SQLiteOpenHelper(this,path,null,1) {
                    @Override
                    public void onCreate(SQLiteDatabase db) {
//                        创建
                        Toast.makeText(SqliteActivity.this,"数据库创建",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//                        升级
                        Toast.makeText(SqliteActivity.this,"数据库升级",Toast.LENGTH_LONG).show();

                    }
                };
                helper.getReadableDatabase(); // 用于获取数据库对象；
                break;
            case R.id.btn_check:
//                select 查询
//
                break;
            case R.id.btn_update:
//                更新
                break;
            case R.id.btn_del:
//                删除
                break;
            case R.id.genderGp:
                if(i== R.id.rg_man){
//                    男
                    genderStr="男";
                }else{
//                    女
                    genderStr="女";
                }
                break;
        }
    }
}
