package org.carlosjimz87.caloriescalculatorkmm

import android.hardware.lights.Light
import org.carlosjimz87.caloriescalculatorkmm.models.CaloriesSection
import org.carlosjimz87.caloriescalculatorkmm.theme.Blue
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.LightBlue
import org.carlosjimz87.caloriescalculatorkmm.theme.LightGreen
import org.carlosjimz87.caloriescalculatorkmm.theme.LightOrange
import org.carlosjimz87.caloriescalculatorkmm.theme.LightYellow
import org.carlosjimz87.caloriescalculatorkmm.theme.Orange
import org.carlosjimz87.caloriescalculatorkmm.theme.Yellow
import org.carlosjimz87.caloriescalculatorkmm.utils.lighten

object Constants {
    val caloriesList = listOf(
        "Fats" to "31g",
        "Carb." to "66g",
        "Proteins" to "32g",
        "Calories" to "900kcal"
    )

    val meals = listOf(
        CaloriesSection("Breakfast", "Oatmeal with fruits and nuts", 450, LightGreen),
        CaloriesSection("Lunch", "Chops with potatoes", 384, LightYellow),
        CaloriesSection("Dinner", "No products added yet", 0, LightOrange),
        CaloriesSection("Other", "No products added yet", 0, LightBlue)
    )

    val countryList = mapOf(
        "+1" to "US",
        "+44" to "UK",
        "+91" to "India"
    )
}