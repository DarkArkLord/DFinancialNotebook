package dark.andapp.dfinnb.presentaion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dark.andapp.dfinnb.data.local.FinanceManagerDatabase
import dark.andapp.dfinnb.data.local.entity.TransactionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsFragmentViewModel @Inject constructor (
    private val db: FinanceManagerDatabase
) : ViewModel() {

    val transactions = db.transactionDao.getAllTransactions()

    fun createTransaction(
        name: String,
        amount: Double,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val transaction = TransactionEntity(
                id = 0,
                categoryId = 0,
                name = name,
                amount = amount,
                createdAt = System.currentTimeMillis(),
            )
            val id = db.transactionDao.insertTransaction(transaction)
        }
    }
}