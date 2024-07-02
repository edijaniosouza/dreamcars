package com.barrosedijanio.dreamcars.util

class EmailValidator(
    private val email: String
) {

    fun validateEmail(): Boolean{
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return emailRegex.matches(email)
    }
}