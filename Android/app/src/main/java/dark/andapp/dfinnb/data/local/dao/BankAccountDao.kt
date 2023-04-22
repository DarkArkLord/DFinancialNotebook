package dark.andapp.dfinnb.data.local.dao

import androidx.room.*
import dark.andapp.dfinnb.data.local.entity.BankAccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BankAccountDao {
    @Query("SELECT * FROM BankAccountEntity")
    fun getAll(): Flow<List<BankAccountEntity>>

    @Query("SELECT * FROM BankAccountEntity WHERE id = :id")
    fun getById(id: Int): BankAccountEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: BankAccountEntity): Int

    @Update
    suspend fun update(entity: BankAccountEntity)

    @Delete
    suspend fun delete(entity: BankAccountEntity)
}