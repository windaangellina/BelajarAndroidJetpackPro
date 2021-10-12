/*
* Copyright (C) 2013 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.android.actionbarcompat.basic.tests;

import com.example.android.actionbarcompat.basic.*;

import android.support.test.espresso.Espresso;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static org.hamcrest.Matchers.is;

/**
* Tests for Basic sample.
*/
public class SampleTests extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mTestActivity;

    public SampleTests() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // Starts the activity under test using the default Intent with:
        // action = {@link Intent#ACTION_MAIN}
        // flags = {@link Intent#FLAG_ACTIVITY_NEW_TASK}
        // All other fields are null or empty.
        mTestActivity = getActivity();
    }

    /**
    * Test if the test fixture has been set up correctly.
    */
    public void testPreconditions() {
        //Try to add a message to add context to your assertions. These messages will be shown if
        //a tests fails and make it easy to understand why a test failed
        assertNotNull("mTestActivity is null", mTestActivity);
    }

    /**
    * Add more tests below.
    */

    public void testTabSelectionOnViewId(){

        Espresso.onView(withId(R.id.tab_id_1)).perform(click());
        Espresso.onView(withId(R.id.tab_id_2)).perform(click());
        Espresso.onView(withId(R.id.tab_id_3)).perform(click());

    }

    public void testTabSelectionOnViewTag(){

        Espresso.onView(withTagValue(is((Object) "tab1"))).perform(click());
        Espresso.onView(withTagValue(is((Object) "tab2"))).perform(click());
        Espresso.onView(withTagValue(is((Object) "tab3"))).perform(click());
    }

}
