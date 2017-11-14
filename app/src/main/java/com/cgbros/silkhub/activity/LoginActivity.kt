package com.cgbros.silkhub.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cgbros.silkhub.R
import com.cgbros.silkhub.activity.callback.FacebookLoginCallback
import com.cgbros.silkhub.activity.listener.OpenSessionsEventListener
import com.facebook.CallbackManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*

open class LoginActivity : AppCompatActivity() {

    private val openSessionsRef: DatabaseReference = FirebaseDatabase.getInstance()
            .getReference("openSessions")
    private val callbackManager: CallbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()

        // Show open sessions
        openSessionsRef.addValueEventListener(OpenSessionsEventListener(this))

        // Wrap login button
        facebook_login_button.registerCallback(callbackManager,
                FacebookLoginCallback(this))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

}
