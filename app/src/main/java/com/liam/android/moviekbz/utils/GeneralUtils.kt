package com.liam.android.moviekbz.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.focus(context: Context) {
    text?.let { setSelection(it.length) }
    postDelayed({
        if(context != null){
            requestFocus()
            val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }
    }, 200)
}