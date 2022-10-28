package com.example.firebasedemo.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.firebasedemo.R
import com.example.firebasedemo.manager.AuthHandler
import com.example.firebasedemo.manager.AuthManager
import com.example.firebasedemo.utils.Extensions.toast

class SignInActivity : BaseActivity() {
    lateinit var et_email: EditText
    lateinit var et_password: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initViews()
    }

    private fun initViews() {
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)
        val btn = findViewById<Button>(R.id.btn_signIn)

        btn.setOnClickListener {
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            firebaseSignIn(email, password)

        }
        val signUp = findViewById<TextView>(R.id.tv_signUp)
        signUp.setOnClickListener {
            callSignUpActivity(this@SignInActivity)
        }
    }

    private fun firebaseSignIn(email: String, password: String) {
        AuthManager.signIn(email, password, object : AuthHandler {
            override fun onSuccess() {
                toast("Successfully")
                callMainActivity(context)
            }

            override fun onError(error: Exception?) {
                toast("Failed")
            }
        })
    }
}