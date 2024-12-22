package com.example.loginregisterui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class RegisterPageFragment : Fragment(R.layout.register_page_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputFullName = view.findViewById<TextInputLayout>(R.id.InputFullName)
        val inputEmail = view.findViewById<TextInputLayout>(R.id.InputValidEmail)
        val inputPhoneNumber = view.findViewById<TextInputLayout>(R.id.InputPhoneNumber)
        val inputPassword = view.findViewById<TextInputLayout>(R.id.InputStrongPassword)

        val inputFullNameText =
            view.findViewById<TextInputEditText>(R.id.InputFullNameText).text.toString()
        val inputEmailText =
            view.findViewById<TextInputEditText>(R.id.InputValidEmailText).text.toString()
        val inputPhoneNumberText =
            view.findViewById<TextInputEditText>(R.id.InputPhoneNumberText).text.toString()
        val inputPasswordText =
            view.findViewById<TextInputEditText>(R.id.InputStrongPasswordText).text.toString()

        val confirmEulaCheckBox = view.findViewById<CheckBox>(R.id.checkbox_agreeTerms)
        val nextButton = view.findViewById<Button>(R.id.button_createAccount)

        val isFullNameValid = CredentialsManager().isFullNameValid(inputFullNameText)
        val isEmailValid = CredentialsManager().isEmailValid(inputEmailText)
        val isPhoneNumberValid = CredentialsManager().isPhoneNumberValid(inputPhoneNumberText)
        val isPasswordValid = CredentialsManager().isPasswordValid(inputPasswordText)

        val credentialsManager = CredentialsManager()

        nextButton.setOnClickListener {
            if (credentialsManager.register(
                    inputFullNameText,
                    inputEmailText,
                    inputPhoneNumberText,
                    inputPasswordText
                )
                && confirmEulaCheckBox.isChecked
            ) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, LoginPageFragment())
                    .addToBackStack(null)
                    .commit()
            } else {
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

        val loginLabel = view.findViewById<TextView>(R.id.loginLabel)
        loginLabel.setOnClickListener {
            Log.d("onboarding", "Login pressed")

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginPageFragment())
                .addToBackStack(null)
                .commit()
        }

    }

}