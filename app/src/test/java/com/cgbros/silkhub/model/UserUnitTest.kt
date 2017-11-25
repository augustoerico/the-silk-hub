package com.cgbros.silkhub.model

import com.cgbros.silkhub.enumerator.Platform
import com.cgbros.silkhub.enumerator.PlayerAlignment
import org.junit.Test
import org.junit.Assert.*

class UserUnitTest {

    @Test
    fun shouldCreateAnEmptyUser() {
        val user = User()

        assertEquals(User(
                profile = Profile(),
                currentSession = ""
        ), user)

        assert(user.isEmpty())
    }

    @Test
    fun shouldCreateUserFromMap() {
        val user = User(mapOf(
                "currentSession" to "123",
                "profile" to mapOf(
                        "uid" to "456",
                        "nickname" to "GAMER",
                        "platform" to "XBOX_360",
                        "alignment" to "TRUE_NEUTRAL"
                )
        ))

        assertEquals(User(
                currentSession = "123",
                profile = Profile(
                        uid = "456",
                        nickname = "GAMER",
                        platform = Platform.XBOX_360,
                        alignment = PlayerAlignment.TRUE_NEUTRAL
                )
        ), user)
    }
}