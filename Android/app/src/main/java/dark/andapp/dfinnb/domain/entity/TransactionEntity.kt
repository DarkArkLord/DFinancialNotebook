package dark.andapp.dfinnb.domain.entity

data class TransactionEntity(
    val id: Int,
    val bank: NamedEntity,
    val category: NamedEntity,
    val amount: Double,
    var createdAt: Long,
    var comment: String? = null,
)