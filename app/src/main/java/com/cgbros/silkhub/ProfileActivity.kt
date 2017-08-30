package com.cgbros.silkhub

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    override fun onStart() {
        super.onStart()

        // FIXME this snippet is repeated everywhere
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

        // TODO save to DB

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()

        return
    }
}
