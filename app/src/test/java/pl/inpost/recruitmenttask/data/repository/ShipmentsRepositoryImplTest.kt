package pl.inpost.recruitmenttask.data.repository

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.inpost.recruitmenttask.data.model.CustomerDto
import pl.inpost.recruitmenttask.data.model.OperationsDto
import pl.inpost.recruitmenttask.data.model.ShipmentDto
import pl.inpost.recruitmenttask.data.model.ShipmentStatusDto
import pl.inpost.recruitmenttask.data.model.ShipmentTypeDto
import pl.inpost.recruitmenttask.data.network.api.ShipmentApi
import pl.inpost.recruitmenttask.mockk.MockkTest
import java.time.ZonedDateTime

class ShipmentsRepositoryImplTest : MockkTest() {

    private val shipmentApi: ShipmentApi = mockk()
    private val sut = ShipmentsRepositoryImpl(shipmentApi)

    @Test
    fun `getShipments calls api to get data`() {
        // given
        val fakeShipment1 = fakeShipment(number = "1", senderName = "Marian", operationHighlight = true)
        val fakeShipment2 = fakeShipment(number = "2", senderName = "Konstantyna", operationHighlight = false)
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
        assertThat(result.size).isEqualTo(shipments.size)
        assertThat(result[0].number).isEqualTo(shipments[0].number)
        assertThat(result[1].number).isEqualTo(shipments[1].number)
        assertThat(result[0].sender.name).isEqualTo(shipments[0].sender!!.name)
        assertThat(result[1].sender.name).isEqualTo(shipments[1].sender!!.name)
        assertThat(result[0].operations.highlight).isEqualTo(shipments[0].operations.highlight)
        assertThat(result[1].operations.highlight).isEqualTo(shipments[1].operations.highlight)
    }
}

private fun fakeShipment(
    number: String,
    senderName: String,
    operationHighlight: Boolean
) = ShipmentDto(
    number = number,
    shipmentType = ShipmentTypeDto.PARCEL_LOCKER,
    status = ShipmentStatusDto.DELIVERED,
    eventLog = emptyList(),
    openCode = null,
    expiryDate = null,
    storedDate = null,
    pickUpDate = ZonedDateTime.now(),
    receiver = CustomerDto(
        email = null,
        phoneNumber = null,
        name = null
    ),
    sender = CustomerDto(
        email = null,
        phoneNumber = null,
        name = senderName
    ),
    operations = OperationsDto(
        manualArchive = false,
        delete = false,
        collect = false,
        highlight = operationHighlight,
        expandAvizo = false,
        endOfWeekCollection = false
    )
)

/*
        // given
        every { clientProvider.client } returns mockClientRespondWith(
            json = ResponseQuoteOfTheDay()
        )

        // when
        val result = runBlocking { subject.quoteOfTheDay() }

        // then
        assertThat(result).isInstanceOf(QuoteOfTheDayResponse::class.java)*/
