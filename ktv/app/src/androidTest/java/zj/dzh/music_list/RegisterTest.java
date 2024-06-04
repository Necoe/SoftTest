package zj.dzh.music_list;


import static androidx.test.espresso.Espresso.onView;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import zj.dzh.music_list.Activity.LoginActivity;

@RunWith(AndroidJUnit4.class)
public class RegisterTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    /**
     * 测试注册账号
     */
    @Test
    public void RegisterSuccessTest() throws InterruptedException {
        // 点击注册按钮
        onView(ViewMatchers.withId(R.id.btn_register))
                .perform(ViewActions.click());

        // 输入用户名
        onView(ViewMatchers.withId(R.id.reg_name))
                .perform(ViewActions.typeText("123"));

        Thread.sleep(1000);

        // 输入密码
        onView(ViewMatchers.withId(R.id.reg_password))
                .perform(ViewActions.typeText("123"));
        // 点击注册
        onView(ViewMatchers.withId(R.id.btn_reg))
                .perform(ViewActions.click());

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

    @Test
    public void RegisterSuccessTest2() throws InterruptedException {
        // 点击注册按钮
        onView(ViewMatchers.withId(R.id.btn_register))
                .perform(ViewActions.click());

        // 输入用户名
        onView(ViewMatchers.withId(R.id.reg_name))
                .perform(ViewActions.typeText("abc"));

        Thread.sleep(1000);

        // 输入密码
        onView(ViewMatchers.withId(R.id.reg_password))
                .perform(ViewActions.typeText("abc"));
        // 点击注册
        onView(ViewMatchers.withId(R.id.btn_reg))
                .perform(ViewActions.click());

        // 输入用户名
        onView(ViewMatchers.withId(R.id.et_account))
                .perform(ViewActions.typeText("abc"));

        // 输入密码
        onView(ViewMatchers.withId(R.id.et_password))
                .perform(ViewActions.typeText("abc"));

        // 点击登录
        onView(ViewMatchers.withId(R.id.btn_login))
                .perform(ViewActions.click());

    }

    public void Music() throws InterruptedException{







    }

























}
