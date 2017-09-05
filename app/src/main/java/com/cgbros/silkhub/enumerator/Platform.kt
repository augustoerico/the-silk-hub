package com.cgbros.silkhub.enumerator

import com.cgbros.silkhub.R

enum class Platform {

    PC, PS3, PS4, XBOX_360, XBOX_ONE, EMPTY;

    companion object Factory {
        fun fromId(value: Int): Platform {
            return when (value) {
                R.id.profile_pc ->  PC
                R.id.profile_ps3 -> PS3
                R.id.profile_ps4 -> PS4
                R.id.profile_xbox360 -> XBOX_360
                R.id.profile_xboxone -> XBOX_ONE
                else -> EMPTY
            }
        }

        fun idFromString(value: String): Int {
            return when (value.trim().toUpperCase()) {
                "PC" -> R.id.profile_pc
                "PS3" -> R.id.profile_ps3
                "PS4" -> R.id.profile_ps4
                "XBOX_360" -> R.id.profile_xbox360
                "XBOX_ONE" -> R.id.profile_xboxone
                else -> -1
            }
        }
    }
}
