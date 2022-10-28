package com.example.firebasedemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.firebasedemo.R
import com.example.firebasedemo.manager.AuthHandler
import com.example.firebasedemo.manager.AuthManager
import com.example.firebasedemo.utils.Extensions.toast

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initViews()
    }

    private fun initViews() {
        val signIn = findViewById<TextView>(R.id.tv_signIn)
        val et_emailReg = findViewById<EditText>(R.id.et_emailReg)
        val et_passwordReg = findViewById<EditText>(R.id.et_passwordReg)
        val btn = findViewById<Button>(R.id.btn_registration)
        btn.setOnClickListener {
            val email = et_emailReg.text.toString().trim()
            val password = et_passwordReg.text.toString().trim()
            firebaseSignUp(email, password)
        }

        signIn.setOnClickListener {
            callSignInActivity(this@SignUpActivity)
        }
    }

    private fun firebaseSignUp(email: String, password: String) {
        AuthManager.signUp(email, password, object : AuthHandler {
            override fun onSuccess() {
                toast("Success")
                callMainActivity(context)
            }

            override fun onError(error: Exception?) {
                toast("Failed")
            }

        })
    }
}