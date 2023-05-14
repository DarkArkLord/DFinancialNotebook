package dark.andapp.dfinnb.presentaion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dark.andapp.dfinnb.data.local.FinanceManagerDatabase
import dark.andapp.dfinnb.domain.entity.TransactionEntity
import dark.andapp.dfinnb.presentaion.extensions.toData
import dark.andapp.dfinnb.presentaion.extensions.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val db: FinanceManagerDatabase
) : ViewModel() {
    fun getAll(): Flow<List<TransactionEntity>> {
        return db.transactionDao.getAll().map {
            it.map {
                val bankAccount = db.bankAccountDao.getById(it.bankId).toDomain()
                val category = db.categoryDao.getById(it.categoryId).toDomain()
                it.toDomain(bankAccount, category)
            }
        }
    }

    fun createTransaction(entity: TransactionEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            entity.toData().also {
                db.transactionDao.insert(it)
            }
        }
    }

    fun delete(entity: TransactionEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            entity.toData().also {
                db.transactionDao.delete(it)
            }
        }
    }
}