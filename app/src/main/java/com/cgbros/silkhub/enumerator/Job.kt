package com.cgbros.silkhub.enumerator

enum class Job {

    FLEECA_JOB, PRISON_BREAK, HUMANE_LABS_RAID, SERIES_A_FUNDING, PACIFIC_STANDARD_JOB,
    CEO_MISSIONS, BIKER_MISSIONS, BUNKER_MISSIONS, HANGAR_MISSIONS;

    companion object Factory {

        fun fromPosition(position: Int): Job {

            var job = FLEECA_JOB

            when(position) {
                1 -> job = PRISON_BREAK
                2 -> job = HUMANE_LABS_RAID
                3 -> job = SERIES_A_FUNDING
                4 -> job = PACIFIC_STANDARD_JOB
                5 -> job = CEO_MISSIONS
                6 -> job = BIKER_MISSIONS
                7 -> job = BUNKER_MISSIONS
                8 -> job = HANGAR_MISSIONS
            }

            return job
        }

    }

}
