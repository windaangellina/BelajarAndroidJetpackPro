package com.winda.submissionjetpackpro1.ui.main

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.winda.submissionjetpackpro1.R
import com.winda.submissionjetpackpro1.utils.DataDummy
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CatalogueFragmentTest : TestCase(){

    private val instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
    private val dummyShows = DataDummy.generateDataDummyFromResources(instrumentationContext, 2, true)

    @Test
    fun loadShows(){
        val scenario = launchFragmentInContainer<CatalogueFragment>()
        val lastIndex = dummyShows.size

        onView(withId(R.id.recyclerListShow)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerListShow)).perform(RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(lastIndex))
    }

    @Test
    fun loadShowsDetail(){
        val scenario = launchFragmentInContainer<CatalogueFragment>()
        val show = dummyShows[3]

        assertNotNull(show)

        // simulate scroll and onItemClick
        onView(withId(R.id.recyclerListShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))

        // verify display status
        onView(withId(R.id.tvDetailJudul)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTagline)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailRatings)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailUserScores)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDetailDuration)).check(matches(isDisplayed()))
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))

        // verify values
        onView(withId(R.id.tvDetailJudul)).check(matches(withText(show.getTitleWithReleaseYear())))
        onView(withId(R.id.tvTagline)).check(matches(withText(show.tagLine)))
        onView(withId(R.id.tvDetailRatings)).check(matches(withText(show.ratings)))
        onView(withId(R.id.tvDetailUserScores)).check(matches(withText(show.getUserScoresAsString())))
        onView(withId(R.id.tvReleaseDate)).check(matches(withText(show.getReleaseDateAsString())))
        onView(withId(R.id.tvOverview)).check(matches(withText(show.overview)))
        onView(withId(R.id.tvDetailDuration)).check(matches(withText(show.getDurationAsString())))
    }
}