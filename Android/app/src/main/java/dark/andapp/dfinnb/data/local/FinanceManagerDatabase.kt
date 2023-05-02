package dark.andapp.dfinnb.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dark.andapp.dfinnb.data.local.dao.BankAccountDao
import dark.andapp.dfinnb.data.local.dao.CategoryDao
import dark.andapp.dfinnb.data.local.dao.TransactionDao
import dark.andapp.dfinnb.data.local.entity.BankAccountEntity
import dark.andapp.dfinnb.data.local.entity.CategoryEntity
import dark.andapp.dfinnb.data.local.entity.TransactionEntity

@Database(
    entities = [
        BankAccountEntity::class,
        CategoryEntity::class,
        TransactionEntity::class,
    ],
    version = 1
)
abstract class FinanceManagerDatabase : RoomDatabase() {
    abstract val transactionDao: TransactionDao
    abstract val bankAccountDao: BankAccountDao
    abstract val categoryDao: CategoryDao

    companion object {
        const val DATABASE_NAME = "TEST_db"

        @Volatile
        private var instance: FinanceManagerDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            FinanceManagerDatabase::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries().build()
    }
}