package com.cgbros.silkhub.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.cgbros.silkhub.R
import com.cgbros.silkhub.model.Profile
import com.cgbros.silkhub.model.Session
import com.cgbros.silkhub.model.User
import com.cgbros.silkhub.singleton.LoggedInUser
import com.cgbros.silkhub.singleton.OpenSessions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_current_session.*
import kotlinx.android.synthetic.main.current_session_crew_member.view.*

class CurrentSessionActivity : AppCompatActivity() {

    private val that = this

    private var session: Session? = Session()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_session)
    }

    override fun onStart() {
        super.onStart()

        LoggedInUser.getInstance { user: User ->
            if (!user.currentSession.isNullOrEmpty()) {
                OpenSessions.get().child(user.currentSession)
                        .addValueEventListener(ValueEventListenerImpl())
            }
        }
    }

    private inner class CurrentSessionCrewAdapter(val crew: List<Profile>) : ArrayAdapter<Profile>(
            this, R.layout.current_session_crew_member, crew)
    {
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            var view = convertView
            val holder: Holder?

            val profile: Profile = crew[position]

            if (view == null) {
                view = layoutInflater.inflate(
                        R.layout.current_session_crew_member, viewGroup, false
                )

                holder = Holder(view)
                view.tag = holder
            } else {
                holder = view.tag as Holder
            }

            holder.nicknameTextView.text = profile.nickname
            holder.alignmentTextView.text = profile.alignment.toString()

            return view!!
        }

        inner class Holder(view: View) {
            var nicknameTextView = view.current_session_crew_member_nickname
            var alignmentTextView = view.current_session_crew_member_alignment
        }

    }

    private inner class ValueEventListenerImpl : ValueEventListener {
        override fun onCancelled(error: DatabaseError?) {
            Log.e("CurrentSession", error!!.message, error.toException())
            // TODO I18N
            Toast.makeText(that, "Check connection", Toast.LENGTH_SHORT).show()
        }

        override fun onDataChange(snapshot: DataSnapshot?) {
            Log.d("CurrentSession", "Trigger onDataChange")
            val session = snapshot!!.getValue(Session::class.java)!!

            val crew = session.crew.map { it.value }
            current_session_crew.adapter = CurrentSessionCrewAdapter(crew)

            that.session = session
        }
    }
}
