package com.acme.a3csci3130;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import static org.hamcrest.Matchers.not;

/*
 *Code Reference for the Toast test
 *Title: gistfile1.txt
 *Author: Bruno de Lima e Silva
 *Date: Apr 5, 2016
 *Availability: https://gist.github.com/brunodles/badaa6de2ad3a84138d517795f15efc7#file-gistfile1-txt
 */

/**
 * Espresso test for the application.
 * The tests through InterruptedExceptions because of the wait statements.
 * @author Matthew MacMullin
 */
@RunWith(AndroidJUnit4.class)
public class UiTests {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    /**
     * Test for new business entry functionality
     * @throws InterruptedException
     */
    @Test
    public void newEntryTest() throws InterruptedException {
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText("2"));
        closeSoftKeyboard();
        setBaseUIFieldValues();
        onView(withId(R.id.submitButton)).perform(click());
        Thread.sleep(1000);
        onView(withText("Error: Creation failed!")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        onView(withId(R.id.name)).perform(replaceText("UI Test2"));
        closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());
        Thread.sleep(1000);
        onView(withText("Business entry created")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        Thread.sleep(1000);

        String tag = "UI Test2";
        onView(withTagValue(Matchers.is((Object) tag))).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.name)).check(matches(withText("UI Test2")));
        onView(withId(R.id.number)).check(matches(withText("123456789")));
        onView(withId(R.id.primaryBusiness)).check(matches(withText("Processor")));
        onView(withId(R.id.address)).check(matches(withText("123 Espresso test")));
        onView(withId(R.id.provinceSpinner)).check(matches(withSpinnerText(containsString("NS"))));

        onView(withId(R.id.deleteButton)).perform(click());
    }

    /**
     * Test for firebase rules
     * @throws InterruptedException
     */
    @Test
    public void firebaseRulesTest() throws InterruptedException {
        onView(withId(R.id.submitButton)).perform(click());
        setBaseUIFieldValues();
        onView(withId(R.id.name)).perform(replaceText("5"));
        onView(withId(R.id.submitButton)).perform(click());
        Thread.sleep(1000);
        onView(withText("Error: Creation failed!")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        onView(withId(R.id.name)).perform(replaceText("UI Test5"));
        onView(withId(R.id.primaryBusiness)).perform(replaceText("Fish Stick Maker"));
        onView(withId(R.id.submitButton)).perform(click());
        Thread.sleep(1000);
        onView(withText("Error: Creation failed!")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        onView(withId(R.id.number)).perform(replaceText("andtuyolp"));
        onView(withId(R.id.primaryBusiness)).perform(replaceText("Fish Monger"));
        onView(withId(R.id.submitButton)).perform(click());
        Thread.sleep(1000);
        onView(withText("Error: Creation failed!")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        onView(withId(R.id.name)).perform(replaceText("123456789"));
        onView(withId(R.id.address)).perform(replaceText("12345678901234567890" +
                "12345678901234567890123456789012345678901234567890"));
        onView(withId(R.id.submitButton)).perform(click());
        Thread.sleep(1000);
        onView(withText("Error: Creation failed!")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        onView(withId(R.id.name)).perform(replaceText("UI Test5"));
        onView(withId(R.id.address)).perform(replaceText("#1 test Street"));
        onView(withId(R.id.submitButton)).perform(click());
        Thread.sleep(1000);
        onView(withText("Error: Creation failed!")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    /**
     * Test for entry deletion functionality
     * @throws InterruptedException
     */
    @Test
    public void deleteTest() throws InterruptedException {
        onView(withId(R.id.submitButton)).perform(click());
        setBaseUIFieldValues();
        onView(withId(R.id.name)).perform(replaceText("UI Test4"));
        closeSoftKeyboard();
        onView(withId(R.id.submitButton)).perform(click());

        Thread.sleep(2000);
        String tag = "UI Test4";
        onView(withTagValue(Matchers.is((Object) tag))).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.deleteButton)).perform(click());
        Thread.sleep(1000);
        onView(withText("Business entry deleted")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        Thread.sleep(1000);

        onView(withTagValue(Matchers.is((Object) tag))).check(doesNotExist());
    }

    /**
     * Test for firebase entry access functionality
     * @throws InterruptedException
     */
    @Test
    public void readDetailsTest() throws InterruptedException {
        Thread.sleep(2000);
        String tag = "UI Test";
        onView(withTagValue(Matchers.is((Object) tag))).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.name)).check(matches(withText("UI Test")));
        onView(withId(R.id.number)).check(matches(withText("123456789")));
        onView(withId(R.id.primaryBusiness)).check(matches(withText("Processor")));
        onView(withId(R.id.address)).check(matches(withText("123 Espresso test")));
        onView(withId(R.id.provinceSpinner)).check(matches(withSpinnerText(containsString("NS"))));
    }

    /**
     * Test for entry update functionality
     * @throws InterruptedException
     */
    @Test
    public void updateTest() throws InterruptedException {
        Thread.sleep(2000);
        String tag = "UI Test";
        onView(withTagValue(Matchers.is((Object) tag))).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.name)).perform(replaceText("UI Test3"));
        closeSoftKeyboard();
        onView(withId(R.id.number)).perform(replaceText("111111111999"));
        closeSoftKeyboard();
        onView(withId(R.id.primaryBusiness)).perform(replaceText("Fish Monger"));
        closeSoftKeyboard();
        onView(withId(R.id.address)).perform(replaceText("Updating Ave."));
        closeSoftKeyboard();
        onView(withId(R.id.provinceSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("ON"))).perform(click());
        onView(withId(R.id.updateButton)).perform(click());

        Thread.sleep(1000);
        onView(withText("Error: Update failed!")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        onView(withId(R.id.number)).perform(replaceText("111111111"));
        onView(withId(R.id.updateButton)).perform(click());

        Thread.sleep(1000);
        onView(withText("Business entry updated")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        Thread.sleep(1000);
        tag = "UI Test3";
        onView(withTagValue(Matchers.is((Object) tag))).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.name)).check(matches(withText("UI Test3")));
        onView(withId(R.id.number)).check(matches(withText("111111111")));
        onView(withId(R.id.primaryBusiness)).check(matches(withText("Fish Monger")));
        onView(withId(R.id.address)).check(matches(withText("Updating Ave.")));
        onView(withId(R.id.provinceSpinner)).check(matches(withSpinnerText(containsString("ON"))));

        onView(withId(R.id.name)).perform(replaceText("UI Test"));
        setBaseUIFieldValues();
        onView(withId(R.id.updateButton)).perform(click());
    }

    /**
     * Used to populate basic information for test cases.
     */
    private void setBaseUIFieldValues(){
        closeSoftKeyboard();
        onView(withId(R.id.number)).perform(replaceText("123456789"));
        closeSoftKeyboard();
        onView(withId(R.id.primaryBusiness)).perform(replaceText("Processor"));
        closeSoftKeyboard();
        onView(withId(R.id.address)).perform(replaceText("123 Espresso test"));
        closeSoftKeyboard();
        onView(withId(R.id.provinceSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("NS"))).perform(click());
    }
}
