package com.example.albertsonapplication.retrofit

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast


class NetworkCallsManager {
    var isInternetAvailable = false
    val context: NetworkCallsManager = this

    fun checkConnection(context: Context): Boolean {
        return checkConnection(context, true)
    }

    fun checkConnection(context: Context, isDialogRequired: Boolean): Boolean {

        if (isDialogRequired && !isInternetAvailable) {
            val builder = AlertDialog.Builder(context)
            //set title for alert dialog
            builder.setTitle("Network Connection")
            //set message for alert dialog
            builder.setMessage("No Network available ")
            builder.setIcon(android.R.drawable.ic_dialog_alert)

            //performing positive action
            builder.setPositiveButton("Ok"){dialogInterface, which ->
                Toast.makeText(context,"clicked yes",Toast.LENGTH_LONG).show()
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

        return isInternetAvailable
    }

}