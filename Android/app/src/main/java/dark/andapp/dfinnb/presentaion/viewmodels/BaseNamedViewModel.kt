package dark.andapp.dfinnb.presentaion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dark.andapp.dfinnb.data.local.dao.INamedEntityDao
import dark.andapp.dfinnb.data.local.entity.INamedEntity
import dark.andapp.dfinnb.domain.entity.NamedEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

abstract class BaseNamedViewModel<DataT : INamedEntity> : ViewModel() {
    protected abstract fun getDao(): INamedEntityDao<DataT>
    protected abstract fun mapToDomain(dataEntity: DataT): NamedEntity
    protected abstract fun mapToData(entity: NamedEntity): DataT

    fun getAll(): Flow<List<NamedEntity>> {
        return this.getDao().getAll().map {
            it.map {
                mapToDomain(it)
            }
        }
    }

    fun create(entity: NamedEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            val dataEntity = mapToData(entity)
            getDao().insert(dataEntity)
        }
    }

    fun delete(entity: NamedEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            mapToData(entity).also {
                getDao().delete(it)
            }
        }
    }
}