package com.example.campus360

import org.json.JSONArray

data class Room(
    val id: String,
    val name: String,
    val building: String,
    val floor: Int,
    val type: String,
    val description: String
) {
    companion object {
        fun fromJsonArray(array: JSONArray): List<Room> {
            val rooms = mutableListOf<Room>()
            for (i in 0 until array.length()) {
                val obj = array.getJSONObject(i)
                rooms.add(
                    Room(
                        id = obj.getString("id"),
                        name = obj.getString("name"),
                        building = obj.getString("building"),
                        floor = obj.getInt("floor"),
                        type = obj.getString("type"),
                        description = obj.optString("description", "")
                    )
                )
            }
            return rooms
        }
    }
}