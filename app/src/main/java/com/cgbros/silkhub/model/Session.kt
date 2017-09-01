package com.cgbros.silkhub.model

import com.cgbros.silkhub.enumerator.Job

class Session(var hostUid: String, var job: Job) {

    fun toMap() = mapOf(
            "hostUid" to hostUid,
            "job" to job.toString()
    )

}
