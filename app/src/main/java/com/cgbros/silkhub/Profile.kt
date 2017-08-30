package com.cgbros.silkhub

class Profile(val uid: String, val nickname: String, val platform: Platform,
              val alignment: PlayerAlignment) {

    fun toMap() = mapOf(
            "uid" to uid,
            "nickname" to nickname,
            "platform" to platform.toString(),
            "alignment" to alignment.toString()
    )

}
