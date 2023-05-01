package dark.andapp.dfinnb.data.local.dao

import androidx.room.*
import dark.andapp.dfinnb.data.local.entity.BankAccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BankAccountDao : INamedEntityDao<BankAccountEntity> {
    @Query("SELECT * FROM BankAccountEntity")
    override fun getAll(): Flow<List<BankAccountEntity>>

    @Query("SELECT * FROM BankAccountEntity WHERE id = :id")
    override fun getById(id: Int): BankAccountEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(entity: BankAccountEntity)

    @Update
    override suspend fun update(entity: BankAccountEntity)

    @Delete
    override suspend fun delete(entity: BankAccountEntity)
}