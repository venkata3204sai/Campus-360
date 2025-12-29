package com.example.campus360

object TimeEstimator {

    private const val CORRIDOR_TIME_SEC = 5
    private const val STAIRS_TIME_SEC = 10
    private const val PASSAGE_TIME_SEC = 15

    fun estimateTime(route: List<String>): Int {
        if (route.size < 2) return 0

        var totalTime = 0

        for (i in 1 until route.size) {
            val node = route[i]

            totalTime += when {
                node.contains("STAIRS", ignoreCase = true) ->
                    STAIRS_TIME_SEC

                node.contains("PASSAGE", ignoreCase = true) ->
                    PASSAGE_TIME_SEC

                else ->
                    CORRIDOR_TIME_SEC
            }
        }

        return totalTime
    }
}
