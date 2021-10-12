package com.winda.couchpotato.ui.catalogue

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
import com.winda.couchpotato.R
import com.winda.couchpotato.utils.DataDummy
import com.winda.couchpotato.utils.test.EspressoIdlingResource
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CatalogueActivityTest{
    private val dummyTvShows = DataDummy.generateTvShowsDummyArrayList()
    private val dummyMovies = DataDummy.generateMoviesDummyArrayList()

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
        onView(withId(R.id.tvDetailTitle)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.imgPoster)).check(ViewAssertions.matches(isDisplayed()))

        // verify values
        onView(withId(R.id.tvDetailTitle)).check(ViewAssertions.matches(withText(show.getTitleWithReleaseYear())))
        onView(withId(R.id.tvReleaseDate)).check(ViewAssertions.matches(withText(show.getReleaseDateAsString())))
        onView(withId(R.id.tvOverview)).check(ViewAssertions.matches(withText(show.overview)))
    }

    @Test
    fun loadTvShows(){
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
        onView(withId(R.id.tvDetailTitle)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.imgPoster)).check(ViewAssertions.matches(isDisplayed()))

        // verify values
        onView(withId(R.id.tvDetailTitle)).check(ViewAssertions.matches(withText(show.getTitleWithReleaseYear())))
        onView(withId(R.id.tvReleaseDate)).check(ViewAssertions.matches(withText(show.getReleaseDateAsString())))
        onView(withId(R.id.tvOverview)).check(ViewAssertions.matches(withText(show.overview)))
    }

    @Test
    fun testChangeFavoriteMovies(){
        onView(withText("MOVIES")).perform(click())

        // simulate clicking search icon and typing on SearchView
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java))
            .perform(typeText("avengers"))
            .perform(pressKey(KeyEvent.KEYCODE_ENTER))

        // simulate click item
        onView(allOf(withId(R.id.recyclerListShow), isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            ))

        // add to favorite
        onView(withId(R.id.floatingButtonFavorite)).perform(click())

        // remove from favorite
        onView(withId(R.id.floatingButtonFavorite)).perform(click())
    }


    @Test
    fun testChangeFavoriteTvShows(){
        onView(withText("TV SHOWS")).perform(click())

        // simulate clicking search icon and typing on SearchView
        onView(withId(R.id.search)).perform(click())
        onView(isAssignableFrom(AutoCompleteTextView::class.java))
            .perform(typeText("vincenzo"))
            .perform(pressKey(KeyEvent.KEYCODE_ENTER))

        // simulate click item
        onView(allOf(withId(R.id.recyclerListShow), isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            ))

        // add to favorite
        onView(withId(R.id.floatingButtonFavorite)).perform(click())

        // remove from favorite
        onView(withId(R.id.floatingButtonFavorite)).perform(click())
    }

}