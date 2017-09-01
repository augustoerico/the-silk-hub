package com.cgbros.silkhub.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.cgbros.silkhub.enumerator.Job
import com.cgbros.silkhub.R
import com.cgbros.silkhub.model.Session
import com.cgbros.silkhub.singleton.LoggedInProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_session.*

class CreateSessionActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var session: Session = Session(Job.FLEECA_JOB, LoggedInProfile.instance, arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_session)
    }

    override fun onStart() {
        super.onStart()

        create_session_jobs.adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, Job.values().map { it.toString() })
        create_session_jobs.onItemSelectedListener = this

        create_session_discard.setOnClickListener { discard() }
        create_session_create.setOnClickListener { createSession() }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        return
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        session.job = Job.fromPosition(position)
    }

    private fun discard() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return
    }

    private fun createSession() {
        FirebaseDatabase.getInstance().getReference("open_sessions")
                .push()
                .setValue(session.toStringMap())

        Toast.makeText(this, "Session created", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        return
    }

}
