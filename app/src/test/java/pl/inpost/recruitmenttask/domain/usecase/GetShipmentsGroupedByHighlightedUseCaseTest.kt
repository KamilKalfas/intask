package pl.inpost.recruitmenttask.domain.usecase

import io.mockk.Ordering
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.model.ShipmentStatus
import pl.inpost.recruitmenttask.domain.repository.ShipmentsRepository
import pl.inpost.recruitmenttask.mockk.MockkTest
import pl.inpost.recruitmenttask.mockk.fakeShipment

class GetShipmentsGroupedByHighlightedUseCaseTest : MockkTest() {

    private val shipmentsRepository: ShipmentsRepository = mockk()
    private val sortShipmentsUseCase: SortShipmentsUseCase = mockk()

    private val sut = GetShipmentsGroupedByHighlightedUseCase(shipmentsRepository, sortShipmentsUseCase)

    @Test
    fun `given groups are empty return empty lists`() {
        // given
        val list = emptyList<Shipment>()
        coEvery { shipmentsRepository.getShipments() } returns list

        // when
        val result = runBlocking { sut.execute() }

        // then
        coVerify(exactly = 1) {
            shipmentsRepository.getShipments()
        }
        verify(exactly = 0) {
            sortShipmentsUseCase.execute(any())
        }
        assertThat(result).isInstanceOf(GroupedShipmentsResult.Success::class.java)
        val groupedShipmentsResult :GroupedShipmentsResult.Success = result as GroupedShipmentsResult.Success
        assertThat(groupedShipmentsResult.readyToPickup).isEmpty()
        assertThat(groupedShipmentsResult.rest).isEmpty()
    }

    @Test
    fun `given shipments list received from repository data is grouped and sorted`() {
        // given
        val list = listOf(
            fakeShipment(number = "1", status = ShipmentStatus.CREATED, highlight = true),
            fakeShipment(number = "2", status = ShipmentStatus.CREATED, highlight = false),
        )
        val group1 = listOf(list.first())
        val group2 = listOf(list.last())
        coEvery { shipmentsRepository.getShipments() } returns list
        every { sortShipmentsUseCase.execute(any()) } returns group1 andThen group2

        // when
        val result = runBlocking { sut.execute() }

        // then
        coVerify(exactly = 1) {
            shipmentsRepository.getShipments()
        }
        verify(exactly = 2) {
            sortShipmentsUseCase.execute(any())
        }
        assertThat(result).isInstanceOf(GroupedShipmentsResult.Success::class.java)
        val groupedShipmentsResult :GroupedShipmentsResult.Success = result as GroupedShipmentsResult.Success
        assertThat(groupedShipmentsResult.readyToPickup).isEqualTo(group1)
        assertThat(groupedShipmentsResult.rest).isEqualTo(group2)
    }

    @Test
    fun `exception is handled`() {
        // given
        coEvery { shipmentsRepository.getShipments() } throws Exception("ojjoj")

        // when
        val result = runBlocking { sut.execute() }

        // then
        assertThat(result).isInstanceOf(GroupedShipmentsResult.Error::class.java)
    }
}
