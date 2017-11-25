package com.cgbros.silkhub.enumerator

enum class Job {

    EMPTY, HEIST_MISSIONS, CEO_MISSIONS, BIKER_MISSIONS;

    companion object Factory {
        fun fromPosition(position: Int): Job {
            return when(position) {
                1 -> HEIST_MISSIONS
                2 -> CEO_MISSIONS
                3 -> BIKER_MISSIONS
                else -> EMPTY
            }
        }
    }
}
