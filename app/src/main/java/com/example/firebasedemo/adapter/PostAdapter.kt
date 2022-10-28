package com.example.firebasedemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasedemo.R
import com.example.firebasedemo.model.Post
import com.example.firebasedemo.ui.CreateActivity
import com.example.firebasedemo.ui.MainActivity

/**
 * Created by Eldor Turgunov.
 * Firebase demo
 * eldorturgunov777@gmail.com
 */
class PostAdapter(var activity: MainActivity, var items: ArrayList<Post>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post: Post = items[position]

        if (holder is PostViewHolder) {
            holder.tv_title.text = post.title!!.toUpperCase()
            holder.tv_body.text = post.body
            holder.longClick.setOnClickListener {
                activity.dialogPoster(post)
                false
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_title: TextView
        var tv_body: TextView
        var iv_profile: ImageView
        var longClick: LinearLayout

        init {
            tv_title = view.findViewById(R.id.tv_title)
            tv_body = view.findViewById(R.id.tv_body)
            iv_profile = view.findViewById(R.id.iv_profile)
            longClick=view.findViewById(R.id.longClick)
        }
    }

}