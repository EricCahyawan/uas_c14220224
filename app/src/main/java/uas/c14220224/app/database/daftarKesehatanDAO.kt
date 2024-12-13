package uas.c14220224.app.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface daftarKesehatanDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(daftar: daftarKesehatan)

    @Query("SELECT * FROM daftarKesehatan ORDER BY idCatatan asc")
    fun selectAll() : MutableList<daftarKesehatan>

    @Query("SELECT * FROM daftarKesehatan WHERE idCatatan=:isi_id")
    suspend fun getItem(isi_id: Int): daftarKesehatan
}