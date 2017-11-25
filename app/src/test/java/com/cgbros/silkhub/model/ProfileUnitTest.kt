package com.cgbros.silkhub.model

import com.cgbros.silkhub.enumerator.Platform
import com.cgbros.silkhub.enumerator.PlayerAlignment
import org.junit.Test
import org.junit.Assert.*

class ProfileUnitTest {

    @Test
    fun shouldCreateEmptyProfile() {
        val profile = Profile()
        assertEquals(Profile(
                uid = "",
                nickname = "",
                platform = Platform.EMPTY,
                alignment = PlayerAlignment.EMPTY
        ), profile)
        assert(profile.isEmpty())
    }

    @Test
    fun shouldCreateProfileFromMap() {
        val profile = Profile(mapOf(
                "uid" to "1",
                "nickname" to "N_I_C_K",
                "platform" to Platform.PS4,
                "alignment" to PlayerAlignment.CHAOTIC_EVIL
        ))
        assertEquals(
                Profile(
                        uid = "1",
                        nickname = "N_I_C_K",
                        platform = Platform.PS4,
                        alignment = PlayerAlignment.CHAOTIC_EVIL
                ), profile)
    }

    @Test
    fun shouldCreateProfileFromStringMap() {
        val profile = Profile(mapOf(
                "uid" to "123",
                "nickname" to "NICKNAME",
                "platform" to "xbox_360",
                "alignment" to "lawful_neutral"
        ))
        assertEquals(Profile(
                uid = "123",
                nickname = "NICKNAME",
                platform = Platform.XBOX_360,
                alignment = PlayerAlignment.LAWFUL_NEUTRAL
        ), profile)
    }

    @Test
    fun shouldCreateProfileMissingParam() {
        val profile = Profile(mapOf(
                "uid" to "456",
                "nickname" to "NAME"
        ))
        assertEquals(Profile(
                uid = "456",
                nickname = "NAME",
                platform = Platform.EMPTY,
                alignment = PlayerAlignment.EMPTY
        ), profile)
    }
}
