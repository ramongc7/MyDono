package com.canonicalexamples.myDono.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "ong_table")
data class Ong(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String = "",
    val fav: Int = -1
    ) {
    val isValid: Boolean
        get() = name.isNotEmpty() && id >= 0 && fav==-1 || fav == 1
}
