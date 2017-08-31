package com.cgbros.silkhub

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()

    private val usersRef = database.getReference("users")

    private val that = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = mAuth.currentUser
        if (currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            return
        }

        profile_discard.setOnClickListener { discard() }
        profile_save.setOnClickListener { save() }

        usersRef.child("${currentUser.uid}/profile").addValueEventListener(MyListener())

    }

    private fun discard() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return
    }

    private fun save() {

        val uid = mAuth.currentUser!!.uid

        Profile.uid = uid
        Profile.nickname = profile_nickname.text.toString()
        Profile.platform = Platform.fromId(profile_platform.checkedRadioButtonId).toString()
        Profile.alignment = PlayerAlignment.fromValues(
                profile_law_chaos.progress,
                profile_good_evil.progress
        ).toString()

        usersRef.child("$uid/profile")
                .updateChildren(Profile.toMap())
                .addOnCompleteListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Ops, something is wrong", Toast.LENGTH_SHORT).show()
                }
    }

    inner class MyListener : ValueEventListener {

        override fun onCancelled(databaseError: DatabaseError?) {
            Log.e("Profile", "Error on profile load", databaseError!!.toException())
            Toast.makeText(that, "Error on load. Check connection", Toast.LENGTH_SHORT).show()
        }

        override fun onDataChange(data: DataSnapshot?) {
            val profile = data?.getValue(Profile::class.java)

            if (profile != null) {

                val alignment = PlayerAlignment.axisValues(enumValueOf<PlayerAlignment>(profile.alignment))

                (profile_nickname as TextView).text = profile.nickname
                (profile_law_chaos as SeekBar).progress = alignment.first
                (profile_good_evil as SeekBar).progress = alignment.second
                (profile_platform as RadioGroup).check(Platform.idFromString(profile.platform))

            }
        }

    }
}
