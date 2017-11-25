package com.cgbros.silkhub.enumerator

import org.junit.Test
import org.junit.Assert.*

class JobUnitTest {

    @Test
    fun shouldGetEnumFromPosition() {
        assertEquals(Job.FLEECA_JOB, Job.fromPosition(0))
        assertEquals(Job.PRISON_BREAK, Job.fromPosition(1))
        assertEquals(Job.HUMANE_LABS_RAID, Job.fromPosition(2))
        assertEquals(Job.SERIES_A_FUNDING, Job.fromPosition(3))
        assertEquals(Job.PACIFIC_STANDARD_JOB, Job.fromPosition(4))
        assertEquals(Job.CEO_MISSIONS, Job.fromPosition(5))
        assertEquals(Job.BIKER_MISSIONS, Job.fromPosition(6))
    }

}
