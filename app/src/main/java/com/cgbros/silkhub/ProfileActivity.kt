package com.cgbros.silkhub

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()

    private val profilesRef = database.getReference("profiles")

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

        // TODO Load from DB
    }

    private fun discard() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return
    }

    private fun save() {

        val uid = mAuth.currentUser!!.uid

        val profile = Profile(
                uid = uid,
                nickname = profile_nickname.text.toString(),
                platform = Platform.fromId(profile_platform.checkedRadioButtonId),
                alignment = PlayerAlignment.fromValues(
                        profile_law_chaos.progress,
                        profile_good_evil.progress
                )
        )

        profilesRef.child(uid)
                .setValue(profile.toMap())
                .addOnCompleteListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Ops, something is wrong", Toast.LENGTH_SHORT).show()
                }

    }
}
