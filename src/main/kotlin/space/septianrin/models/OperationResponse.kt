package space.septianrin.models

@kotlinx.serialization.Serializable
data class OperationResponse(
    val status: Int,
    val message: String,
    val data: String?
)