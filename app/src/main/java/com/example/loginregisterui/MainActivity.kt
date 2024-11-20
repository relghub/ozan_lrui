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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val inputEmail = findViewById<TextInputLayout>(R.id.InputEmail);
        val inputPasword = findViewById<TextInputLayout>(R.id.InputPassword);

        val inputEmailText = findViewById<TextInputEditText>(R.id.InputEmailText).text;
        val inputPasswordText = findViewById<TextInputEditText>(R.id.InputPasswordText).text;

        val nextButton = findViewById<Button>(R.id.next_button);
        nextButton.setOnClickListener {

            val isEmailValid = CredentialsManager().isEmailValid(inputEmailText.toString());
            val isPasswordValid = CredentialsManager().isPasswordValid(inputPasswordText.toString());

            if(!isEmailValid) {
                inputEmail.isErrorEnabled = true;
                inputEmail.error = "Email is invalid!";
            }else{
                inputEmail.isErrorEnabled = false;
            }

            if(!isPasswordValid) {
                inputEmail.isErrorEnabled = true;
                inputPasword.error = "Password is invalid!";
            }else{
                inputEmail.isErrorEnabled = false;
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