package pl.inpost.recruitmenttask.data.database

import androidx.room.Dao
import androidx.room.Insert
import pl.inpost.recruitmenttask.data.database.entity.OperationsEntity

@Dao
interface OperationsDao {

    @Insert
    suspend fun insert(entity: OperationsEntity) : Long
}