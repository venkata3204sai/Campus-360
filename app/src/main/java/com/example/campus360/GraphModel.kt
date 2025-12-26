package com.example.campus360

import android.content.Context
import org.json.JSONObject

object GraphModel {
    fun loadGraph(context: Context): Map<String, List<String>> {
        val jsonText = context.assets
            .open("graph.json")
            .bufferedReader()
            .use { it.readText() }

        val root = JSONObject(jsonText)
        val edges = root.getJSONArray("edges")

        val graph = mutableMapOf<String, MutableList<String>>()

        for (i in 0 until edges.length()) {
            val edge = edges.getJSONObject(i)
            val from = edge.getString("from")
            val to = edge.getString("to")

            graph.getOrPut(from) { mutableListOf() }.add(to)
            graph.getOrPut(to) { mutableListOf() }.add(from)
        }

        return graph.mapValues { it.value.toList() }
    }
}
