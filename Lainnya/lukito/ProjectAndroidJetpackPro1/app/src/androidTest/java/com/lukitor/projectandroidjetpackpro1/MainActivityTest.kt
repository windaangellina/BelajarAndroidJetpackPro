package com.lukitor.projectandroidjetpackpro1

import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.lukitor.projectandroidjetpackpro1.`class`.Movie
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest{
    private lateinit var viewModel: DataViewModel
    //private val dataTvShow = viewModel.getData("TV Show")
    private lateinit var dataAll: ObservableArrayList<Movie>

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        viewModel = DataViewModel()
        dataAll = viewModel.getData("All")
    }

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testMovie() {
        Espresso.onView(withId(R.id.rvFollowing)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rvFollowing)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataAll.size))
    }

    @Test
    fun testDetailMovie() {
        Espresso.onView(withId(R.id.rvFollowing)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2,MyViewAction.clickOnViewChild(R.id.gambarcard)))
        Espresso.onView(withId(R.id.txtDJudul)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtDJudul)).check(ViewAssertions.matches(ViewMatchers.withText(dataAll[2].judul)))
        Espresso.onView(withId(R.id.txtdeskripsi)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtdeskripsi)).check(ViewAssertions.matches(ViewMatchers.withText(dataAll[2].description)))
        Espresso.onView(withId(R.id.txtRating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtRating)).check(ViewAssertions.matches(ViewMatchers.withText(dataAll[2].rating)))
        Espresso.onView(withId(R.id.txtGenre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.txtGenre)).check(ViewAssertions.matches(ViewMatchers.withText(dataAll[2].genre)))
    }
}