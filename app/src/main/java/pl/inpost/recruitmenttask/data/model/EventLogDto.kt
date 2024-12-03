package pl.inpost.recruitmenttask.data.model

import java.time.ZonedDateTime

data class EventLogDto(
    val name: String,
    val date: ZonedDateTime
)
