package com.cgbros.silkhub.enumerator

enum class Job {

    FLEECA_JOB, PRISON_BREAK, HUMANE_LABS_RAID, SERIES_A_FUNDING, PACIFIC_STANDARD_JOB,
    CEO_MISSIONS, BIKER_MISSIONS, EMPTY;

    companion object Factory {
        fun fromPosition(position: Int): Job {
            return when(position) {
                0 -> FLEECA_JOB
                1 -> PRISON_BREAK
                2 -> HUMANE_LABS_RAID
                3 -> SERIES_A_FUNDING
                4 -> PACIFIC_STANDARD_JOB
                5 -> CEO_MISSIONS
                6 -> BIKER_MISSIONS
                else -> EMPTY
            }
        }

        fun values(): Array<Job> {
            return Job.values().asList().filter { it != EMPTY }.toTypedArray()
        }
    }
}
