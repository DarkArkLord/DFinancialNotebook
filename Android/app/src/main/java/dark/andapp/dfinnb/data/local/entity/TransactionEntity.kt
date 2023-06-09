package dark.andapp.dfinnb.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = BankAccountEntity::class,
            parentColumns = ["id"],
            childColumns = ["bankId"]
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"]
        ),
    ]
)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var bankId: Int,
    var categoryId: Int,
    var amount: Double,
    var createdAt: Long,
    var comment: String? = null,
)