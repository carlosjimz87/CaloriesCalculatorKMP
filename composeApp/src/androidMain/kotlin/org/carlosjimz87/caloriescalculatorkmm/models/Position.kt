package org.carlosjimz87.caloriescalculatorkmm.models

enum class Position {
    TOP, MIDDLE, BOTTOM;
    companion object {
        fun fromIndex(index: Int, size: Int): Position {
            return when (index) {
                0 -> TOP
                size - 1 -> BOTTOM
                else -> MIDDLE
            }
        }
    }
}