package com.winda.couchpotato

import android.view.KeyEvent
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.winda.couchpotato.ui.catalogue.CatalogueActivity
import com.winda.couchpotato.utils.DataDummy
import com.winda.couchpotato.utils.test.EspressoIdlingResource
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

class CatalogueActivityTest{
    private val dummyTvShows = DataDummy.generateTvShowsDummyList()
    private val dummyMovies = DataDummy.generateMoviesDummyList()

    @get:Rule
    var activityRule = ActivityScenarioRule(CatalogueActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(CatalogueActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies(){
        delayProcess()
        onView(withText("MOVIES")).perform(click())

        // simulate clicking search icon and typing on SearchView
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java))
                .perform(typeText("avengers endgame"))
                .perform(pressKey(KeyEvent.KEYCODE_ENTER))

        onView(allOf(withId(R.id.recyclerListShow), isDisplayed()))
                .check(ViewAssertions.matches(isDisplayed()))
        onView(allOf(withId(R.id.recyclerListShow), isDisplayed())).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        dummyMovies.size
                )
        )
    }

    @Test
    fun loadDetailMovies(){
        delayProcess()
        onView(withText("MOVIES")).perform(click())

        // simulate clicking search icon and typing on SearchView
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java))
                .perform(typeText("avengers endgame"))
                .perform(pressKey(KeyEvent.KEYCODE_ENTER))

        // simulate click item
        val show = dummyMovies[0]
        onView(allOf(withId(R.id.recyclerListShow), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0, click()
                ))

        // verify display status
        onView(withId(R.id.tvDetailJudul)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvDetailUserScores)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.imgPoster)).check(ViewAssertions.matches(isDisplayed()))

        // verify values
        onView(withId(R.id.tvDetailJudul)).check(ViewAssertions.matches(withText(show.getTitleWithReleaseYear())))
        onView(withId(R.id.tvDetailUserScores)).check(ViewAssertions.matches(withText(show.getUserScoresAsString())))
        onView(withId(R.id.tvReleaseDate)).check(ViewAssertions.matches(withText(show.getReleaseDateAsString())))
        onView(withId(R.id.tvOverview)).check(ViewAssertions.matches(withText(show.overview)))
    }

    @Test
    fun loadTvShows(){
        delayProcess()
        onView(withText("TV SHOWS")).perform(click())

        // simulate clicking search icon and typing on SearchView
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java))
                .perform(typeText("vincenzo"))
                .perform(pressKey(KeyEvent.KEYCODE_ENTER))

        // allOf(withId(R.id.recyclerListShow), isDisplayed())
        // -> there are > 1 views with id recyclerListShow. choose displayed one to be tested
        onView(allOf(withId(R.id.recyclerListShow), isDisplayed()))
                .check(ViewAssertions.matches(isDisplayed()))
        onView(allOf(withId(R.id.recyclerListShow), isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        dummyTvShows.size
                ))
    }

    @Test
    fun loadDetailTvShows(){
        delayProcess()
        onView(withText("TV SHOWS")).perform(click())

        // simulate clicking search icon and typing on SearchView
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java))
                .perform(typeText("vincenzo"))
                .perform(pressKey(KeyEvent.KEYCODE_ENTER))

        // simulate click item
        val show = dummyTvShows[0]
        onView(allOf(withId(R.id.recyclerListShow), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0, click()
                ))

        // verify display status
        onView(withId(R.id.tvDetailJudul)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvDetailUserScores)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.imgPoster)).check(ViewAssertions.matches(isDisplayed()))

        // verify values
        onView(withId(R.id.tvDetailJudul)).check(ViewAssertions.matches(withText(show.getTitleWithReleaseYear())))
        onView(withId(R.id.tvDetailUserScores)).check(ViewAssertions.matches(withText(show.getUserScoresAsString())))
        onView(withId(R.id.tvReleaseDate)).check(ViewAssertions.matches(withText(show.getReleaseDateAsString())))
        onView(withId(R.id.tvOverview)).check(ViewAssertions.matches(withText(show.overview)))
    }

    private fun delayProcess() {
        try {
            Thread.sleep(SERVICE_LATENCY_IN_MILLIS)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}