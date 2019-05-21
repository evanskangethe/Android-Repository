package com.example.app.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layoutRes: Int,attachToRoot: Boolean = false):View {
    return LayoutInflater.from(context).inflate(layoutRes,this,attachToRoot)
}

fun Context.toast(message: String) {
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}