package com.example.loginregisterui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class RegisterPageFragment : Fragment(R.layout.register_page_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputFullName = view.findViewById<TextInputLayout>(R.id.InputFullName)
        val inputEmail = view.findViewById<TextInputLayout>(R.id.InputValidEmail)
        val inputPhoneNumber = view.findViewById<TextInputLayout>(R.id.InputPhoneNumber)
        val inputPassword = view.findViewById<TextInputLayout>(R.id.InputStrongPassword)

        val nextButton = view.findViewById<Button>(R.id.button_createAccount)

        nextButton.setOnClickListener {
            val inputFullNameText =
                view.findViewById<TextInputEditText>(R.id.InputFullNameText).text.toString()
            val inputEmailText =
                view.findViewById<TextInputEditText>(R.id.InputValidEmailText).text.toString()
            val inputPhoneNumberText =
                view.findViewById<TextInputEditText>(R.id.InputPhoneNumberText).text.toString()
            val inputPasswordText =
                view.findViewById<TextInputEditText>(R.id.InputStrongPasswordText).text.toString()

            val confirmEulaCheckBox = view.findViewById<CheckBox>(R.id.checkbox_agreeTerms)

            val isFullNameValid = CredentialsManager.isFullNameValid(inputFullNameText)
            val isEmailValid = CredentialsManager.isEmailValid(inputEmailText)
            val isPhoneNumberValid = CredentialsManager.isPhoneNumberValid(inputPhoneNumberText)
            val isPasswordValid = CredentialsManager.isPasswordValid(inputPasswordText)

            if (CredentialsManager.register(
                    inputFullNameText,
                    inputEmailText,
                    inputPhoneNumberText,
                    inputPasswordText
                ) == "New account registered successfully"
                && confirmEulaCheckBox.isChecked
            ) {
                Log.d("CredentialsManager","Successful registration")
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, LoginPageFragment())
                    .addToBackStack(null)
                    .commit()
            } else {
                if (!isFullNameValid) {
                    inputFullName.isErrorEnabled = true
                    inputFullName.error = "Full name cannot be blank!"
                    Log.d("CredentialsManager", "Invalid name entry")
                } else {
                    inputFullName.isErrorEnabled = false
                }

                if (!isEmailValid) {
                    inputEmail.isErrorEnabled = true
                    inputEmail.error = "Email is invalid!"
                    Log.d("CredentialsManager", "Invalid email entry")
                } else {
                    inputEmail.isErrorEnabled = false
                }

                if (!isPhoneNumberValid) {
                    inputPhoneNumber.isErrorEnabled = true
                    inputPhoneNumber.error = "Phone number cannot be blank!"
                    Log.d("CredentialsManager", "Invalid phone number entry")
                } else {
                    inputPhoneNumber.isErrorEnabled = false
                }

                if (!isPasswordValid) {
                    inputPassword.isErrorEnabled = true
                    inputPassword.error = "Password is invalid!"
                    Log.d("CredentialsManager", "Invalid password entry")
                } else {
                    inputPassword.isErrorEnabled = false
                }

                if (!confirmEulaCheckBox.isChecked) {
                    Toast.makeText(requireContext(), "Accept the EULA to proceed", Toast.LENGTH_SHORT).show()
                    Log.d("RegisterPageFragment", "Confirm EULA checkbox is not checked")
                }

            }

        }

        val loginLabel = view.findViewById<TextView>(R.id.loginLabel)
        loginLabel.setOnClickListener {
            Log.d("onboarding", "Login pressed")

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginPageFragment())
                .commit()
        }

    }

}