package com.cgbros.silkhub.model

import com.cgbros.silkhub.enumerator.Job
import java.util.*

class Session(var uid: String, var job: Job, var crew: Map<String, Profile>) {

    constructor() : this(
            uid = "",
            job = Job.FLEECA_JOB,
            crew = mapOf((UUID.randomUUID().toString()) to Profile())
    )

    constructor(uid: String, map: Map<String, Any>) : this(
            uid = uid,
            job = enumValueOf<Job>((map["job"] as String)),
            crew = (map["crew"] as Map<String, Any>).map {
                it.key to Profile(it.value as Map<String, String>)
            }.toMap()
    )

    fun toStringMap() = mapOf(
            "uid" to uid,
            "crew" to crew.toString(),
            "job" to job.toString()
    )

    override fun toString(): String {
        return "{uid=$uid,job=$job,crew=$crew}"
    }

}
