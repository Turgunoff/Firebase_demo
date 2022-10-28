package com.example.firebasedemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.firebasedemo.R
import com.example.firebasedemo.adapter.PostAdapter
import com.example.firebasedemo.manager.DatabaseHandler
import com.example.firebasedemo.manager.DatabaseManager
import com.example.firebasedemo.model.Post
import com.example.firebasedemo.utils.Extensions.toast

class CreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        initViews()
    }

    private fun initViews() {
        val iv_close = findViewById<ImageView>(R.id.iv_close)
        val et_body = findViewById<EditText>(R.id.et_body)
        val et_title = findViewById<EditText>(R.id.et_title)
        val btn_create = findViewById<Button>(R.id.btn_create)

        iv_close.setOnClickListener {
            finish()
        }
        btn_create.setOnClickListener {
            val body = et_body.text.toString().trim()
            val title = et_title.text.toString().trim()
            if (body.isNotEmpty() && title.isNotEmpty()) {
                val post = Post(title, body)
                storeDatabase(post)
            } else {
                toast("Enter post parameters")
            }
        }

    }


    private fun storeDatabase(post: Post) {
        DatabaseManager.storePost(post, object : DatabaseHandler {
            override fun onSuccess(post: Post?, posts: ArrayList<Post>) {
                finishIntent()
            }

            override fun onError() {
            }
        })
    }

    private fun finishIntent() {
        val returnIntent = intent
        setResult(RESULT_OK, returnIntent)
        finish()
    }
}