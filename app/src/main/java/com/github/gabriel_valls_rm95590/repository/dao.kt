package com.github.gabriel_valls_rm95590.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.gabriel_valls_rm95590.model.EcoDica

@Dao
interface EcoDicaDao {
    @Insert
    suspend fun insert(ecoDica: EcoDica)

    @Query("SELECT * FROM EcoDica")
    suspend fun getAllDicas(): List<EcoDica>
}
