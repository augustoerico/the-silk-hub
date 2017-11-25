package com.cgbros.silkhub.model

data class User(val profile: Profile, val currentSession: String) {

    constructor() : this(
            profile = Profile(),
            currentSession = ""
    )

    @Suppress("UNCHECKED_CAST")
    constructor(map: Map<String, Any?>) : this(
            profile = Profile(map["profile"] as Map<String, Any?>),
            currentSession = map["currentSession"] as String
    )

    fun isEmpty() = currentSession.isEmpty() && profile.isEmpty()

    fun toMap() = mapOf(
            "currentSession" to currentSession,
            "profile" to profile.toMap()
    )

}
