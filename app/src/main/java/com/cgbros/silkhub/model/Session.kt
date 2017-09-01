package com.cgbros.silkhub.model

import com.cgbros.silkhub.enumerator.Job

class Session(var job: Job, var host: Profile, var crew: List<Profile>) {

    constructor() : this(job = Job.FLEECA_JOB, host = Profile(), crew = arrayListOf())

    fun toStringMap() = mapOf(
            "host" to host.toStringMap(),
            "crew" to crew.map { it.uid to it.toStringMap() }.toMap(),
            "job" to job.toString()
    )

}
