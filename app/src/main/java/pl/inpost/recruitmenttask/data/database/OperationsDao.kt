package pl.inpost.recruitmenttask.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pl.inpost.recruitmenttask.data.database.entity.OperationsEntity

@Dao
interface OperationsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: OperationsEntity) : Long

    @Update
    suspend fun update(entity: OperationsEntity)

    suspend fun upsert(entity: OperationsEntity) : Long {
        val id = insert(entity)
        return if (id == ID_ALREADY_EXISTS) {
            update(entity)
            entity.id
        } else id
    }

    @Query("UPDATE operations SET manualArchive = :archive WHERE id = :operationId")
    suspend fun updateArchiveFlag(operationId: Long, archive: Boolean)
}

const val ID_ALREADY_EXISTS = -1L