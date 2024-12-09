package org.carlosjimz87.caloriescalculatorkmm.navigation


enum class Screen {
    LOGIN,
    REGISTER,
    DASHBOARD,
}

sealed class NavDestinations(val route: String) {
    data object Login : NavDestinations(Screen.LOGIN.name)
    data object Register : NavDestinations(Screen.REGISTER.name)
    data object Dashboard: NavDestinations(Screen.DASHBOARD.name)

    companion object{
        fun values() = listOf(Login, Register, Dashboard)
    }
}