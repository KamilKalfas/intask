package pl.inpost.recruitmenttask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pl.inpost.recruitmenttask.data.database.AppDatabase.Companion.VERSION
import pl.inpost.recruitmenttask.data.database.entity.CustomerEntity
import pl.inpost.recruitmenttask.data.database.entity.OperationsEntity
import pl.inpost.recruitmenttask.data.database.entity.ShipmentEntity

@Database(
    entities = [
        ShipmentEntity::class, CustomerEntity::class, OperationsEntity::class
    ], version = VERSION
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val VERSION = 1
    }

    abstract fun shipmentsDao(): ShipmentsDao
    abstract fun customersDao(): CustomersDao
    abstract fun operationsDao(): OperationsDao
}