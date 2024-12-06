package pl.inpost.recruitmenttask.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ShipmentEntityWithDetails(
    @Embedded val shipment: ShipmentEntity,
    @Relation(
        parentColumn = "customer_id",
        entityColumn = "id",
        entity = CustomerEntity::class
    ) val sender: CustomerEntity?,
    @Relation(
        parentColumn = "operation_id",
        entityColumn = "id",
        entity = OperationsEntity::class
    ) val operations: OperationsEntity
)
