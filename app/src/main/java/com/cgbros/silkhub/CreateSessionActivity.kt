package com.cgbros.silkhub

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_session.*

class CreateSessionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_session)
    }

    override fun onStart() {
        super.onStart()
        create_session_jobs.adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, Job.values().map { it.toString() })

        create_session_discard.setOnClickListener { discard() }
        create_session_create.setOnClickListener { createSession() }
    }

    private fun discard() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return
    }

    private fun createSession() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        
        Toast.makeText(this, "Session created", Toast.LENGTH_SHORT).show()

        return
    }

}
