package dark.andapp.dfinnb.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dark.andapp.dfinnb.data.local.FinanceManagerDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFinanceManagerDatabase(app: Application): FinanceManagerDatabase {
        return Room.databaseBuilder(
            app,
            FinanceManagerDatabase::class.java,
            FinanceManagerDatabase.DATABASE_NAME,
        ).build()
    }
}