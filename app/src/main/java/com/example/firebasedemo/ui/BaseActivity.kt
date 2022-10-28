package com.example.firebasedemo.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import com.example.firebasedemo.R

/**
 * Created by Eldor Turgunov.
 * Firebase demo
 * eldorturgunov777@gmail.com
 */
open class BaseActivity : AppCompatActivity() {
    var progress: AppCompatDialog? = null
    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
    }

    fun showLoading(activity: Activity?) {
        if (activity == null) return

        if (progress != null && progress!!.isShowing) {

        } else {
//            progress= AppCompatDialog(activity, R.style.CustomDialog)
        }
    }

    fun dismissLoading() {
        if (progress != null && progress!!.isShowing) {
            progress!!.dismiss()
        }
    }

    fun callSignInActivity(context: Context?) {
        val intent = Intent(context, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun callSignUpActivity(context: Context?) {
        val intent = Intent(context, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun callMainActivity(context: Context?) {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}