package com.example.campus360

fun generateDirections(room: Room): List<String> {
    return listOf(
        "Enter Building ${room.building}.",
        "Go to Floor ${room.floor}.",
        "Follow the corridor.",
        "Locate ${room.name}.",
        "Destination reached."
    )
}