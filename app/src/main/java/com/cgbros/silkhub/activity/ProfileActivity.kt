package com.cgbros.silkhub.activity

import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import com.cgbros.silkhub.R
import com.cgbros.silkhub.enumerator.Platform
import com.cgbros.silkhub.enumerator.PlayerAlignment
import com.cgbros.silkhub.model.Profile
import com.cgbros.silkhub.model.User
import com.cgbros.silkhub.singleton.LoggedInUser
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AuthenticatedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }

    override fun onStart() {
        super.onStart()

        LoggedInUser
                .getInstance { user: User ->
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
                uid = currentUser!!.uid,
                nickname = profile_nickname.text.toString(),
                platform = Platform.fromId(profile_platform.checkedRadioButtonId),
                alignment = PlayerAlignment.fromValues(
                        profile_law_chaos.progress,
                        profile_good_evil.progress
                )
        )

        LoggedInUser
                .setInstance { user: User ->
                    user.copy(profile = profile)
                }
                .publish()
                .addOnCompleteListener {
                    startActivity(Intent(this, MainActivity::class.java))
                    // TODO I18N
                    Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    // TODO I18N
                    Toast.makeText(this, "Ops, something is wrong", Toast.LENGTH_SHORT).show()
                }
    }

}
