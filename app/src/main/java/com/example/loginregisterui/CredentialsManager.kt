package com.example.loginregisterui

import kotlin.text.equals

object CredentialsManager {

    private val credentialsMap = mutableMapOf((Pair("test@te.st", "1234")))

    fun isEmailValid(email: String): Boolean {
        if (email.isEmpty()) return false
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        return emailRegex.matches(email)
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun isFullNameValid(fullName: String): Boolean {
        return fullName.isNotEmpty()
    }

    fun isPhoneNumberValid(phoneNumber: String): Boolean {
        return phoneNumber.isNotEmpty()
    }

    fun login(email: String, password: String): Boolean {
        return credentialsMap[email.lowercase()].equals(password)
    }

    fun register(fullName: String, email: String, phoneNumber: String, password: String): String {
        if (credentialsMap.containsKey(email.lowercase())) {
            return "Email is already taken"
        } else {
            if (isFullNameValid(fullName) &&
                isEmailValid(email) &&
                isPhoneNumberValid(phoneNumber) &&
                isPasswordValid(password)
            ) {
                credentialsMap[email] = password
                return "New account registered successfully"
            }
        }
        return "Registration failure"
    }
}
