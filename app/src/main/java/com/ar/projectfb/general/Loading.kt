package com.ar.projectfb.general

import android.app.AlertDialog
import com.ar.projectfb.R

class Loading(val homeTvShow: HomeTvShow) {

    private lateinit var isdialog: AlertDialog
    fun startLoading(){
        /**set View*/
        val infalter = homeTvShow.layoutInflater
        val dialogView = infalter.inflate(R.layout.loading,null)
        /**set Dialog*/

        val bulider = AlertDialog.Builder(homeTvShow)
        bulider.setView(dialogView)
        bulider.setCancelable(false)
        isdialog = bulider.create()
        isdialog.show()
    }

    fun isDismiss(){
        isdialog.dismiss()
    }

}
