package com.example.campus360

import com.example.campus360.domain.PathFinder
import org.junit.Assert.assertEquals
import org.junit.Test

class PathFinderTest {
    @Test
    fun `finds correct path in simple graph`() {

        val graph = mapOf(
            "A" to listOf("B"),
            "B" to listOf("A", "C"),
            "C" to listOf("B", "D"),
            "D" to listOf("C")
        )

        val path = PathFinder.findPath(graph, "A", "D")
        val expectedPath = listOf("A", "B", "C", "D")
        assertEquals(expectedPath, path)
    }

    @Test
    fun `returns empty path when no route exists`() {
        val graph = mapOf(
            "A" to emptyList<String>(),
            "B" to emptyList<String>()
        )

        val path = PathFinder.findPath(graph, "A", "B")
        assertEquals(emptyList<String>(), path)
    }
}