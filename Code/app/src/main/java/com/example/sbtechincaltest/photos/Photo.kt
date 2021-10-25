package com.example.sbtechincaltest.photos

data class Photo(val title: String, private val _url: String) {
    // API is missing the suffix on images but Glide requires it
    // obviously not a perfect way to deal with this
    // Some images still don't work, accessing them in the browser with the suffix gives a 410
    val url = _url.takeIf { it.endsWith(".png") } ?: "$_url.png"
}