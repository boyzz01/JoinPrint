package com.ard.joinprint.Util

import android.app.Activity
import android.app.AlertDialog
import com.ard.joinprint.R

class LoadingDialog internal constructor(var activity: Activity) {

    var alertDialog: AlertDialog? = null

    fun startLoadingDialog() {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.custom_loading, null))
        builder.setCancelable(false)
        alertDialog = builder.show()
        alertDialog?.show()
    }

    fun dismissDialog() {
        alertDialog!!.dismiss()
    }
}