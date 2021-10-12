package com.winda.couchpotato.ui.favorite

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.winda.couchpotato.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteActivityTest{
    @get:Rule
    var activityRule = ActivityScenarioRule(FavoriteActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(FavoriteActivity::class.java)
    }

    @Test
    fun loadFavorite(){
        onView(withId(R.id.recyclerListShow))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}