package com.cgbros.silkhub.enumerator

import org.junit.Test
import org.junit.Assert.*

class JobUnitTest {

    @Test
    fun shouldGetEnumFromPosition() {
        assertEquals(Job.HEIST_MISSIONS, Job.fromPosition(1))
        assertEquals(Job.CEO_MISSIONS, Job.fromPosition(2))
        assertEquals(Job.BIKER_MISSIONS, Job.fromPosition(3))
    }

}
