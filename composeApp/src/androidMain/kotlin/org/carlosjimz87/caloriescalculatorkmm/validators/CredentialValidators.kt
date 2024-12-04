package org.carlosjimz87.caloriescalculatorkmm.validators

fun validateEmail(email: String): String? {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex()
    return when {
        email.isBlank() -> null
        !email.matches(emailRegex) -> "Invalid email address"
        else -> null // No error
    }
}

fun validatePassword(password: String): String? {
    // Regex explanation:
    // (?=.*[0-9])       -> At least one digit
    // (?=.*[a-z])       -> At least one lowercase letter
    // (?=.*[A-Z])       -> At least one uppercase letter
    // (?=.*[@#*$%^&+=])  -> At least one special character
    // .{6,12}           -> Minimum 6, maximum 12 characters
    val passwordRegex = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#*$%^&+=]).{6,12}$""".toRegex()

    return when {
        password.isBlank() -> null // Allow blank for optional field
        !password.matches(passwordRegex) ->
            "Password must be 6-10 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character."
        else -> null // No error
    }
}