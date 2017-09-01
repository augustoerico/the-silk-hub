package com.cgbros.silkhub

class Session(var hostUid: String, var job: Job) {

    fun toMap() = mapOf(
            "hostUid" to hostUid,
            "job" to job.toString()
    )

}
