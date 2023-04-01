package dark.andapp.dfinnb.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var categoryId: Int,
    var name: String,
    var comment: String? = null,
    var amount: Double,
    var createdAt: Long,
)