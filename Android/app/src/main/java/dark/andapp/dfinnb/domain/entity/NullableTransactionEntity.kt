package dark.andapp.dfinnb.domain.entity

data class NullableTransactionEntity(
    var bank: NamedEntity? = null,
    var category: NamedEntity? = null,
    var amount: Double? = null,
    var createdAt: Long? = null,
    var comment: String? = null,
)