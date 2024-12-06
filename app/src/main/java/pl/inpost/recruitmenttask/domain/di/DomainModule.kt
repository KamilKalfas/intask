package pl.inpost.recruitmenttask.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import pl.inpost.recruitmenttask.domain.repository.ShipmentsRepository
import pl.inpost.recruitmenttask.domain.usecase.ArchiveShipmentUseCase
import pl.inpost.recruitmenttask.domain.usecase.GetShipmentsGroupedByHighlightedUseCase
import pl.inpost.recruitmenttask.domain.usecase.SortShipmentsUseCase

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideGetShipmentGroupedUseCase(
        shipmentsRepository: ShipmentsRepository,
        sortShipmentsUseCase: SortShipmentsUseCase
    ): GetShipmentsGroupedByHighlightedUseCase {
        return GetShipmentsGroupedByHighlightedUseCase(shipmentsRepository, sortShipmentsUseCase)
    }

    @Provides
    fun provideSortShipmentUseCase(): SortShipmentsUseCase = SortShipmentsUseCase()

    @Provides
    fun provideArchiveShipmentUseCase(
        shipmentsRepository: ShipmentsRepository
    ): ArchiveShipmentUseCase {
        return ArchiveShipmentUseCase(shipmentsRepository)
    }
}

