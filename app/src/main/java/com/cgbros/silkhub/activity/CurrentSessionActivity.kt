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
import kotlinx.android.synthetic.main.current_session_crew_member.*

class CurrentSessionActivity : AppCompatActivity() {

    private val that = this

    private var session: Session? = Session()
    private var crew = listOf<Profile>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_session)
        current_session_crew.adapter = CurrentSessionCrewAdapter(crew)
    }

    override fun onStart() {
        super.onStart()

        LoggedInUser.getInstance { user: User ->
            OpenSessions.get().child(user.currentSession)
                    .addValueEventListener(ValueEventListenerImpl())
        }
    }

    private inner class CurrentSessionCrewAdapter(val crew: List<Profile>) : ArrayAdapter<Profile>(
            this, R.layout.current_session_crew_member, crew)
    {
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            var view = convertView
            var holder = Holder()

            if (view == null) {
                view = that.layoutInflater.inflate(
                        R.layout.current_session_crew_member, viewGroup, false
                )
                view.tag = holder
            } else {
                holder = view.tag as Holder
            }

            val profile: Profile = crew[position]
            holder.nicknameTextView.text = profile.nickname
            holder.alignmentTextView.text = profile.alignment.toString()

            return view!!
        }

        inner class Holder {
            var nicknameTextView = current_session_crew_member_nickname
            var alignmentTextView = current_session_crew_member_alignment
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

            Log.d("CurrentSession", "AEHOOO: ${session.toStringMap()}") // TODO remove-me

            crew = session.crew.map { it.value }

            that.session = session
            current_session_title.text = session.job.toString()
        }
    }
}
