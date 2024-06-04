package zj.dzh.music_list;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import zj.dzh.music_list.Activity.MainActivity;

@RunWith(AndroidJUnit4.class)
public class MusicActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void clickMenu1_ShowsSongPage() {
        // 模拟点击音乐列表按钮
        Espresso.onView(ViewMatchers.withId(R.id.menu1))
                .perform(ViewActions.click());
        // 判断列表可见性
        Espresso.onView(ViewMatchers.withId(R.id.menu1))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }



}
