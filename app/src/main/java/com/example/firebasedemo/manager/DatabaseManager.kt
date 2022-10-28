package com.example.firebasedemo.manager

import com.example.firebasedemo.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * Created by Eldor Turgunov.
 * Firebase demo
 * eldorturgunov777@gmail.com
 */
object DatabaseManager {

    val database = FirebaseDatabase.getInstance().reference
    val reference = database.child("posts")

    fun storePost(post: Post, handler: DatabaseHandler) {
        val key = reference.push().key
        if (key == null) return
        post.id = key
        reference.child(key).setValue(post)
            .addOnSuccessListener {
                handler.onSuccess()
            }.addOnFailureListener {
                handler.onError()
            }
    }

    fun deletePost(post: Post, handler: DatabaseHandler) {
        val key = reference.push().key
        if (key == null) return
        post.id = key
        reference.child(key).removeValue()
            .addOnSuccessListener {
                handler.onSuccess()
            }.addOnFailureListener {
                handler.onError()
            }
    }

    fun loadPost(handler: DatabaseHandler) {
        reference.addValueEventListener(object : ValueEventListener {
            val posts: ArrayList<Post> = ArrayList()

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val post: Post? = data.getValue(Post::class.java)
                        post.let {
                            posts.add(post!!)
                        }
                    }
                    handler.onSuccess(posts = posts)
                } else {
                    handler.onSuccess(posts = ArrayList())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                handler.onError()
            }

        })
    }
}