package com.cgbros.silkhub.model

import com.cgbros.silkhub.enumerator.Job

data class Session(
        val id: String,
        val job: Job,
        val crew: Map<String, Profile>
) {

    constructor() : this(
            id = "",
            job = Job.EMPTY,
            crew = mapOf()
    )

    constructor(map: Map<String, Any?>) : this (
            id = (map["id"] ?: "") as String,
            job = job(map["job"]),
            crew = crew(map["crew"])
    )

    companion object {
        fun job(job: Any?): Job {
            return if (job != null)
                when (job) {
                    is Job -> job
                    is String -> if (job.isEmpty())
                        Job.EMPTY
                    else
                        enumValueOf(job.toUpperCase())
                    else -> throw IllegalArgumentException("Session.job=$job not allowed")
                } else Job.EMPTY
        }

        fun crew(crew: Any?): Map<String, Profile> {
            return if (crew == null) {
                mapOf()
            } else {
                when (crew) {
                    is Map<*, *> -> crew.map {
                        @Suppress("UNCHECKED_CAST")
                        (it.key as String) to Profile(it.value as Map<String, Any?>)
                    }.toMap()
                    else -> throw IllegalArgumentException("Session.crew=$crew not allowed")
                }
            }

        }
    }

    fun isEmpty() = id.isEmpty() && crew.isEmpty() && job.equals(Job.EMPTY)

    fun toMap() = mapOf(
            "id" to id,
            "crew" to crew.map { it.key to it.value.toMap() }.toMap(),
            "job" to job
    )

}
