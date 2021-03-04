package com.canonicalexamples.myDono.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface OngDAO {
    @Insert
    suspend fun create(ong: Ong)
    @Query("SELECT * FROM ong_table WHERE id = :id")
    suspend fun get(id: Int): Ong?
    @Query("SELECT * FROM ong_table")
    suspend fun fetchOngs(): List<Ong>
    @Update
    suspend fun update(ong: Ong)
    @Query("DELETE FROM ong_table WHERE id = :id")
    suspend fun delete(id: Int)
}
