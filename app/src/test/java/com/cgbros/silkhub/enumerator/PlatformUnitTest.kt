package com.cgbros.silkhub.enumerator

import com.cgbros.silkhub.R
import org.junit.Assert.assertEquals
import org.junit.Test

class PlatformUnitTest {

    @Test
    fun shouldGetExpectedEnumerator() {
        assertEquals(Platform.fromId(R.id.profile_pc), Platform.PC)
        assertEquals(Platform.fromId(R.id.profile_ps3), Platform.PS3)
        assertEquals(Platform.fromId(R.id.profile_ps4), Platform.PS4)
        assertEquals(Platform.fromId(R.id.profile_xbox360), Platform.XBOX_360)
        assertEquals(Platform.fromId(R.id.profile_xboxone), Platform.XBOX_ONE)
    }

    @Test
    fun shouldGetIdFromString() {
        assertEquals(Platform.idFromString("PC"), R.id.profile_pc)
        assertEquals(Platform.idFromString("PS3"), R.id.profile_ps3)
        assertEquals(Platform.idFromString("PS4"), R.id.profile_ps4)
        assertEquals(Platform.idFromString("XBOX_360"), R.id.profile_xbox360)
        assertEquals(Platform.idFromString("XBOX_ONE"), R.id.profile_xboxone)
    }

}
