package com.cgbros.silkhub

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val openSessionsRef: DatabaseReference = database.getReference("open_sessions")

    private val that = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()

        // Show open sessions
        openSessionsRef.addValueEventListener(MyListener())

    }

    inner class MyListener : ValueEventListener {

        override fun onCancelled(error: DatabaseError?) {
            val message = error?.message ?: "Cancelled"
            Toast.makeText(that, message, Toast.LENGTH_SHORT).show()
        }

        override fun onDataChange(snapshot: DataSnapshot?) {
            val count = snapshot?.children?.count() ?: 0

            val text = "$count open sessions"
            login_open_sessions.text = text
        }

    }

}
