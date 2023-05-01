package dark.andapp.dfinnb.data.local.dao

import dark.andapp.dfinnb.data.local.entity.INamedEntity
import kotlinx.coroutines.flow.Flow

interface INamedEntityDao<T : INamedEntity> {
    fun getAll(): Flow<List<T>>
    fun getById(id: Int): T
    suspend fun insert(entity: T)
    suspend fun update(entity: T)
    suspend fun delete(entity: T)
}