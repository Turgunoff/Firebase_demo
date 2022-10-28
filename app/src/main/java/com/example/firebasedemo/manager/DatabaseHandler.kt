package com.example.firebasedemo.manager

import com.example.firebasedemo.model.Post

/**
 * Created by Eldor Turgunov.
 * Firebase demo
 * eldorturgunov777@gmail.com
 */
interface DatabaseHandler {
    fun onSuccess(post: Post? = null, posts: ArrayList<Post> = ArrayList())
    fun onError()
}