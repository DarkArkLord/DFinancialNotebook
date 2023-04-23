package dark.andapp.dfinnb.domain.entity

data class TransactionEntity(
    val id: Int,
    val bank: BankAccountEntity,
    val category: CategoryEntity,
    val amount: Double,
    var createdAt: Long,
    var comment: String? = null,
)