package zj.dzh.music_list;


import static androidx.test.espresso.Espresso.onView;

import android.widget.Button;
import android.widget.EditText;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import zj.dzh.music_list.Activity.LoginActivity;

public class LoginActivityTest {

    private LoginActivity activity;
    private EditText et_account;
    private EditText et_password;
    private Button btn_login;


    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    //有效用例
    @Test
    public void testLoginWithValidCredentials() throws InterruptedException {
        // 输入用户名
        onView(ViewMatchers.withId(R.id.et_account))
                .perform(ViewActions.typeText("123"));
        // 输入密码
        onView(ViewMatchers.withId(R.id.et_password))
                .perform(ViewActions.typeText("123"));
        // 点击登录
        onView(ViewMatchers.withId(R.id.btn_login))
                .perform(ViewActions.click());
    }

    //有效用例
    @Test
    public void testLoginWithValidCredentials_2() throws InterruptedException {
        // 输入用户名
        onView(ViewMatchers.withId(R.id.et_account))
                .perform(ViewActions.typeText("qazwsxedc"));
        // 输入密码
        onView(ViewMatchers.withId(R.id.et_password))
                .perform(ViewActions.typeText("plokijuhyg"));
        // 点击登录
        onView(ViewMatchers.withId(R.id.btn_login))
                .perform(ViewActions.click());
    }


    //有效用例
    @Test
    public void testLoginWithValidCredentials_3() throws InterruptedException {
        // 输入用户名
        onView(ViewMatchers.withId(R.id.et_account))
                .perform(ViewActions.typeText("q7w8e9"));
        // 输入密码
        onView(ViewMatchers.withId(R.id.et_password))
                .perform(ViewActions.typeText("a4s5d6"));
        // 点击登录
        onView(ViewMatchers.withId(R.id.btn_login))
                .perform(ViewActions.click());
    }

    //有效用例
    @Test
    public void testLoginWithValidCredentials_4() throws InterruptedException {
        // 输入用户名
        onView(ViewMatchers.withId(R.id.et_account))
                .perform(ViewActions.typeText("!q@w#e"));
        // 输入密码
        onView(ViewMatchers.withId(R.id.et_password))
                .perform(ViewActions.typeText("a4s5d6"));
        // 点击登录
        onView(ViewMatchers.withId(R.id.btn_login))
                .perform(ViewActions.click());
    }


    //无效用例   未注册
    @Test
    public void testLoginWithInvalidCredentials() throws InterruptedException {
        // 输入用户名
        onView(ViewMatchers.withId(R.id.et_account))
                .perform(ViewActions.typeText("456"));
        // 输入密码
        onView(ViewMatchers.withId(R.id.et_password))
                .perform(ViewActions.typeText("456"));
        // 点击登录
        onView(ViewMatchers.withId(R.id.btn_login))
                .perform(ViewActions.click());
    }


    @Test
    public void onActivityResult() {
    }
}