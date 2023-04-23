package dark.andapp.dfinnb.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
)