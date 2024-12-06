package pl.inpost.recruitmenttask.data.datasource

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.Test
import pl.inpost.recruitmenttask.data.network.api.ShipmentApi
import pl.inpost.recruitmenttask.mockk.MockkTest
import pl.inpost.recruitmenttask.mockk.fakeShipmentDto

class ShipmentsRemoteDataSourceTest : MockkTest() {

    private val shipmentApi: ShipmentApi = mockk()
    private val sut = ShipmentsRemoteDataSource(shipmentApi)

    @Test
    fun `getShipments calls api to get data`() {
        // given
        val fakeShipment1 = fakeShipmentDto(number = "1", senderName = "Marian", operationHighlight = true)
        val fakeShipment2 = fakeShipmentDto(number = "2", senderName = "Konstantyna", operationHighlight = false)
        val shipments = listOf(
            fakeShipment1, fakeShipment2
        )
        coEvery { shipmentApi.getShipments() } returns shipments

        // when
        val result = runBlocking { sut.getShipments() }

        // then
        coVerify(exactly = 1) {
            shipmentApi.getShipments()
        }
        Assertions.assertThat(result.size).isEqualTo(shipments.size)
        Assertions.assertThat(result[0].number).isEqualTo(shipments[0].number)
        Assertions.assertThat(result[1].number).isEqualTo(shipments[1].number)
        Assertions.assertThat(result[0].sender.name).isEqualTo(shipments[0].sender!!.name)
        Assertions.assertThat(result[1].sender.name).isEqualTo(shipments[1].sender!!.name)
        Assertions.assertThat(result[0].operations.highlight).isEqualTo(shipments[0].operations.highlight)
        Assertions.assertThat(result[1].operations.highlight).isEqualTo(shipments[1].operations.highlight)
    }
}