package com.cgbros.silkhub.model

class User(var profile: Profile, var currentSession: String) {

    constructor() : this(profile = Profile(), currentSession = "")

    fun toStringMap() = mapOf(
            "profile" to profile,
            "currentSession" to currentSession
    )

    fun isEmpty() = profile.isEmpty() && currentSession.isEmpty()

}
