package com.example.loginregisterui

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class CredentialsManagerTest {


    @Test
    fun givenEmptyEmail_thenReturnFalse() {
        val credentialsManager = CredentialsManager()

        val isEmailValid = credentialsManager.isEmailValid("") // Boş bir e-posta kontrolü

        assertEquals(false, isEmailValid) // Geçerli olmadığını doğrula
    }

    @Test
    fun givenInvalidEmailFormat_thenReturnFalse() {
        val credentialsManager = CredentialsManager()

        val isEmailValid = credentialsManager.isEmailValid("invalid-email") // Yanlış formatta e-posta

        assertEquals(false, isEmailValid) // Geçerli olmadığını doğrula
    }

    @Test
    fun givenValidEmailFormat_thenReturnTrue() {
        val credentialsManager = CredentialsManager()

        val isEmailValid = credentialsManager.isEmailValid("example@test.com")

        assertEquals(true, isEmailValid) // Geçerli olduğunu doğrula
    }

    @Test
    fun givenEmptyPassword_thenReturnFalse() {
        val credentialsManager = CredentialsManager()

        val isPasswordValid = credentialsManager.isPasswordValid("")

        assertEquals(false, isPasswordValid)
    }

    @Test
    fun givenFilledPassword_thenReturnTrue() {
        val credentialsManager = CredentialsManager()

        val isPasswordValid = credentialsManager.isPasswordValid("securePassword123")

        assertEquals(true, isPasswordValid)
    }

    @Test
    fun givenProperCredentials_whenUserRegisters_thenCreateAccount() {
        val email = "another@te.st"
        val password = "12234"

        val credentialsManager = CredentialsManager()

        credentialsManager.register("John", email,  "600 600 000", password)

        val isLoginSuccess = credentialsManager.login(email, password)

        assertEquals(true, isLoginSuccess)
    }

    // TODO: Given used email, when user registers, then return error
    @Test
    fun givenUsedEmail_whenUserRegisters_thenReturnError() {
        val email = "test@te.st"
        val fullName = "Wide Q"
        val password = "C4S4N0SSTR4"
        val number = "100 200 300"

        val credentialsManager = CredentialsManager()

        val isRegisterSuccess = credentialsManager.register(fullName, email, number, password)

        assertEquals(false, isRegisterSuccess)

    }
    // TODO: Given used email with different casing, =||=, =||=


    // TODO: =||=, when user logins, then return success

}


