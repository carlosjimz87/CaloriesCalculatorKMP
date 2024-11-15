package org.carlosjimz87.caloriescalculatorkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform