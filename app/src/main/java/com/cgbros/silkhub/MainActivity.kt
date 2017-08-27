package com.cgbros.silkhub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        main_current_session_button.setOnClickListener { currentSession() }
        main_profile_button.setOnClickListener { profile() }
        main_create_session_button.setOnClickListener { createSession() }
    }

    private fun currentSession() {
        Log.d("HOME", "currentSession")
    }

    private fun profile() {
        Log.d("HOME", "profile")
    }

    private fun createSession() {
        Log.d("HOME", "createSession")
    }
}
