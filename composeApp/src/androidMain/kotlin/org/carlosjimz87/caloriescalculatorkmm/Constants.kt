package org.carlosjimz87.caloriescalculatorkmm

import org.carlosjimz87.caloriescalculatorkmm.models.CaloriesSection
import org.carlosjimz87.caloriescalculatorkmm.theme.Blue
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
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
        CaloriesSection("Breakfast", "Oatmeal with fruits and nuts", 450, Green.lighten(0.2f)),
        CaloriesSection("Lunch", "Chops with potatoes", 384, Yellow.lighten(0.2f)),
        CaloriesSection("Dinner", "No products added yet", 0, Orange.lighten(0.2f)),
        CaloriesSection("Other", "No products added yet", 0, Blue.lighten(0.2f))
    )

    val countryList = mapOf(
        "+1" to "US",
        "+44" to "UK",
        "+91" to "India"
    )
}