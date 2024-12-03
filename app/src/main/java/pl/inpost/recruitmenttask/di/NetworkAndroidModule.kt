package pl.inpost.recruitmenttask.di

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

@InstallIn(SingletonComponent::class)
@Module
class NetworkAndroidModule {

    @Provides
    fun shipmentApi(
        @ApplicationContext context: Context,
        zoneDateTimeTypeAdapter: ZoneDateTimeTypeAdapter,
        shipmentStatusTypeAdapter: ShipmentStatusTypeAdapter
    ): ShipmentApi = MockShipmentApi(context, zoneDateTimeTypeAdapter, shipmentStatusTypeAdapter)
}
