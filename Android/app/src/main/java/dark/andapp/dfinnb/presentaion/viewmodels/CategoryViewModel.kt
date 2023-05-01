package dark.andapp.dfinnb.presentaion.viewmodels

import dagger.hilt.android.lifecycle.HiltViewModel
import dark.andapp.dfinnb.data.local.FinanceManagerDatabase
import dark.andapp.dfinnb.data.local.dao.INamedEntityDao
import dark.andapp.dfinnb.domain.entity.NamedEntity
import dark.andapp.dfinnb.presentaion.extensions.toDomain
import javax.inject.Inject

typealias DataC = dark.andapp.dfinnb.data.local.entity.CategoryEntity

@HiltViewModel
abstract class CategoryViewModel @Inject constructor(
    private val db: FinanceManagerDatabase
) : BaseNamedViewModel<DataC>() {
    override fun getDao(): INamedEntityDao<DataC> {
        return db.categoryDao;
    }

    override fun mapToDomain(dataEntity: DataC): NamedEntity {
        val entiry = dataEntity.toDomain()
        // add some fields
        return entiry
    }

    override fun mapToData(entity: NamedEntity): DataC {
        return DataC(
            id = entity.id,
            name = entity.name,
        )
    }
}