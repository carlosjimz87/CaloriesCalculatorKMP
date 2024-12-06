package org.carlosjimz87.caloriescalculatorkmm

import org.carlosjimz87.caloriescalculatorkmm.models.CaloriesSection
import org.carlosjimz87.caloriescalculatorkmm.theme.Blue
import org.carlosjimz87.caloriescalculatorkmm.theme.Green
import org.carlosjimz87.caloriescalculatorkmm.theme.Orange
import org.carlosjimz87.caloriescalculatorkmm.theme.Yellow

object Constants {
    val caloriesList = listOf(
        "Fats" to "31g",
        "Carb." to "66g",
        "Proteins" to "32g",
        "Calories" to "900kcal"
    )

    val meals = listOf(
        CaloriesSection("Breakfast", "Oatmeal with fruits and nuts", 450, Green),
        CaloriesSection("Lunch", "Chops with potatoes", 384, Yellow),
        CaloriesSection("Dinner", "No products added yet", 0, Orange),
        CaloriesSection("Other", "No products added yet", 0, Blue)
    )

    val countryList = mapOf(
        "+1" to "US",
        "+44" to "UK",
        "+91" to "India"
    )
}