package com.cgbros.silkhub.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.cgbros.silkhub.enumerator.Platform
import com.cgbros.silkhub.enumerator.PlayerAlignment
import com.cgbros.silkhub.R
import com.cgbros.silkhub.model.Profile
import com.cgbros.silkhub.model.User
import com.cgbros.silkhub.singleton.LoggedInUser
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

        val currentUser = mAuth.currentUser
        if (currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            return
        }

        LoggedInUser
                .getInstance { user: User ->
                    Toast.makeText(this, "AEHOOOOO: ${user.toStringMap()}", Toast.LENGTH_SHORT).show()
                    val alignment = PlayerAlignment.axisValues(user.profile.alignment)

                    (profile_nickname as TextView).text = user.profile.nickname
                    (profile_law_chaos as SeekBar).progress = alignment.first
                    (profile_good_evil as SeekBar).progress = alignment.second
                    (profile_platform as RadioGroup).check(
                        Platform.idFromString(user.profile.platform.toString())
                    )

                    profile_discard.setOnClickListener { discard() }
                    profile_save.setOnClickListener { save() }
                }
    }

    private fun discard() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        return
    }

    private fun save() {

        val profile = Profile(
                uid = mAuth.currentUser!!.uid,
                nickname = profile_nickname.text.toString(),
                platform = Platform.fromId(profile_platform.checkedRadioButtonId).toString(),
                alignment = PlayerAlignment.fromValues(
                        profile_law_chaos.progress,
                        profile_good_evil.progress
                ).toString()
        )

        LoggedInUser
                .setInstance { user: User ->
                    user.profile = profile
                }
                .publish()
                .addOnCompleteListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Ops, something is wrong", Toast.LENGTH_SHORT).show()
                }
    }

    private fun load() {

    }
}
