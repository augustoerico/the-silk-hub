package com.cgbros.silkhub.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.cgbros.silkhub.R
import com.cgbros.silkhub.enumerator.Job
import com.cgbros.silkhub.model.Session
import com.cgbros.silkhub.model.User
import com.cgbros.silkhub.singleton.LoggedInUser
import com.cgbros.silkhub.singleton.OpenSessions
import kotlinx.android.synthetic.main.activity_create_session.*
import java.util.*

class CreateSessionActivity : AuthenticatedActivity(), AdapterView.OnItemSelectedListener {

    private val that = this

    private var session: Session = Session()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_session)

        LoggedInUser.getInstance { user: User ->
            session = Session(
                    id = UUID.randomUUID().toString(),
                    job = Job.EMPTY,
                    crew = mapOf(user.profile.uid to user.profile)
            )
        }
    }

    override fun onStart() {
        super.onStart()

        create_session_jobs.adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                Job.values().map { it.toString() }
        )
        create_session_jobs.onItemSelectedListener = this

        create_session_discard.setOnClickListener { discard() }
        create_session_create.setOnClickListener { createSession() }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) { }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        session = session.copy(job = Job.fromPosition(position))
    }

    private fun discard() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun createSession() {

        OpenSessions.get().updateChildren(mapOf(session.id to session.toMap()), { _, _ ->
            LoggedInUser
                    .setInstance({ user: User ->
                        user.copy(currentSession = session.id)
                    })
                    .publish()
            Toast.makeText(that, "Session created", Toast.LENGTH_SHORT).show()
            startActivity(Intent(that, MainActivity::class.java))
        })

    }

}
