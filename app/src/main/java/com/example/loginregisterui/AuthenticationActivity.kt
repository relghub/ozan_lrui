package com.example.loginregisterui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class AuthenticationActivity : AppCompatActivity() {
    private var isLoginFragmentDisplayed = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authentication_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LoginPageFragment())
                .commit()
        }

        val toggleButton: Button = findViewById(R.id.toggle_fragment_button)
        toggleButton.setOnClickListener {
            toggleFragment()
        }
    }

    private fun toggleFragment() {
        val fragmentToShow: Fragment = if (isLoginFragmentDisplayed) {
            RegisterPageFragment()
        } else {
            LoginPageFragment()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragmentToShow)
            .commit()

        isLoginFragmentDisplayed = !isLoginFragmentDisplayed
    }
}