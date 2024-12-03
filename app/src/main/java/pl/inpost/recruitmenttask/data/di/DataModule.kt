package pl.inpost.recruitmenttask.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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
        shipmentApi: ShipmentApi,
    ) : ShipmentsRepository = ShipmentsRepositoryImpl(shipmentApi)
}
