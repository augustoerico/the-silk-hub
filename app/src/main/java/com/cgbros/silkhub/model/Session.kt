package com.cgbros.silkhub.model

import com.cgbros.silkhub.enumerator.Job
import java.util.*

class Session(var job: Job, var crew: Map<String, Profile>) {

    constructor() : this(
            job = Job.FLEECA_JOB,
            crew = mapOf((UUID.randomUUID().toString()) to Profile())
    )

    fun toStringMap() = mapOf(
            "crew" to crew.toString(),
            "job" to job.toString()
    )

}
