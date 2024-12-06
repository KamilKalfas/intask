package pl.inpost.recruitmenttask.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import pl.inpost.recruitmenttask.data.database.AppDatabase
import pl.inpost.recruitmenttask.data.database.CustomerDao
import pl.inpost.recruitmenttask.data.database.ShipmentsDao
import pl.inpost.recruitmenttask.data.database.entity.OperationsDao
import pl.inpost.recruitmenttask.data.datasource.ShipmentsLocalDataSource
import pl.inpost.recruitmenttask.data.datasource.ShipmentsRemoteDataSource
import pl.inpost.recruitmenttask.data.network.adapters.ShipmentStatusTypeAdapter
import pl.inpost.recruitmenttask.data.network.adapters.ZoneDateTimeTypeAdapter
import pl.inpost.recruitmenttask.data.network.api.MockShipmentApi
import pl.inpost.recruitmenttask.data.network.api.ShipmentApi
import pl.inpost.recruitmenttask.data.repository.ShipmentsRepositoryImpl
import pl.inpost.recruitmenttask.domain.repository.ShipmentsRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun shipmentApi(
        @ApplicationContext context: Context,
        zoneDateTimeTypeAdapter: ZoneDateTimeTypeAdapter,
        shipmentStatusTypeAdapter: ShipmentStatusTypeAdapter
    ): ShipmentApi = MockShipmentApi(context, zoneDateTimeTypeAdapter, shipmentStatusTypeAdapter)

    @Provides
    fun provideShipmentRepository(
        remoteDataSource: ShipmentsRemoteDataSource,
    ): ShipmentsRepository = ShipmentsRepositoryImpl(remoteDataSource)

    @Provides
    fun provideRemoteDataSource(api: ShipmentApi): ShipmentsRemoteDataSource {
        return ShipmentsRemoteDataSource(api)
    }

    @Provides
    fun provideLocalDataSource(
        shipmentsDao: ShipmentsDao,
        customerDao: CustomerDao,
        operationsDao: OperationsDao
    ): ShipmentsLocalDataSource {
        return ShipmentsLocalDataSource(
            shipmentsDao = shipmentsDao,
            operationsDao = operationsDao,
            customersDao = customerDao,
        )
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java,
        ).build()
    }

    @Provides
    @Singleton
    fun provideShipmentsDao(db: AppDatabase): ShipmentsDao {
        return db.shipmentsDao()
    }

    @Provides
    @Singleton
    fun provideCustomerDao(db: AppDatabase): CustomerDao {
        return db.customersDao()
    }

    @Provides
    @Singleton
    fun provideOperationsDao(db: AppDatabase): OperationsDao {
        return db.operationsDao()
    }
}
