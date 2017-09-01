package com.cgbros.silkhub.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cgbros.silkhub.R
import com.cgbros.silkhub.model.User
import com.cgbros.silkhub.singleton.LoggedInUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AuthenticatedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        LoggedInUser
                .getInstance { user: User ->
                    if (user.currentSession != "") {
                        Toast.makeText(
                                this, "IT HAS A SESSION", Toast.LENGTH_LONG
                        ).show()
                        // TODO disable create new session or join another session
                    }

                    main_current_session_button.setOnClickListener { currentSession() }
                    main_profile_button.setOnClickListener { profile() }
                    main_create_session_button.setOnClickListener { createSession() }
                }
    }

    private fun currentSession() {
        Log.d("HOME", "currentSession")
    }

    private fun profile() {
        Log.d("HOME", "profile")
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
        return
    }

    private fun createSession() {
        Log.d("HOME", "createSession")
        val intent = Intent(this, CreateSessionActivity::class.java)
        startActivity(intent)
        return
    }
}
