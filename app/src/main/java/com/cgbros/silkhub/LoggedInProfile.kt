package com.cgbros.silkhub

import com.cgbros.silkhub.model.Profile
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object LoggedInProfile {

    var instance: Profile = Profile()

    private val userUid = FirebaseAuth.getInstance().currentUser!!.uid
    private val profileRef = FirebaseDatabase.getInstance().reference.child("users/$userUid/profile")

    init {
        profileRef.addValueEventListener(
                object : ValueEventListener {

                    override fun onCancelled(error: DatabaseError?) {
                        throw error!!.toException()
                    }

                    override fun onDataChange(data: DataSnapshot?) {
                        instance = data?.getValue(Profile::class.java) ?: Profile()
                    }
                }
        )
    }

    fun publish(): Task<Void> {
        return profileRef.updateChildren(instance.toStringMap())
    }

}
