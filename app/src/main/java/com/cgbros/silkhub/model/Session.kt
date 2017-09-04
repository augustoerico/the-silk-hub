package com.cgbros.silkhub.model

import com.cgbros.silkhub.enumerator.Job
import java.util.*

class Session(var job: Job, var crew: Map<String, Profile>) {

    constructor() : this(
            job = Job.FLEECA_JOB,
            crew = mapOf((UUID.randomUUID().toString()) to Profile())
    )

    constructor(map: Map<String, Any>) : this(
            job = enumValueOf<Job>((map["job"] as String)),
            crew = (map["crew"] as Map<String, Any>).map {
                it.key to Profile(it.value as Map<String, String>)
            }.toMap()
    )

    fun toStringMap() = mapOf(
            "crew" to crew.toString(),
            "job" to job.toString()
    )

}
