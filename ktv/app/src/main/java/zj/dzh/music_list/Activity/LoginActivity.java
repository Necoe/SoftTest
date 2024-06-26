package zj.dzh.music_list.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import zj.dzh.music_list.DataBase.UserDao;
import zj.dzh.music_list.R;

public class LoginActivity extends AppCompatActivity {
    public Button btn_login;    //登录按钮
    public Button btn_register; //注册按钮
    public EditText et_account; //账号输入框
    public EditText et_password;//密码输入框
    public UserDao dao;         //数据库对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView() {
        //去除标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        //绑定控件
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);

        //登录按钮
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = et_account.getText().toString().trim();
                String pass = et_password.getText().toString().trim();
                dao = new UserDao(getApplicationContext());
                dao.open();
                if (!dao.isExist(acc)) {
                    Toast.makeText(LoginActivity.this, "账号不存在，请重新输入！", Toast.LENGTH_SHORT).show();
                } else {
                    if (dao.getPassword(acc).equals(pass)) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        //创建意图对象，进行跳转
                        startActivity(intent);
                        //销毁该活动
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "密码错误，请重新输入！", Toast.LENGTH_SHORT).show();
                    }
                }
                dao.close();
            }
        });

        // 注册按钮
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            //判断结果码是否等于1，等于1则接受返回数据。
            if (requestCode == 1 && resultCode == 1) {
                String name = data.getStringExtra("acc");
                String password = data.getStringExtra("pass");
                et_account.setText(name);
                et_password.setText(password);
            }
        }
    }
}

