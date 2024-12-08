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
        val credentialsManager = CredentialsManager()

        credentialsManager.register("John", "another@te.st", "600 600 000", "12234")

        val isLoginSuccess = credentialsManager.login("another@te.st", "12234")

        assertTrue(isLoginSuccess)
    }

}


