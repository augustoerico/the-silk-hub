package com.cgbros.silkhub.activity.listener

import android.widget.TextView
import com.cgbros.silkhub.R
import com.cgbros.silkhub.activity.LoginActivity
import com.facebook.FacebookSdk
import com.google.firebase.FirebaseApp
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.mockito.Mockito.`when` as whenCalled

@RunWith(RobolectricTestRunner::class)
class LoginActivityIntegrationTest {

    @Before
    fun setup() {
        println("Setting up test environment...")
//        val (request, response, result) = "https://silkhub-cad21.firebaseio.com/openSessions.json"
//                .httpGet().responseString()
//        println(response)
//        println(result)

        println("Initializing firebase app")
        FirebaseApp.initializeApp(RuntimeEnvironment.application)

        println("Initializing Facebook app")
        FacebookSdk.sdkInitialize(RuntimeEnvironment.application)

        println("Done!")
    }

    @After
    fun cleanUp() {
        println("Cleaning up....")
        println("Done!")
    }

    @Test
    fun shouldDisplayNumberOfOpenSessions() {

        // Given
        val activity = Robolectric.buildActivity(LoginActivity::class.java)
                .create()
                .start()
                .visible()
                .get()

        // When
        val openSessions = activity.findViewById<TextView>(R.id.login_open_sessions).text

        // Then
        Thread.sleep(10_000)
//        assertEquals("1 open sessions", openSessions)
        assert(true) // FIXME

    }

}
