package com.it.androiddata;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SqliteExpterActivity extends AppCompatActivity implements View.OnClickListener {

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


//                inseret 操作；
//    参数2：数据库名称；
//    如果只有一个数据库名称，那么这个数据库位置会只有一个私有目录中；
//    如果带SD卡路径：那么数据库位置则在制定的路径下；
        String path = Environment.getExternalStorageState() + "stu.db";
         SQLiteOpenHelper helper = new SQLiteOpenHelper(this, path, null, 2) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
//            创建
                /**
                 Toast.makeText(SqliteExpterActivity.this, "数据库创建", Toast.LENGTH_LONG).show();
                 //            如果数据不逊在，则会调用onCreat方法，那么可以将表的创建工作放在这完成
                 String sql="create table test_tv(_id integer primary key autoincrement,"+"name varhcar(20),"+
                 "age_integer)";
                 sqLiteDatabase.execSQL(sql); */
            }
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        升级
                Toast.makeText(SqliteExpterActivity.this, "数据库升级", Toast.LENGTH_LONG).show();

            }
        };
//                用于获取数据库对象
//                1、数据库存在，则直接打开数据库
//                2、数据库不存在，则创建数据库，在打开数据库
//                3、数据库存在，但版本号升高了，则调用数据库升级方法
        helper.getReadableDatabase();  // 用于获取数据库对象；

        db=helper.getReadableDatabase();
        String nameStr=edt_username.getText().toString();
        String ageStr =edt_age.getText().toString().toString();
//                String sql="insert into info_tb(name,age,gender)values('"+nameStr+"',"+ageStr+",'"+genderStr+"' )";
        String sql="insert into info_tb(name ,age,gender)values(?,?,?)";
//        db.execSQL(sql,new String[] {nameStr,ageStr,genderStr});
//                db.rawQuery();    查询 select*from
//                db.execSQL();     添加 删除  修改  创建表
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
