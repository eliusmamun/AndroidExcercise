package com.example.wiproassignment

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.wiproassignment.model.Facts
import com.example.wiproassignment.model.ListDataService
import com.example.wiproassignment.model.Rows
import com.example.wiproassignment.utils.EspressoIdlingResource
import com.example.wiproassignment.view.ListDataAdapter
import com.example.wiproassignment.view.MainActivity
import com.example.wiproassignment.viewmodel.ListDataViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.hamcrest.Matchers.*
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Before
    fun registeringIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }


    @After
    fun unregisteringIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }



    @Test
    fun onLaunchSuccessfullyListData() {

        activityRule.activity.viewModelStore

        onView(withId(R.id.factsList))
            .check(matches(isDisplayed()))

        onView(withId(R.id.list_error))
            .check(matches(not(isDisplayed())))

        onView(withId(R.id.loading_view))
            .check(matches(not(isDisplayed())))

    }


    @Test
    fun testRecyclerViewScroll() {
        val recyclerView: RecyclerView = activityRule.activity.findViewById(R.id.factsList)
        val itemCount: Int? = recyclerView.adapter?.itemCount

        onView(withId(R.id.factsList))
            .perform(
                RecyclerViewActions.scrollToPosition<ListDataAdapter.FactsViewHolder>(
                    itemCount!!.minus(
                        1
                    )
                )
            )
    }
    

    @Test
    fun onLaunchWithError() {
        Thread.sleep(2000L)
        CoroutineScope(Dispatchers.Main).launch {
            activityRule.activity.viewModel.factsLoadError.value = "Error"
            activityRule.activity.viewModel.loading.value = false

        }
        onView(withId(R.id.list_error))
            .check(matches(isDisplayed()))
        onView(withId(R.id.loading_view))
            .check(matches(not(isDisplayed())))


    }

}