package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class asyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule=new ActivityTestRule(MainActivity.class);

    @Test
    public void testJokeTextViewIsVisible(){
        onView(withId(R.id.tell_button)).perform(click());
        onView(withId(R.id.joke_tv)).check(matches(isDisplayed()));
    }


    @Test
    public void testJokeTextViewNotEmpty(){
        GCETask gceTask=new GCETask(null);
        gceTask.execute(InstrumentationRegistry.getTargetContext());
        try {
            String joke=gceTask.get(5,TimeUnit.SECONDS);
            Assert.assertTrue(!joke.equals(""));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }





}
