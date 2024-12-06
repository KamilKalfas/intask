package pl.inpost.recruitmenttask.data.database.entity

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface OperationsDao {
    
    @Insert
    suspend fun insert(entity: OperationsEntity) : Long
}