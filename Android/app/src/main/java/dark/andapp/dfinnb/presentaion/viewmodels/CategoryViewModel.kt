package dark.andapp.dfinnb.presentaion.viewmodels

import dagger.hilt.android.lifecycle.HiltViewModel
import dark.andapp.dfinnb.data.local.FinanceManagerDatabase
import dark.andapp.dfinnb.data.local.dao.INamedEntityDao
import dark.andapp.dfinnb.domain.entity.NamedEntity
import dark.andapp.dfinnb.presentaion.extensions.toDomain
import javax.inject.Inject

typealias DataC = dark.andapp.dfinnb.data.local.entity.CategoryEntity

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val db: FinanceManagerDatabase
) : BaseNamedViewModel<DataC>() {
    override fun getDao(): INamedEntityDao<DataC> {
        return db.categoryDao;
    }

    override fun mapToDomain(dataEntity: DataC): NamedEntity {
        val entity = dataEntity.toDomain()

        val transactions = db.transactionDao.getByCategory(entity.id)
        entity.count = transactions.size
        entity.amount = transactions.sumOf { it.amount }

        return entity
    }

    override fun mapToData(entity: NamedEntity): DataC {
        return DataC(
            id = entity.id,
            name = entity.name,
        )
    }
}