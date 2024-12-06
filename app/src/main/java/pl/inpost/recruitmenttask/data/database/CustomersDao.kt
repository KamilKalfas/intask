package pl.inpost.recruitmenttask.data.database

import androidx.room.Dao
import androidx.room.Insert
import pl.inpost.recruitmenttask.data.database.entity.CustomerEntity

@Dao
interface CustomersDao {

    @Insert
    suspend fun insert(entity: CustomerEntity) : Long
}

