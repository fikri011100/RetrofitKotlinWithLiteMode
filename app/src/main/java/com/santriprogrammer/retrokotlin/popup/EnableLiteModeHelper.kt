package com.santriprogrammer.retrokotlin.popup

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import com.santriprogrammer.retrokotlin.R

class EnableLiteModeHelper(context: Context) : BaseDialogHelper() {

    override val dialogVIew: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.enable_lite_popup, null)
    }

    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogVIew)

    //oke button
    private val okIcon: Button by lazy {
        dialogVIew.findViewById<Button>(R.id.button_yes)
    }

    //no button
    private val noIcon: Button by lazy {
        dialogVIew.findViewById<Button>(R.id.buttonNo)
    }

    fun noIconClickListener(func: (() -> Unit)? = null) =
            with(noIcon) {
                setClickListenerToDialogIcon(func)
            }

    fun okIconClickListener(func: (() -> Unit)? = null) =
            with(okIcon) {
                setClickListenerToDialogIcon(func)
            }

    private fun View.setClickListenerToDialogIcon(func: (() -> Unit)?) =
            setOnClickListener {
                func?.invoke()
                dialog?.dismiss()
            }
}