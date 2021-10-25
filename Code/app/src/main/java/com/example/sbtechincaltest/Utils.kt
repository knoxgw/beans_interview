package com.example.sbtechincaltest

import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(message: String) =
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()

class OnTextChangedWatcher(private val callback: () -> Unit) : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        callback.invoke()
    }

    override fun afterTextChanged(p0: Editable?) {
    }
}