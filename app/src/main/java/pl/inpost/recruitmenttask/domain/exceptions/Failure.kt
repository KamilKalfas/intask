package pl.inpost.recruitmenttask.domain.exceptions

sealed class Failure(val exception: Exception) : Exception("Failure") {
    open class FeatureFailure(featureException: Exception = Exception("Feature failure")) : Failure(featureException)
}
