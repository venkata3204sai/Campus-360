package com.example.campus360

import java.util.ArrayDeque

object PathFinder {

    fun findPath(
        graph: Map<String, List<String>>,
        startId: String,
        destId: String
    ): List<String> {

        if (startId == destId) return listOf(startId)

        val visited = mutableSetOf<String>()
        val queue: ArrayDeque<List<String>> = ArrayDeque()

        queue.add(listOf(startId))

        while (queue.isNotEmpty()) {
            val path = queue.removeFirst()
            val current = path.last()

            if (current == destId) return path

            if (current !in visited) {
                visited.add(current)
                val neighbors = graph[current] ?: emptyList()
                for (n in neighbors) {
                    if (n !in visited) {
                        queue.add(path + n)
                    }
                }
            }
        }
        return emptyList()
    }
}