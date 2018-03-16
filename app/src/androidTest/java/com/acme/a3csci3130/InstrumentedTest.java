package com.acme.a3csci3130;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.firebase.database.FirebaseDatabase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.acme.a3csci3130", appContext.getPackageName());
    }

    @Test
    public void userCreate() throws Exception {

        MainActivity mA = main.getActivity();
        int count = mA.getCount();

        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(clearText()).perform(typeText("Mitchells Hatchet Job"));
        onView(withId(R.id.businessNumber)).perform(clearText()).perform(typeText("123456789"));
        onView(withId(R.id.primaryBusiness)).perform(clearText()).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(clearText()).perform(typeText("China"));
        onView(withId(R.id.province_territory)).perform(clearText()).perform(typeText("NS"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());

       assertEquals( count + 1, mA.getCount());
    }

    @Test
    public void userRead() throws Exception {

        MainActivity mA = main.getActivity();

        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(clearText()).perform(typeText("Mitchells Hatchet Jorb"));
        onView(withId(R.id.businessNumber)).perform(clearText()).perform(typeText("123456788"));
        onView(withId(R.id.primaryBusiness)).perform(clearText()).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(clearText()).perform(typeText("Chinu"));
        onView(withId(R.id.province_territory)).perform(clearText()).perform(typeText("NB"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());

        boolean somethingExists = false;
        if (mA.getCount() > 0)
            somethingExists = true;

        assertEquals(true, somethingExists);
    }

    @Test // Test not working as intended
    public void userUpdate() throws Exception {

        MainActivity mA = main.getActivity();

        // Create a business
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(clearText()).perform(typeText("Mitchells Hatchet Jorb2"));
        onView(withId(R.id.businessNumber)).perform(clearText()).perform(typeText("123456787"));
        onView(withId(R.id.primaryBusiness)).perform(clearText()).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(clearText()).perform(typeText("Chini"));
        onView(withId(R.id.province_territory)).perform(clearText()).perform(typeText("NT"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());

        // Update the business
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.name)).perform(clearText()).perform(typeText("Mitchell Hatchet Jorb2"));
        onView(withId(R.id.businessNumber)).perform(clearText()).perform(typeText("223456787"));
        onView(withId(R.id.primaryBusiness)).perform(clearText()).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(clearText()).perform(typeText("Rhini"));
        onView(withId(R.id.province_territory)).perform(clearText()).perform(typeText("NT"));
        onView(withId(R.id.updateButton)).perform(click());

        boolean somethingExists = false;
        if (mA.getCount() > 0)
            somethingExists = true;

        assertEquals(true, somethingExists);
    }

    @Test // Test not working as intended
    public void userDelete() throws Exception {

        MainActivity mA = main.getActivity();

        // create a business to delete
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(clearText()).perform(typeText("Mitchell Hatchet Job"));
        onView(withId(R.id.businessNumber)).perform(clearText()).perform(typeText("123456487"));
        onView(withId(R.id.primaryBusiness)).perform(clearText()).perform(typeText("Fisher"));
        onView(withId(R.id.address)).perform(clearText()).perform(typeText("Chaps"));
        onView(withId(R.id.province_territory)).perform(clearText()).perform(typeText("QC"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());

        // make sure we added a business
        int count = mA.getCount();

        // remove the business
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.deleteButton)).perform(click());

        // check that we removed the business
        assertEquals( count - 1, mA.getCount());
    }

}
