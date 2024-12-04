package pl.inpost.recruitmenttask.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.inpost.recruitmenttask.domain.repository.ShipmentsRepository
import pl.inpost.recruitmenttask.domain.usecase.GetShipmentsGroupedByHighlightedUseCase

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetShipmentGroupedUseCase(shipmentsRepository: ShipmentsRepository) : GetShipmentsGroupedByHighlightedUseCase {
        return GetShipmentsGroupedByHighlightedUseCase(shipmentsRepository)
    }
}