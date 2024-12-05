package pl.inpost.recruitmenttask.domain.model

enum class ShipmentStatus {
    CREATED,

    CONFIRMED,

    ADOPTED_AT_SOURCE_BRANCH,

    SENT_FROM_SOURCE_BRANCH,

    ADOPTED_AT_SORTING_CENTER,

    SENT_FROM_SORTING_CENTER,

    OTHER,

    DELIVERED,

    RETURNED_TO_SENDER,

    AVIZO,

    OUT_FOR_DELIVERY,

    READY_TO_PICKUP,

    PICKUP_TIME_EXPIRED,

    UNKNOWN
}
