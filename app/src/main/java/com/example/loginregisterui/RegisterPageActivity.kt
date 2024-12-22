package com.example.loginregisterui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegisterPageActivity : AppCompatActivity() {

    private val inputFullName: TextInputLayout
        get() = findViewById(R.id.InputFullName)

    private val inputEmail: TextInputLayout
        get() = findViewById(R.id.InputValidEmail)

    private val inputPhoneNumber: TextInputLayout
        get() = findViewById(R.id.InputPhoneNumber)

    private val inputPassword: TextInputLayout
        get() = findViewById(R.id.InputStrongPassword)

    private val inputFullNameText: String
        get() = findViewById<TextInputEditText>(R.id.InputFullNameText).text.toString()

    private val inputEmailText: String
        get() = findViewById<TextInputEditText>(R.id.InputValidEmailText).text.toString()

    private val inputPhoneNumberText: String
        get() = findViewById<TextInputEditText>(R.id.InputPhoneNumberText).text.toString()

    private val inputPasswordText: String
        get() = findViewById<TextInputEditText>(R.id.InputStrongPasswordText).text.toString()

    private val confirmEulaCheckBox : CheckBox
        get() = findViewById(R.id.checkbox_agreeTerms)

    private val nextButton: Button
        get() = findViewById(R.id.button_createAccount)

    private val isFullNameValid: Boolean
        get() = CredentialsManager().isFullNameValid(inputFullNameText)

    private val isEmailValid: Boolean
        get() = CredentialsManager().isEmailValid(inputEmailText)

    private val isPhoneNumberValid: Boolean
        get() = CredentialsManager().isPhoneNumberValid(inputPhoneNumberText)

    private val isPasswordValid: Boolean
        get() = CredentialsManager().isPasswordValid(inputPasswordText)

    private val credentialsManager = CredentialsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.register_page_activity)

        nextButton.setOnClickListener {
            if (credentialsManager.register(
                    inputFullNameText,
                    inputEmailText,
                    inputPhoneNumberText,
                    inputPasswordText)
                && confirmEulaCheckBox.isChecked)
            {
                val intent = Intent(this, LoginPageActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            else {
                if (!isFullNameValid) {
                    inputFullName.isErrorEnabled = true
                    inputFullName.error = "Full name cannot be blank!"
                } else {
                    inputFullName.isErrorEnabled = false
                }

                if (!isEmailValid) {
                    inputEmail.isErrorEnabled = true
                    inputEmail.error = "Email is invalid!"
                } else {
                    inputEmail.isErrorEnabled = false
                }

                if (!isPhoneNumberValid) {
                    inputPhoneNumber.isErrorEnabled = true
                    inputPhoneNumber.error = "Phone number cannot be blank!"
                } else {
                    inputPhoneNumber.isErrorEnabled = false
                }

                if (!isPasswordValid) {
                    inputPassword.isErrorEnabled = true
                    inputPassword.error = "Password is invalid!"
                } else {
                    inputPassword.isErrorEnabled = false
                }
            }

        }

        val loginLabel = findViewById<TextView>(R.id.loginLabel)
        loginLabel.setOnClickListener {
            Log.d("onboarding", "Login pressed")


            val intent = Intent(this, LoginPageActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)


        }

    }


}