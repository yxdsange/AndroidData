package com.it.androiddata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreferencesActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText accEdt, pwdEdt;
    private Button btn_login;
    private String account;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        initUI();
        initOnClickListener();
    }

    private void initUI() {
        accEdt =findViewById(R.id.et_userid);
        pwdEdt =findViewById(R.id.ed_pwd);
        btn_login=findViewById(R.id.btn_login);
//        SharePreference 的读取
//          第一步：获取SharePreference 对象（参数1，文件名，参数2：模式）
        SharedPreferences share=getSharedPreferences("myshare",MODE_PRIVATE);
//          第二部：根据Key获取内容 (参数1 key  参数2 ：当对应可以不存在时，返回参数2的内容作为默认值)
        String accStr=share.getString("account",null);
        String pwdStr=share.getString("pwd","");

        accEdt.setText(accStr);
        pwdEdt.setText(pwdStr);
    }

    private void initOnClickListener() {
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//    1、获取两个输入框的内容
        account = accEdt.getText().toString();
        pwd = pwdEdt.getText().toString();
//    2、验证
        if (account.equals("admin")&& pwd.equals("123")){

//            1、获取SharePreference 对象(参数1 ：文件名；参数2：模式)
            SharedPreferences share= getSharedPreferences("myshare",MODE_PRIVATE);
//            2、获取Editor对象，
            SharedPreferences.Editor ed=share.edit();
//            3、存储信息
            ed.putString("account",account);
            ed.putString("pwd",pwd);
//            4、执行提交操作
                ed.commit();
//                ed.clear();  // 清空信息
            Toast.makeText(SharedPreferencesActivity.this,"登录成功",Toast.LENGTH_LONG).show();
        }else{
//            弹出提示：账号或密码错误；
            Toast.makeText(SharedPreferencesActivity.this,"账号或密码错误",Toast.LENGTH_LONG).show();
        }
//          2.1、存储信息到SharePreference
//          2.2、验证失败，提示用户


    }
}
