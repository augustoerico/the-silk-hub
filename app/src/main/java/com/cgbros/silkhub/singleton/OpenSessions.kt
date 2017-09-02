package com.cgbros.silkhub.singleton

import com.google.firebase.database.FirebaseDatabase

object OpenSessions {

    fun get() = FirebaseDatabase.getInstance().reference.child("openSessions")

}
