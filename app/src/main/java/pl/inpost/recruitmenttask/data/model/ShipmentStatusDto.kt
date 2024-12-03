package pl.inpost.recruitmenttask.data.model

import com.squareup.moshi.Json

/**
 * Order of statuses
 * 1. CREATED
 * 2. CONFIRMED
 * 3. ADOPTED_AT_SOURCE_BRANCH
 * 4. SENT_FROM_SOURCE_BRANCH
 * 5. ADOPTED_AT_SORTING_CENTER
 * 6. SENT_FROM_SORTING_CENTER
 * 7. OTHER
 * 8. DELIVERED
 * 9. RETURNED_TO_SENDER
 * 10. AVIZO
 * 11. OUT_FOR_DELIVERY
 * 12. READY_TO_PICKUP
 * 13. PICKUP_TIME_EXPIRED
 */
enum class ShipmentStatusDto {

    @Json(name = "ADOPTED_AT_SORTING_CENTER")
    ADOPTED_AT_SORTING_CENTER,

    @Json(name = "SENT_FROM_SORTING_CENTER")
    SENT_FROM_SORTING_CENTER,

    @Json(name = "ADOPTED_AT_SOURCE_BRANCH")
    ADOPTED_AT_SOURCE_BRANCH,

    @Json(name = "SENT_FROM_SOURCE_BRANCH")
    SENT_FROM_SOURCE_BRANCH,

    @Json(name = "AVIZO")
    AVIZO,

    @Json(name = "CONFIRMED")
    CONFIRMED,

    @Json(name = "CREATED")
    CREATED,

    @Json(name = "DELIVERED")
    DELIVERED,

    @Json(name = "OTHER")
    OTHER,

    @Json(name = "OUT_FOR_DELIVERY")
    OUT_FOR_DELIVERY,

    @Json(name = "PICKUP_TIME_EXPIRED")
    PICKUP_TIME_EXPIRED,

    @Json(name = "READY_TO_PICKUP")
    READY_TO_PICKUP,

    @Json(name = "RETURNED_TO_SENDER")
    RETURNED_TO_SENDER,

    UNKNOWN,
}
