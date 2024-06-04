//import androidx.test.espresso.Espresso;
//import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.matcher.ViewMatchers;
//import androidx.test.rule.ActivityTestRule;
//import org.junit.Rule;
//import org.junit.Test;
//import zj.dzh.music_list.Activity.RegisterActivity;
//import zj.dzh.music_list.R;
//
//public class RegisterActivityTest {
//
//    @Rule
//    public ActivityTestRule<RegisterActivity> activityRule = new ActivityTestRule<>(RegisterActivity.class);
//
//    @Test
//    public void testRegistrationSuccess() {
//        // Enter valid credentials
//        Espresso.onView(ViewMatchers.withId(R.id.reg_name)).perform(ViewActions.typeText("test_user"));
//        Espresso.onView(ViewMatchers.withId(R.id.reg_password)).perform(ViewActions.typeText("test_password"));
//
//        // Click the register button
//        Espresso.onView(ViewMatchers.withId(R.id.btn_reg)).perform(ViewActions.click());
//
//        // Verify success message toast is displayed
//        Espresso.onView(ViewMatchers.withText("注册成功！")).inRoot(new ToastMatcher())
//                .check(matches(isDisplayed()));
//
//        // You can also add assertions to verify the activity navigation to the login screen
//    }
//
//    // Define a custom matcher for toast messages
//    public static class ToastMatcher extends TypeSafeMatcher<Root> {
//        @Override
//        public void describeTo(Description description) {
//            description.appendText("is toast");
//        }
//
//        @Override
//        public boolean matchesSafely(Root root) {
//            int type = root.getWindowLayoutParams().get().type;
//            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
//                IBinder windowToken = root.getDecorView().getWindowToken();
//                IBinder appToken = root.getDecorView().getApplicationWindowToken();
//                if (windowToken == appToken) {
//                    return true;
//                }
//            }
//            return false;
//        }
//    }
//}
