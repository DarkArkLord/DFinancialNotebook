package dark.andapp.dfinnb.data.local.dao

import androidx.room.*
import dark.andapp.dfinnb.data.local.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface INamedEntityDao<T> {
    fun getAll(): Flow<List<T>>
    fun getById(id: Int): T
    suspend fun insert(entity: T)
    suspend fun update(entity: T)
    suspend fun delete(entity: T)
}