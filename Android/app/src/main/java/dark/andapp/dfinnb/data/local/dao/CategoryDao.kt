package dark.andapp.dfinnb.data.local.dao

import androidx.room.*
import dark.andapp.dfinnb.data.local.entity.BankAccountEntity
import dark.andapp.dfinnb.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao : INamedEntityDao<CategoryEntity> {
    @Query("SELECT * FROM CategoryEntity")
    override fun getAll(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM CategoryEntity WHERE id = :id")
    override fun getById(id: Int): CategoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(entity: CategoryEntity)

    @Update
    override suspend fun update(entity: CategoryEntity)

    @Delete
    override suspend fun delete(entity: CategoryEntity)
}