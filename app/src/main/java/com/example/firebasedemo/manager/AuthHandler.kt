package com.example.firebasedemo.manager

/**
 * Created by Eldor Turgunov.
 * Firebase demo
 * eldorturgunov777@gmail.com
 */
interface AuthHandler {
    fun onSuccess()
    fun onError(error: Exception?)
}