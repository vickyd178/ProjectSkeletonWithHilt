package com.avion.projectskeletonwithhilt.utils

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText


var EditText.value
    get() = this.text.toString()
    set(value) {
        this.setText(value)
    }


fun EditText.showPassword(show: Boolean) {

    this.transformationMethod =
        if (show)
            HideReturnsTransformationMethod.getInstance()
        else
            PasswordTransformationMethod.getInstance()

    this.setSelection(this.text.toString().length)

}
