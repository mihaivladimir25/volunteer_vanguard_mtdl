package com.example.proiect_voluntariat;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testEditTextsDisplayed() {
        onView(withId(R.id.editTextLoginUsername)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextLoginPassword)).check(matches(isDisplayed()));
    }

    @Test
    public void testLoginButtonDisplayed() {
        onView(withId(R.id.buttonLogin)).check(matches(isDisplayed()));
    }

    @Test
    public void testSuccessfulLogin() {
        onView(withId(R.id.editTextLoginUsername)).perform(typeText("mihai"), closeSoftKeyboard());
        onView(withId(R.id.editTextLoginPassword)).perform(typeText("mihai"), closeSoftKeyboard());

        onView(withId(R.id.buttonLogin)).perform(click());

        onView(withText("Login Success")).check(matches(isDisplayed()));

        ActivityScenario<HomeActivity> homeActivityScenario = ActivityScenario.launch(HomeActivity.class);
        homeActivityScenario.onActivity(activity -> {
        });
    }

    @Test
    public void testInvalidCredentialsLogin() {
        onView(withId(R.id.editTextLoginUsername)).perform(typeText("andrei"), closeSoftKeyboard());
        onView(withId(R.id.editTextLoginPassword)).perform(typeText("invalidPassword"), closeSoftKeyboard());

        onView(withId(R.id.buttonLogin)).perform(click());

        onView(withText("Invalid Username or Password")).check(matches(isDisplayed()));

        onView(withId(R.id.editTextLoginUsername)).check(matches(isDisplayed()));
    }

    @Test
    public void testRegisterTextViewClicked() {
        onView(withId(R.id.textRegister)).perform(click());

        ActivityScenario<RegisterActivity> registerActivityScenario = ActivityScenario.launch(RegisterActivity.class);
        registerActivityScenario.onActivity(activity -> {
        });
    }

    @Test
    public void testForgotTextViewClicked() {
        onView(withId(R.id.textForgot)).perform(click());

        ActivityScenario<ForgotPasswordActivity> forgotPasswordActivityScenario = ActivityScenario.launch(ForgotPasswordActivity.class);
        forgotPasswordActivityScenario.onActivity(activity -> {
        });
    }
}