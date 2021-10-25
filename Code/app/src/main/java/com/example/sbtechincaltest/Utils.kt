package com.example.sbtechincaltest

import android.text.Editable
import android.text.TextWatcher

class OnTextChangedWatcher(private val callback: () -> Unit) : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        callback.invoke()
    }

    override fun afterTextChanged(p0: Editable?) {
    }
}