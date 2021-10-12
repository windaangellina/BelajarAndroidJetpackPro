package com.example.submission1.activitycode

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.submission1.R
import com.example.submission1.db.EspressoIdlingResource
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest{
    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies(){
        onView(ViewMatchers.withText("MOVIES")).perform(ViewActions.click())
        onView(allOf(withId(R.id.listFollower), ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(allOf(withId(R.id.listFollower), ViewMatchers.isDisplayed())).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        9
                )
        )
    }

    @Test
    fun loadDetailMovies(){
        onView(ViewMatchers.withText("MOVIES")).perform(ViewActions.click())
        onView(allOf(withId(R.id.listFollower), ViewMatchers.isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0, ViewActions.click()
                ))
        onView(withId(R.id.img_item_photo)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvJudul)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvIsi)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvJudul)).check(ViewAssertions.matches(ViewMatchers.withText("Avengers: Endgame")))
        onView(withId(R.id.tvIsi)).check(ViewAssertions.matches(ViewMatchers.withText("After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos\' actions and restore order to the universe once and for all, no matter what consequences may be in store.")))
    }

    @Test
    fun loadShows(){
        onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        onView(allOf(withId(R.id.listFollower), ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(allOf(withId(R.id.listFollower), ViewMatchers.isDisplayed())).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        9
                )
        )
    }

    @Test
    fun loadDetailShow(){
        onView(ViewMatchers.withText("TV SHOWS")).perform(ViewActions.click())
        onView(allOf(withId(R.id.listFollower), ViewMatchers.isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0, ViewActions.click()
                ))
        onView(withId(R.id.img_item_photo)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvJudul)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvIsi)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvJudul)).check(ViewAssertions.matches(ViewMatchers.withText("Marvel Studios: Legends")))
        onView(withId(R.id.tvIsi)).check(ViewAssertions.matches(ViewMatchers.withText("Revisit the epic heroes, villains and moments from across the MCU in preparation for the stories still to come. Each dynamic segment feeds directly into the upcoming series — setting the stage for future events. This series weaves together the many threads that constitute the unparalleled Marvel Cinematic Universe.")))
    }
}