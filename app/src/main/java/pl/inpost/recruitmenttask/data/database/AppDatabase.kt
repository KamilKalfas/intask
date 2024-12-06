package pl.inpost.recruitmenttask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.inpost.recruitmenttask.data.database.AppDatabase.Companion.VERSION
import pl.inpost.recruitmenttask.data.database.entity.CustomerEntity
import pl.inpost.recruitmenttask.data.database.entity.OperationsDao
import pl.inpost.recruitmenttask.data.database.entity.OperationsEntity
import pl.inpost.recruitmenttask.data.database.entity.ShipmentEntity

@Database(
    entities = [
        ShipmentEntity::class, CustomerEntity::class, OperationsEntity::class
    ], version = VERSION
)

abstract class AppDatabase: RoomDatabase() {

    companion object {
        const val VERSION = 1
    }

    abstract fun shipmentsDao() : ShipmentsDao
    abstract fun customersDao(): CustomerDao
    abstract fun operationsDao() : OperationsDao
}