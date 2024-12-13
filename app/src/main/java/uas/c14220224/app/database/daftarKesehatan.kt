package uas.c14220224.app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class daftarKesehatan(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idCatatan")
    var idCatatan: Int = 0,

    @ColumnInfo(name = "tanggalDanJam") // tidak perlu mengambil input
    var tanggalDanJam: String? = null,

    @ColumnInfo(name = "beratBadan")
    var beratBadan: String? = null,

    @ColumnInfo(name = "tekananDarah")
    var tekananDarah: String? = null,

    @ColumnInfo(name = "catatan")
    var catatan: String? = null
)
