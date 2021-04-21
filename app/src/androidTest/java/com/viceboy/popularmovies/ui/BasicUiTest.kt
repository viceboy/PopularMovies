package com.viceboy.popularmovies.ui

import android.os.SystemClock
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.viceboy.popularmovies.MainActivity
import com.viceboy.popularmovies.R
import com.viceboy.popularmovies.ui.RecyclerViewAssertions.itemViewMatches
import junit.framework.Assert.*
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class BasicUiTest {

    @Rule
    @JvmField
    var mActivityTestRule : ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, true, true)


    @Test
    fun test1() {
        SystemClock.sleep(5000)
        onView(
            withId(R.id.rvMovies)).
        check(itemViewMatches(0, R.id.tvTitle,
            withEffectiveVisibility(Visibility.VISIBLE)))
    }
}

object RecyclerViewAssertions {

    /**
     * Provides a RecyclerView assertion based on a view matcher. This allows you to
     * validate whether a RecyclerView contains a row in memory without scrolling the list.
     *
     * @param viewMatcher - an Espresso ViewMatcher for a descendant of any row in the recycler.
     * @return an Espresso ViewAssertion to check against a RecyclerView.
     */
    fun itemViewMatches(
        position: Int,
        @IdRes resId: Int,
        viewMatcher: Matcher<View>
    ): ViewAssertion {
        assertNotNull(viewMatcher)

        return ViewAssertion { view, noViewException ->
            if (noViewException != null) {
                throw noViewException
            }

            assertTrue("View is RecyclerView", view is RecyclerView)

            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            val itemType = adapter!!.getItemViewType(position)
            val viewHolder = adapter.createViewHolder(recyclerView, itemType)
            adapter.bindViewHolder(viewHolder, position)

            val targetView = if (resId == -1) {
                viewHolder.itemView
            } else {
                viewHolder.itemView.findViewById(resId)
            }

            if (viewMatcher.matches(targetView)) {
                return@ViewAssertion  // Found a matching view
            }

            fail("No match found")
        }
    }
}