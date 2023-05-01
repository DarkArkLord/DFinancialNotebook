package dark.andapp.dfinnb.presentaion.viewmodels

import dagger.hilt.android.lifecycle.HiltViewModel
import dark.andapp.dfinnb.data.local.FinanceManagerDatabase
import dark.andapp.dfinnb.data.local.dao.INamedEntityDao
import dark.andapp.dfinnb.domain.entity.NamedEntity
import dark.andapp.dfinnb.presentaion.extensions.toDomain
import javax.inject.Inject

typealias DataBA = dark.andapp.dfinnb.data.local.entity.BankAccountEntity

@HiltViewModel
class BankAccountViewModel @Inject constructor(
    private val db: FinanceManagerDatabase
) : BaseNamedViewModel<DataBA>() {
    override fun getDao(): INamedEntityDao<DataBA> {
        return db.bankAccountDao;
    }

    override fun mapToDomain(dataEntity: DataBA): NamedEntity {
        val entity = dataEntity.toDomain()

        val transactions = db.transactionDao.getByBank(entity.id)
        entity.count = transactions.size
        entity.amount = transactions.sumOf { it.amount }

        return entity
    }

    override fun mapToData(entity: NamedEntity): DataBA {
        return DataBA(
            id = entity.id,
            name = entity.name,
        )
    }
}