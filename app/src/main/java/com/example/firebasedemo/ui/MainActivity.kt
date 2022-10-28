package com.example.firebasedemo.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasedemo.R
import com.example.firebasedemo.adapter.PostAdapter
import com.example.firebasedemo.manager.AuthManager
import com.example.firebasedemo.manager.DatabaseHandler
import com.example.firebasedemo.manager.DatabaseManager
import com.example.firebasedemo.model.Post
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : BaseActivity() {
    lateinit var recycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        recycler = findViewById(R.id.recyclerView)
        recycler.layoutManager = GridLayoutManager(this, 1)
        val iv_logOut = findViewById<ImageView>(R.id.iv_logOut)
        iv_logOut.setOnClickListener {
            AuthManager.signOut()
            callSignInActivity(context)
        }
        val fab = findViewById<FloatingActionButton>(R.id.fab_create)
        fab.setOnClickListener {
            callCreateActivity()
        }

        apiLoadPost()
    }

    private fun callCreateActivity() {
        val intent = Intent(context, CreateActivity::class.java)
        resultLauncher.launch(intent)
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                apiLoadPost()
            }
        }

    private fun apiLoadPost() {
        DatabaseManager.loadPost(object : DatabaseHandler {
            override fun onSuccess(post: Post?, posts: ArrayList<Post>) {
                refreshAdapter(posts)
            }

            override fun onError() {
                TODO("Not yet implemented")
            }

        })
    }

    private fun refreshAdapter(posts: ArrayList<Post>) {
        val adapter = PostAdapter(this, posts)
        recycler.adapter = adapter
    }

    fun dialogPoster(post: Post) {
        AlertDialog.Builder(this)
            .setTitle("Delete Poster")
            .setMessage("Are you sure you want to delete this poster?")
            .setNeutralButton("Edit") { dialog, which ->
            }
            .setPositiveButton(
                "Delete"
            ) { dialog, which ->
//                RetrofitInstance.postService.deletePost(note.id).enqueue(object : Callback<Note> {
//                    override fun onResponse(call: Call<Note>, response: Response<Note>) {
//                        if (response.isSuccessful) {
//                            Toast.makeText(
//                                this@MainActivity,
//                                "Successful Delete",
//                                Toast.LENGTH_SHORT
//                            )
//                                .show()
//                            adapter!!.notifyDataSetChanged()
//                        }
//                    }

//                    override fun onFailure(call: Call<Note>, t: Throwable) {
//                        Toast.makeText(this@MainActivity, "failed", Toast.LENGTH_SHORT).show()
//                    }

//                })
            }
            .setNegativeButton("Cancel", null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
}