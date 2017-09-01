package com.cgbros.silkhub.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

open class AuthenticatedActivity : AppCompatActivity() {

    val currentUser = FirebaseAuth.getInstance().currentUser

    override fun onStart() {
        super.onStart()

        if (currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}
