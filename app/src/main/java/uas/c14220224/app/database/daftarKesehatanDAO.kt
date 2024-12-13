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

    @Query("UPDATE daftarKesehatan SET tanggal =:isi_tanggal, item=:isi_item, jumlah=:isi_jumlah, status=:isi_status WHERE id=:pilihid")
    fun update(isi_tanggal: String, isi_item: String, isi_jumlah: String, pilihid: Int, isi_status: Int)

    @Delete
    fun delete(daftar: daftarKesehatan)

    @Query("SELECT * FROM daftarKesehatan ORDER BY id asc")
    fun selectAll() : MutableList<daftarKesehatan>

    @Query("SELECT * FROM daftarKesehatan WHERE id=:isi_id")
    suspend fun getItem(isi_id: Int): daftarKesehatan
}