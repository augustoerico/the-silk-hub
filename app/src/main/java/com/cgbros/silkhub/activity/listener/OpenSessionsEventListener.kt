package com.cgbros.silkhub.activity.listener

import android.app.Activity
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*

class OpenSessionsEventListener(val activity: Activity) : ValueEventListener {

    override fun onCancelled(error: DatabaseError?) {
        val message = error?.message ?: "Cancelled"
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDataChange(snapshot: DataSnapshot?) {
        val count = snapshot?.children?.count() ?: 0

        val text = "$count open sessions"
        activity.login_open_sessions.text = text
    }

}
