package pl.inpost.recruitmenttask.domain.model

data class Customer(
    private val _email: String?,
    private val _phoneNumber: String?,
    private val _name: String?
) {

    sealed class Sender {
        data class Named(val value: String) : Sender()
        object NoSender : Sender()
    }

    val name: Sender
        get() {
            val resolvedName = sequenceOf(_name, _email, _phoneNumber).firstOrNull { it != null }
            return if (resolvedName != null) {
                Sender.Named(resolvedName)
            } else {
                Sender.NoSender
            }
        }

    companion object {
        val EMPTY = Customer(null, null, null)
    }
}
