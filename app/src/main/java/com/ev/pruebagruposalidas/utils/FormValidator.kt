package com.ev.pruebagruposalidas.utils


class FormValidator {
    companion object {
        private val EMAIL_ADDRESS = "[a-zA-Z0-9+._%-]{1,256}@[a-zA-Z0-9][a-zA-Z0-9-]{0,64}(\\.[a-zA-Z0-9][a-zA-Z0-9-]{0,25})+".toRegex()
    }

    fun isEmailValid(email: String): Boolean {
        return EMAIL_ADDRESS.matches(email)
    }

    fun isNameValid(name: String): Boolean {
        val minLength = 3
        return name.length >= minLength
    }

    fun isAgeValid(age: Int): Boolean {
        val minAge = 18
        val maxAge = 99
        return age in minAge..maxAge
    }
}