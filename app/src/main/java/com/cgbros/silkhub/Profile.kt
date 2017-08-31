package com.cgbros.silkhub

object Profile {

    var uid: String = ""
    var nickname: String = ""
    var platform: String = Platform.PC.toString()
    var alignment: String = PlayerAlignment.TRUE_NEUTRAL.toString()

    fun toMap() = mapOf(
            "uid" to uid,
            "nickname" to nickname,
            "platform" to platform,
            "alignment" to alignment
    )

}
