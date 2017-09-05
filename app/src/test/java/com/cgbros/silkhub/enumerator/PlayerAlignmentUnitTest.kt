package com.cgbros.silkhub.enumerator

import org.junit.Test
import org.junit.Assert.assertEquals
import com.cgbros.silkhub.enumerator.PlayerAlignment.*

class PlayerAlignmentUnitTest {

    @Test
    fun shouldGetExpectedEnumeratorsFromValues() {
        assertEquals(PlayerAlignment.fromValues(0, 0), LAWFUL_GOOD)
        assertEquals(PlayerAlignment.fromValues(1, 1), TRUE_NEUTRAL)
        assertEquals(PlayerAlignment.fromValues(2, 2), CHAOTIC_EVIL)
        assertEquals(PlayerAlignment.fromValues(0, 2), LAWFUL_EVIL)
        assertEquals(PlayerAlignment.fromValues(2, 0), CHAOTIC_GOOD)
    }

    @Test
    fun shouldGetAxisValuesFromEnumerators() {
        assertEquals(PlayerAlignment.axisValues(LAWFUL_GOOD), Pair(0, 0))
        assertEquals(PlayerAlignment.axisValues(TRUE_NEUTRAL), Pair(1, 1))
        assertEquals(PlayerAlignment.axisValues(CHAOTIC_EVIL), Pair(2, 2))
        assertEquals(PlayerAlignment.axisValues(LAWFUL_EVIL), Pair(0, 2))
        assertEquals(PlayerAlignment.axisValues(CHAOTIC_GOOD), Pair(2, 0))
    }

}
