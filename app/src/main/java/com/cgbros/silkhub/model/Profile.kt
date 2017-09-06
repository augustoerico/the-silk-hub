package com.cgbros.silkhub.model

import com.cgbros.silkhub.enumerator.Platform
import com.cgbros.silkhub.enumerator.PlayerAlignment

data class Profile(
        val uid: String,
        val nickname: String,
        val platform: Platform,
        val alignment: PlayerAlignment
) {

    constructor() : this(
            uid = "",
            nickname = "",
            platform = Platform.EMPTY,
            alignment = PlayerAlignment.EMPTY
    )

    constructor(map: Map<String, Any>) : this(
            uid = map["uid"] as String,
            nickname = map["nickname"] as String,
            platform = platform(map["platform"]),
            alignment = alignment(map["alignment"])
    )

    fun isEmpty() = uid.isEmpty() && nickname.isEmpty() && platform == Platform.EMPTY &&
            alignment == PlayerAlignment.EMPTY

    fun toMap() = mapOf(
            "uid" to uid,
            "nickname" to nickname,
            "platform" to platform,
            "alignment" to alignment
    )

    companion object {

        fun platform(platform: Any?): Platform {
            return if (platform != null)
                when (platform) {
                    is Platform -> platform
                    is String -> if (platform.isEmpty())
                        Platform.EMPTY
                    else
                        enumValueOf(platform.toUpperCase())
                    else -> throw IllegalArgumentException("Profile.platform=$platform not allowed")
                } else Platform.EMPTY
        }

        fun alignment(alignment: Any?): PlayerAlignment {
            return if (alignment != null)
                when (alignment) {
                    is PlayerAlignment -> alignment
                    is String -> if (alignment.isEmpty())
                        PlayerAlignment.EMPTY
                    else
                        enumValueOf(alignment.toUpperCase())
                    else -> throw IllegalArgumentException("Profile.alignment=$alignment not allowed")
                } else PlayerAlignment.EMPTY
        }

    }
}
