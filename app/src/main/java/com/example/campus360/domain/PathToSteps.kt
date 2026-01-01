package com.example.campus360.domain

import com.example.campus360.data.model.Room

fun pathToSteps(
    path: List<String>,
    roomsById: Map<String, Room>
): List<String> {

    if (path.isEmpty()) {
        return listOf("No route found between the selected locations.")
    }

    val steps = mutableListOf<String>()

    for (i in 0 until path.size - 1) {
        val current = path[i]
        val next = path[i + 1]

        when {
            next.startsWith("STAIRS") -> {
                if (current.startsWith("STAIRS")) {
                    continue
                }
                else {
                    steps.add("Take the stairs.")
                }
            }
            next.startsWith("PASSAGE") -> {
                steps.add("Proceed through the connecting passage.")
            }
            roomsById.containsKey(next) -> {
                val room = roomsById[next]!!
                steps.add("Go to ${room.name} (Building ${room.building}, Floor ${room.floor}).")
            }
            else -> {
                steps.add("Move to $next.")
            }
        }
    }

    steps.add("You have arrived at ${path.last()}.")

    return steps
}