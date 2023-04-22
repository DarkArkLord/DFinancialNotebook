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
    ]
)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var bankId: Int,
    var categoryId: Int,
    var name: String,
    var comment: String? = null,
    var amount: Double,
    var createdAt: Long,
)