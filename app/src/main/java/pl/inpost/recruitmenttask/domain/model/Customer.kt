package pl.inpost.recruitmenttask.domain.model

data class Customer(
    val email: String?,
    val phoneNumber: String?,
    val name: String?
) {
    companion object {
        val EMPTY = Customer(null, null, null)
    }
}
