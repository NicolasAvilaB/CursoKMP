package data.models

import kotlinx.serialization.Serializable

@Serializable
data class RemoteListExpenses(
    val id: Long = -1,
    val title: String,
    val amount: Double,
    val categoryName: String,
    val description: String,
)