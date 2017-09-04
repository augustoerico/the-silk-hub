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

    constructor(map: Map<String, String>) : this(
            uid = map["uid"] ?: "",
            nickname = map["nickname"] ?: "",
            platform = enumValueOf<Platform>(map["platform"] ?: Platform.PC.toString()),
            alignment = enumValueOf<PlayerAlignment>(map["alignment"] ?:
                    PlayerAlignment.TRUE_NEUTRAL.toString())
    )

    fun toStringMap() = mapOf(
            "uid" to uid,
            "nickname" to nickname,
            "platform" to platform.toString(),
            "alignment" to alignment.toString()
    )

    fun isEmpty() = uid.isEmpty() && nickname.isEmpty()

    override fun toString(): String {
        return toStringMap().toString()
    }
}
