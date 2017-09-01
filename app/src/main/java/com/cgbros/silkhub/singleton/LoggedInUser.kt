package com.cgbros.silkhub.singleton

import com.cgbros.silkhub.model.Profile
import com.cgbros.silkhub.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object LoggedInUser {

    private var instance: User = User()

    private val uid = FirebaseAuth.getInstance().currentUser!!.uid
    private val userRef = FirebaseDatabase.getInstance().reference.child("users/$uid")

    fun get(callback: (user: User) -> Unit): LoggedInUser {
        if (instance.isEmpty()) {

            userRef.child("profile").addValueEventListener(object : ValueEventListener {

                override fun onCancelled(error: DatabaseError?) {
                    throw error!!.toException()
                }

                override fun onDataChange(data: DataSnapshot?) {
                    instance.profile = data?.getValue(Profile::class.java) ?: Profile()
                    callback(instance)
                }

            })

            userRef.child("current_session").addValueEventListener(object : ValueEventListener {

                override fun onCancelled(error: DatabaseError?) {
                    throw error!!.toException()
                }

                override fun onDataChange(data: DataSnapshot?) {
                    instance.currentSession = data?.getValue(String::class.java) ?: ""
                    callback(instance)
                }

            })

        } else {
            callback(instance)
        }
        return this
    }

    fun set(user: User): LoggedInUser {
        instance = user
        return this
    }

}
