package pl.inpost.recruitmenttask.domain.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.inpost.recruitmenttask.domain.model.Customer
import pl.inpost.recruitmenttask.domain.model.Operations
import pl.inpost.recruitmenttask.domain.model.Shipment
import pl.inpost.recruitmenttask.domain.model.ShipmentStatus
import pl.inpost.recruitmenttask.mockk.fakeShipment
import java.time.ZonedDateTime

class SortShipmentsUseCaseTest {

    @Test
    fun `sorted by status`() {
        // given
        val shipments = listOf(
            fakeShipment(number = "1", status = ShipmentStatus.ADOPTED_AT_SOURCE_BRANCH, null, null, null),
            fakeShipment(number = "2", status = ShipmentStatus.CONFIRMED, null, null, null),
            fakeShipment(number = "3", status = ShipmentStatus.CREATED, null, null, null),
        )

        // when
        val result = SortShipmentsUseCase().execute(shipments)

        // then
        assertThat("3").isEqualTo(result[0].number)
        assertThat("2").isEqualTo(result[1].number)
        assertThat("1").isEqualTo(result[2].number)
    }

    @Test
    fun `sorted by pickupDate and where first is the closest to now`() {
        // given
        val shipments = listOf(
            fakeShipment(number = "1", status = ShipmentStatus.ADOPTED_AT_SOURCE_BRANCH, ZonedDateTime.now().plusDays(3), null, null),
            fakeShipment(number = "2", status = ShipmentStatus.CONFIRMED, ZonedDateTime.now().plusDays(2), null, null),
            fakeShipment(number = "3", status = ShipmentStatus.CREATED, ZonedDateTime.now().plusDays(1), null, null),
        )

        // when
        val result = SortShipmentsUseCase().execute(shipments)

        // then
        // "3" expected to be the closest
        assertThat("3").isEqualTo(result[0].number)
        assertThat("2").isEqualTo(result[1].number)
        assertThat("1").isEqualTo(result[2].number)
    }

    @Test
    fun `sorted by pickupDate and null`() {
        // given
        val shipments = listOf(
            fakeShipment(number = "3", status = ShipmentStatus.CONFIRMED, ZonedDateTime.now().plusDays(2), null, null),
            fakeShipment(number = "2", status = ShipmentStatus.CONFIRMED, null, null, null),
            fakeShipment(number = "1", status = ShipmentStatus.CONFIRMED, ZonedDateTime.now().plusDays(1), null, null),
        )

        // when
        val result = SortShipmentsUseCase().execute(shipments)

        // then
        assertThat("1").isEqualTo(result[0].number)
        assertThat("3").isEqualTo(result[1].number)
        assertThat("2").isEqualTo(result[2].number)
    }

    @Test
    fun `sorted by expireDate and null`() {
        // given
        val shipments = listOf(
            fakeShipment(number = "1", status = ShipmentStatus.CREATED, null, ZonedDateTime.now().plusDays(3), null),
            fakeShipment(number = "2", status = ShipmentStatus.CREATED, null, ZonedDateTime.now().plusDays(2), null),
            fakeShipment(number = "3", status = ShipmentStatus.CREATED, null, ZonedDateTime.now().plusDays(1), null),
            fakeShipment(number = "4", status = ShipmentStatus.CREATED, null, null, null),
        )

        // when
        val result = SortShipmentsUseCase().execute(shipments)

        // then
        assertThat("3").isEqualTo(result[0].number)
        assertThat("2").isEqualTo(result[1].number)
        assertThat("1").isEqualTo(result[2].number)
        assertThat("4").isEqualTo(result[3].number)
    }

    @Test
    fun `sorted by storedDate and null`() {
        // given
        val shipments = listOf(
            fakeShipment(number = "1", status = ShipmentStatus.CREATED, null, null, ZonedDateTime.now().plusDays(3)),
            fakeShipment(number = "2", status = ShipmentStatus.CREATED, null, null, ZonedDateTime.now().plusDays(2)),
            fakeShipment(number = "3", status = ShipmentStatus.CREATED, null, null, ZonedDateTime.now().plusDays(1)),
            fakeShipment(number = "4", status = ShipmentStatus.CREATED, null, null, null),
        )

        // when
        val result = SortShipmentsUseCase().execute(shipments)

        // when
        assertThat("3").isEqualTo(result[0].number)
        assertThat("2").isEqualTo(result[1].number)
        assertThat("1").isEqualTo(result[2].number)
        assertThat("4").isEqualTo(result[3].number)

    }

    @Test
    fun `sorted order by number`() {
        // given
        val shipments = listOf(
            fakeShipment(number = "3", status = ShipmentStatus.CREATED, null, null, ZonedDateTime.now().plusDays(1)),
            fakeShipment(number = "1", status = ShipmentStatus.CREATED, null, null, ZonedDateTime.now().plusDays(1)),
            fakeShipment(number = "2", status = ShipmentStatus.CREATED, null, null, ZonedDateTime.now().plusDays(1)),
        )

        // when
        val result = SortShipmentsUseCase().execute(shipments)

        // then
        assertThat("1").isEqualTo(result[0].number)
        assertThat("2").isEqualTo(result[1].number)
        assertThat("3").isEqualTo(result[2].number)
    }


}
