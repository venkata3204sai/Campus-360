package com.example.campus360

fun filterRooms(rooms: List<Room>, query: String): List<Room> {
    return rooms.filter {
        it.name.lowercase().contains(query.lowercase()) ||
                it.id.lowercase().contains(query.lowercase())
    }
}