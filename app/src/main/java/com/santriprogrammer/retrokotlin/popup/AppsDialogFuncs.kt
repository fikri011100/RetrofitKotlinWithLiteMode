package com.santriprogrammer.retrokotlin.popup

import android.app.Activity
import android.app.AlertDialog

inline fun Activity.showEnableLiteModePopup(func: EnableLiteModeHelper.() -> Unit): AlertDialog =
        EnableLiteModeHelper(this).apply {
            func()
        }.dialogCreate()