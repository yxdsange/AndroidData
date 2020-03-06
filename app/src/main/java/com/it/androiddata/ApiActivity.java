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

public class ApiActivity extends AppCompatActivity implements View.OnClickListener {

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

        String path=Environment.getExternalStorageDirectory()+"/stu.db";
        SQLiteOpenHelper helper=new SQLiteOpenHelper(this,path,null,2) {
            @Override
            public void onCreate(SQLiteDatabase db) {
//    创建
                Toast.makeText(ApiActivity.this,"数据库创建",Toast.LENGTH_LONG).show();
//                如果数据库不存在，则会调用OnCreat方法，那么我们可以将表的创建工作放在这里完成
//
                /*
                  String sql ="create table test_tb(_id integer primary key autoincrememt)"+

                 */
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//                升级
                Toast.makeText(ApiActivity.this,"数据库升级",Toast.LENGTH_LONG).show();
            }
        };
//        用于获取数据库对象
//        1.数据库存在，则直接打开数据库
//        2.数据库不存在，则调用创建可数据库的方法，在发开数据库
//        3.数据库存在，但版本号升高了，则调用数据库升级方法
        db=helper.getReadableDatabase();
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

                break;
            case R.id.btn_check:
//                select 查询
//              select *from 表名 where _id =;
                String sql2 ="select * from info _tb";
//                查询结果
                Cursor cursor=db.rawQuery(sql2,null);
//                SimpleCursorAdapter 游标适配器
//              参数3，作为数据源；
                SimpleCursorAdapter simpleCursorAdapter=new SimpleCursorAdapter(
                        this,R.layout.item,cursor,
                        new String[]{"id","name","age","gender"},
                        new int[] {R.id.id_item,R.id.name_item,R.id.age_item,R.id.gender_item},
                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                stuList.setAdapter(simpleCursorAdapter);
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
