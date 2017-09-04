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
import com.cgbros.silkhub.singleton.OpenSessions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_search_session.*
import kotlinx.android.synthetic.main.search_session_session.view.*

class SearchSessionActivity : AppCompatActivity() {

    private val that = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_session)
    }

    override fun onStart() {
        super.onStart()

        OpenSessions.get().addValueEventListener(object : ValueEventListener {

            override fun onCancelled(error: DatabaseError?) {
                Log.e("CurrentSession", error!!.message, error.toException())
                // TODO I18N
                Toast.makeText(that, "Check connection", Toast.LENGTH_SHORT).show()
            }

            override fun onDataChange(snapshot: DataSnapshot?) {
                Log.d("SearchSession", "Trigger onDataChange")

                val sessions = (snapshot!!.value!! as Map<*, *>)
                        .map { Session(it.value as Map<String, Any>) }.toList()

                search_session_cards.setAdapter(SearchSessionAdapter(sessions))
            }


        })
    }

    private inner class SearchSessionAdapter(val sessions: List<Session>) : ArrayAdapter<Session>(
            this, R.layout.search_session_session, sessions)
    {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            var view = convertView
            val holder: Holder?

            val session = sessions[position]

            if (view == null) {
                view = layoutInflater.inflate(R.layout.search_session_session, parent)

                holder = Holder(view)
                view.tag = holder
            } else {
                holder = view.tag as Holder
            }

            holder.job.text = session.job.toString()

            return view!!
        }

        private inner class Holder(view: View) {
            var job = view.search_session_job_title
        }

    }
}
