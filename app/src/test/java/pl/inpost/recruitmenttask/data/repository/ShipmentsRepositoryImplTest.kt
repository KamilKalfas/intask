package pl.inpost.recruitmenttask.data.repository

import io.mockk.coEvery
import io.mockk.coVerify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import pl.inpost.recruitmenttask.data.datasource.ShipmentsLocalDataSource
import pl.inpost.recruitmenttask.data.datasource.ShipmentsRemoteDataSource
import pl.inpost.recruitmenttask.mockk.MockkTest

class ShipmentsRepositoryImplTest : MockkTest() {

    private val remoteSource: ShipmentsRemoteDataSource = mockk()
    private val localSource: ShipmentsLocalDataSource = mockk()
    private val sut = ShipmentsRepositoryImpl(remoteSource, localSource)

    @Test
    fun `no remote call if data already saved in local`() {
        // given
        coEvery { localSource.hasNoSavedData() } returns false

        // when
        runBlocking { sut.getShipments() }

        // then
        coVerify(exactly = 0) {
            remoteSource.getShipments()
            localSource.insertAll(any())
        }
        coVerify(exactly = 1) {
            localSource.getShipments()
        }
    }

    @Test
    fun `remote call if no data in local`() {
        // given
        coEvery { localSource.hasNoSavedData() } returns true

        // when
        runBlocking { sut.getShipments() }

        // then
        coVerify(exactly = 1) {
            remoteSource.getShipments()
            localSource.insertAll(any())
        }
        coVerify(exactly = 0) {
            localSource.getShipments()
        }
    }
}