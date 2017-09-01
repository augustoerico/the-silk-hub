package com.cgbros.silkhub.model

import com.cgbros.silkhub.enumerator.Platform
import com.cgbros.silkhub.enumerator.PlayerAlignment

class Profile(var uid: String,
              var nickname: String,
              var platform: Platform,
              var alignment: PlayerAlignment) {

    constructor(
            uid: String = "",
            nickname: String = "",
            platform: String = Platform.PC.toString(),
            alignment: String = PlayerAlignment.TRUE_NEUTRAL.toString()) : this(
            uid = uid,
            nickname = nickname,
            platform = enumValueOf<Platform>(platform),
            alignment = enumValueOf<PlayerAlignment>(alignment)
    )

    constructor() : this(
            uid = "",
            nickname = "",
            platform = Platform.PC,
            alignment = PlayerAlignment.TRUE_NEUTRAL
    )

    fun toStringMap() = mapOf(
            "uid" to uid,
            "nickname" to nickname,
            "platform" to platform.toString(),
            "alignment" to alignment.toString()
    )

    fun isEmpty() = uid.isEmpty() && nickname.isEmpty()
}
