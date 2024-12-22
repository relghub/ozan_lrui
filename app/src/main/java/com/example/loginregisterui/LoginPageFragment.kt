package com.example.loginregisterui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
// import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginPageFragment : Fragment(R.layout.login_page_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputEmail = view.findViewById<TextInputLayout>(R.id.InputEmail)
        val inputPassword = view.findViewById<TextInputLayout>(R.id.InputPassword)

        // TODO - Implement a session retention after exiting the app
        // val rememberMeCheckbox = view.findViewById<CheckBox>(R.id.checkbox_remember)

        val nextButton = view.findViewById<Button>(R.id.login_button)


        nextButton.setOnClickListener {
            val inputEmailText = view.findViewById<TextInputEditText>(R.id.InputEmailText).text.toString()
            val inputPasswordText = view.findViewById<TextInputEditText>(R.id.InputPasswordText).text.toString()

            val isEmailValid = CredentialsManager.isEmailValid(inputEmailText)
            val isPasswordValid = CredentialsManager.isPasswordValid(inputPasswordText)

            if (CredentialsManager.login(inputEmailText, inputPasswordText)) {
                Toast.makeText(requireContext(), "Welcome to the app!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this.context, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            } else {
                if (!isEmailValid) {
                    inputEmail.isErrorEnabled = true
                    inputEmail.error = "Email is invalid!"
                } else {
                    inputEmail.isErrorEnabled = false
                }

                if (!isPasswordValid) {
                    inputEmail.isErrorEnabled = true
                    inputPassword.error = "Password is invalid!"
                } else {
                    inputEmail.isErrorEnabled = false
                }
            }

        }

        val registerNowLabel = view.findViewById<TextView>(R.id.registerNowLabel)
        registerNowLabel.setOnClickListener {
            Log.d("onboarding", "Register now pressed")


            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RegisterPageFragment())
                .addToBackStack(null)
                .commit()

        }
    }
}