package com.cgbros.silkhub.model

import com.cgbros.silkhub.enumerator.Job
import com.cgbros.silkhub.enumerator.Platform
import com.cgbros.silkhub.enumerator.PlayerAlignment
import org.junit.Test
import org.junit.Assert.*

class SessionUnitTest {

    @Test
    fun shouldCreateAnEmptySession() {
        val session = Session()

        assertEquals(Session(
                id = "",
                job = Job.EMPTY,
                crew = mapOf()
        ), session)
        assert(session.isEmpty())
    }

    @Test
    fun shouldCreateFromStringMap() {

        val profileStringMap = mapOf(
                "uid" to "abc",
                "nickname" to "NickName",
                "platform" to Platform.PC.name,
                "alignment" to PlayerAlignment.CHAOTIC_GOOD.name
        )
        val sessionStringMap = mapOf(
                "id" to "123",
                "job" to Job.HEIST_MISSIONS.name,
                "crew" to mapOf(
                        "abc" to profileStringMap
                )
        )
        val session = Session(sessionStringMap)

        assertEquals(Session(
                id = "123",
                job = Job.HEIST_MISSIONS,
                crew = mapOf(
                        "abc" to Profile(profileStringMap)
                )
        ), session)
    }

}