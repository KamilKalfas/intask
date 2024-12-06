package pl.inpost.recruitmenttask.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import pl.inpost.recruitmenttask.data.database.entity.ShipmentEntity
import pl.inpost.recruitmenttask.data.database.entity.ShipmentEntityWithDetails

@Dao
interface ShipmentsDao {

    @Transaction
    @Query("SELECT * FROM shipments")
    suspend fun getAll(): List<ShipmentEntityWithDetails>

    @Insert
    suspend fun insertAll(entities: List<ShipmentEntity>)
}



