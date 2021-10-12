package com.gracielo.jetpacksubmission3v2

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.gracielo.jetpacksubmission3v2.Utils.DataDummyFilm
import com.gracielo.jetpacksubmission3v2.Utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

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

    private val dummyMovies = DataDummyFilm.generateMovies()
    private val dummyTV = DataDummyFilm.generateTV()


    @Test
    fun loadMovies() {
        delayTwoSecond()
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovies.size
            )
        )
    }

    @Test
    fun loadDetMovies() {
        delayTwoSecond()
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                MyViewAction.clickOnViewChild(R.id.gambarFilm)
            )
        )
        onView(withId(R.id.txtJudulDet)).check(matches(isDisplayed()))
        onView(withId(R.id.txtJudulDet)).check(matches(withText(dummyMovies[2].judul)))
        onView(withId(R.id.txtDescDet)).check(matches(isDisplayed()))
        onView(withId(R.id.txtDescDet)).check(matches(withText(dummyMovies[2].desc)))
        onView(withId(R.id.txtTahunDet)).check(matches(isDisplayed()))
        onView(withId(R.id.txtTahunDet)).check(matches(withText(dummyMovies[2].tahun)))
    }

    @Test
    fun loadTV() {
        onView(withText("TV Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTV.size
            )
        )
    }

    @Test
    fun loadDetTV() {
        onView(withText("TV Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                MyViewAction.clickOnViewChild(R.id.gambarFilm)
            )
        )
        onView(withId(R.id.txtJudulDet)).check(matches(isDisplayed()))
        onView(withId(R.id.txtJudulDet)).check(matches(withText(dummyTV[1].judul)))
        onView(withId(R.id.txtDescDet)).check(matches(isDisplayed()))
        onView(withId(R.id.txtDescDet)).check(matches(withText(dummyTV[1].desc)))
        onView(withId(R.id.txtTahunDet)).check(matches(isDisplayed()))
        onView(withId(R.id.txtTahunDet)).check(matches(withText(dummyTV[1].tahun)))
    }

    @Test
    fun AddAndUpdateFavMovie(){
        delayTwoSecond()
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                2,
                MyViewAction.clickOnViewChild(R.id.gambarFilm)
            )
        )
        onView(withId(R.id.fab_favorite)).perform(click())
        pressBack()

        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                MyViewAction.clickOnViewChild(R.id.gambarFilm)
            )
        )
        onView(withId(R.id.fab_favorite)).perform(click())
        pressBack()

        onView(withId(R.id.item_fav)).perform(click())

        delayTwoSecond()
        onView(withId(R.id.rv_moviesFav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                MyViewAction.clickOnViewChild(R.id.gambarFilm)
            )
        )
        onView(withId(R.id.fab_favorite)).perform(click())
    }

    @Test
    fun AddAndUpdateFavTV(){
        onView(withText("TV Shows")).perform(click())
        delayTwoSecond()
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                MyViewAction.clickOnViewChild(R.id.gambarFilm)
            )
        )
        onView(withId(R.id.fab_favorite)).perform(click())
        pressBack()

        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                MyViewAction.clickOnViewChild(R.id.gambarFilm)
            )
        )
        onView(withId(R.id.fab_favorite)).perform(click())
        pressBack()


        onView(withId(R.id.item_fav)).perform(click())
        delayTwoSecond()
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvFav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                MyViewAction.clickOnViewChild(R.id.gambarFilm)
            )
        )
        onView(withId(R.id.fab_favorite)).perform(click())
    }




    private fun delayTwoSecond() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}