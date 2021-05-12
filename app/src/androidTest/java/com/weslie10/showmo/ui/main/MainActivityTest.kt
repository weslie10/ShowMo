package com.weslie10.showmo.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.weslie10.showmo.R
import com.weslie10.showmo.utils.DataDummy
import com.weslie10.showmo.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {

    private val dummyMovie = DataDummy.moviePopular.results
    private val dummyTvShow = DataDummy.tvShowPopular.results

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun a_loadMovie() {
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size - 1))
    }

    @Test
    fun b_loadDetailMovie() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.detail_movie_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_score)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_date)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_tag_line)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_overview)).check(matches(isDisplayed()))
        onView(isRoot()).perform(swipeUp())
        onView(withId(R.id.detail_movie_original_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_language)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_homepage)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_budget)).check(matches(isEnabled()))
        onView(withId(R.id.detail_movie_revenue)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_movie_status)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_favorite)).check(matches(isDisplayed())).perform(click())
    }

    @Test
    fun c_loadTvShow() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShow.size - 1))
    }

    @Test
    fun d_loadDetailTvShow() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.detail_tv_show_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_show_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_show_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_show_score)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_show_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_show_date)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_show_tag_line)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_show_overview)).check(matches(isDisplayed()))
        onView(isRoot()).perform(swipeUp())
        onView(withId(R.id.detail_tv_show_original_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_show_language)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_show_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_show_homepage)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_show_last_date)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_show_season)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_show_status)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_tv_show_type)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_favorite)).check(matches(isDisplayed())).perform(click())
    }

    @Test
    fun e_loadFavoriteMovies() {
        onView(withId(R.id.btn_favorite_menu)).perform(click())
        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.btn_favorite)).perform(click())
        pressBack()
        onView(withId(R.id.movie_not_found)).check(matches(isDisplayed()))
    }

    @Test
    fun f_loadFavoriteTvShow() {
        onView(withId(R.id.btn_favorite_menu)).perform(click())
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.btn_favorite)).perform(click())
        pressBack()
        onView(withId(R.id.tvshow_not_found)).check(matches(isDisplayed()))
    }

    @Test
    fun seeAbout() {
        onView(withId(R.id.btn_about_menu)).perform(click())
        onView(withId(R.id.logo)).check(matches(isDisplayed()))
        onView(withId(R.id.made_by)).check(matches(isDisplayed()))
        onView(withId(R.id.name)).check(matches(isDisplayed()))
        onView(withId(R.id.social_media)).check(matches(isDisplayed()))
        onView(withId(R.id.instagram)).check(matches(isDisplayed()))
        onView(withId(R.id.github)).check(matches(isDisplayed()))
        onView(withId(R.id.linkedIn)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment_about)).perform(swipeUp())
        onView(withId(R.id.feature)).check(matches(isDisplayed()))
        onView(withId(R.id.features)).check(matches(isDisplayed()))
        onView(withId(R.id.dependency)).check(matches(isDisplayed()))
        onView(withId(R.id.dependencies)).check(matches(isDisplayed()))
    }
}