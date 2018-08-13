package com.santriprogrammer.retrokotlin.popup

import android.app.AlertDialog
import android.view.View

abstract class BaseDialogHelper {

    abstract val dialogVIew: View
    abstract val builder: AlertDialog.Builder

    open var cancelable: Boolean = true

    open var dialog: AlertDialog? = null

    //dialog create
    open fun dialogCreate(): AlertDialog {

        dialog = builder
                .setCancelable(cancelable)
                .create()


        return dialog!!
    }

    open fun onCancelableListener(func: () -> Unit): AlertDialog.Builder? =
            builder.setOnCancelListener {
                func
            }


}
