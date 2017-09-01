package com.cgbros.silkhub.model

import com.cgbros.silkhub.enumerator.Job

class Session(var job: Job, var host: Profile, var crew: List<Profile>) {

    fun toStringMap() = mapOf(
            "host" to host.toStringMap(),
            "crew" to crew.map { it.uid to it.toStringMap() }.toMap(),
            "job" to job.toString()
    )

}
