package dark.andapp.dfinnb.domain.entity

data class TransactionEntity(
    val id: Int,
    val name: String,
    val amount: Double,
    var createdAt: Long,
    var comment: String? = null,
)