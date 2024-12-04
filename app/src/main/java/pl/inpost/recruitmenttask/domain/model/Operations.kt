package pl.inpost.recruitmenttask.domain.model

data class Operations(
    val manualArchive: Boolean,
    val delete: Boolean,
    val collect: Boolean,
    val highlight: Boolean,
    val expandAvizo: Boolean,
    val endOfWeekCollection: Boolean
)