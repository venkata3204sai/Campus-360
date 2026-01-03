package com.example.campus360

import com.example.campus360.domain.TimeEstimator
import org.junit.Assert.assertEquals
import org.junit.Test

class TimeEstimatorTest {

    @Test
    fun `empty route returns zero time`() {
        val route = emptyList<String>()

        val time = TimeEstimator.estimateTime(route)

        assertEquals(0, time)
    }

    @Test
    fun `single step route returns zero time`() {
        val route = listOf("G210")

        val time = TimeEstimator.estimateTime(route)

        assertEquals(0, time)
    }

    @Test
    fun `route with corridor stairs and passage returns correct time`() {
        val route = listOf(
            "H320",
            "PASSAGE_G_H",
            "G213C",
            "G212C",
            "G212A",
            "G211",
            "G210"
        )

        val time = TimeEstimator.estimateTime(route)

        // Expected:
        // STAIRS = 10
        // CORRIDOR = 5
        // PASSAGE = 15
        // TOTAL = 40 seconds
        assertEquals(40, time)
    }
}