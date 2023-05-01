package dark.andapp.dfinnb.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BankAccountEntity (
    @PrimaryKey(autoGenerate = true)
    override var id: Int,
    override var name: String,
) : INamedEntity(id, name)