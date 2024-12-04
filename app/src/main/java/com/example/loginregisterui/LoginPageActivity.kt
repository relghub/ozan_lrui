package com.example.loginregisterui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginPageActivity : AppCompatActivity() {
    private val inputEmail: TextInputLayout
        get() = findViewById<TextInputLayout>(R.id.InputEmail)

    private val inputPassword: TextInputLayout
        get() = findViewById<TextInputLayout>(R.id.InputPasswordText)

    private val inputEmailText: String
        get() = findViewById<TextInputEditText>(R.id.InputEmailText).text.toString()

    private val inputPasswordText: String
        get() = findViewById<TextInputEditText>(R.id.InputPasswordText).text.toString()

    private val nextButton: Button
        get() = findViewById<Button>(R.id.next_button)

    private val isEmailValid: Boolean
        get() = CredentialsManager().isEmailValid(inputEmailText.toString())

    private val isPasswordValid: Boolean
        get() = CredentialsManager().isPasswordValid(inputPasswordText.toString())

    val credentialsManager = CredentialsManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nextButton.setOnClickListener {
            if (credentialsManager.login(inputEmailText, inputPasswordText)) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(intent)
            }
            else {
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

        val registerNowLabel = findViewById<TextView>(R.id.registerNowLabel)
        registerNowLabel.setOnClickListener {
            Log.d("onboarding", "Register now pressed")


            val intent = Intent(this, RegisterPageActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)

        }

    }
}