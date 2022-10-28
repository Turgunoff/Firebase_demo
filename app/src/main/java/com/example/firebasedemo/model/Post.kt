package com.example.firebasedemo.model

import android.icu.text.CaseMap.Title

/**
 * Created by Eldor Turgunov.
 * Firebase demo
 * eldorturgunov777@gmail.com
 */
class Post {
    var id: String? = null
    var title: String? = null
    var body: String? = null
    var img: String? = ""

    constructor()

    constructor(title: String?, body: String?) {
        this.title = title
        this.body = body
    }

    constructor(id: String, title: String?, body: String?) {
        this.id = id
        this.title = title
        this.body = body
    }

}