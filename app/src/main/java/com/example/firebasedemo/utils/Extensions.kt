package com.example.firebasedemo.utils

import android.app.Activity
import android.widget.Toast

/**
 * Created by Eldor Turgunov.
 * Firebase demo
 * eldorturgunov777@gmail.com
 */
object Extensions {

    fun Activity.toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}