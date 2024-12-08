package com.example.loginregisterui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private var isFragmentADisplayed = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentA())
                .commit()
        }

        val toggleButton: Button = findViewById(R.id.toggle_fragment_button)
        toggleButton.setOnClickListener {
            toggleFragment()
        }
    }

    private fun toggleFragment() {
        val fragmentToShow: Fragment = if (isFragmentADisplayed) {
            fragmentB()
        } else {
            fragmentA()
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragmentToShow)
            .commit()

        isFragmentADisplayed = !isFragmentADisplayed
    }
}