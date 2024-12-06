package pl.inpost.recruitmenttask.data.database

import androidx.room.Dao
import androidx.room.Insert
import pl.inpost.recruitmenttask.data.database.entity.ShipmentEntity

@Dao
interface ShipmentsDao {

    @Insert
    suspend fun insertAll(entities: List<ShipmentEntity>)
}



