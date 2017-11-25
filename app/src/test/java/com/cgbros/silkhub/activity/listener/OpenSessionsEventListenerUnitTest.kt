package com.cgbros.silkhub.activity.listener

import android.widget.TextView
import com.cgbros.silkhub.R
import com.cgbros.silkhub.activity.LoginActivity
import com.facebook.FacebookSdk
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.mockito.Mockito.`when` as whenCalled

@RunWith(RobolectricTestRunner::class)
class OpenSessionsEventListenerUnitTest {

    @Before
    fun setup() {
        println("Setting up test environment...")

        println("Initializing firebase app")
        FirebaseApp.initializeApp(RuntimeEnvironment.application)

        println("Initializing Facebook app")
        FacebookSdk.sdkInitialize(RuntimeEnvironment.application)

        println("Done!")
    }

    @Test
    fun shouldDisplayTheNumberOfOpenSessions() {

        // Setup
        val dataSnapshot: DataSnapshot  = mock(DataSnapshot::class.java)
        whenCalled(dataSnapshot.children).thenReturn(listOf(
                Mockito.mock(DataSnapshot::class.java)
        ))

        val activity = Robolectric.buildActivity(LoginActivity::class.java)
                .create()
                .start()
                .visible()
                .get()

        // Given
        val openSessionsEventListener = OpenSessionsEventListener(activity)

        // When
        openSessionsEventListener.onDataChange(dataSnapshot)
        val openSessions = activity.findViewById<TextView>(R.id.login_open_sessions).text

        // Then
        assertEquals("1 open sessions", openSessions)
    }

}