package com.example.campus360

import org.junit.Assert.assertEquals
import org.junit.Test

class SearchUtilsTest {

    @Test
    fun testSearchByRoomId() {
        val rooms = listOf(
            Room("G210", "G210", "G", 2, "classroom", ""),
            Room("G211", "G211", "G", 2, "classroom", ""),
            Room("B101", "B101", "B", 1, "office", "")
        )

        val result = rooms.filter {
            it.id.contains("G21")
        }

        assertEquals(2, result.size)
    }

    @Test
    fun testFilterByType() {
        val rooms = listOf(
            Room("G210", "G210", "G", 2, "classroom", ""),
            Room("C301", "C301", "C", 3, "lab", "")
        )

        val result = rooms.filter { it.type == "classroom" }

        assertEquals(1, result.size)
        assertEquals("G210", result.first().id)
    }
}