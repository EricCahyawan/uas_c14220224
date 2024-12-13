package uas.c14220224.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [daftarKesehatan::class], version = 1)
abstract class daftarKesehatanDB: RoomDatabase() {
    abstract  fun fundaftarKesehatanDB(): daftarKesehatanDAO
    companion object{
        @Volatile
        private var INSTANCE: daftarKesehatanDB? = null

        @JvmStatic
        fun getDatabase(context: Context): daftarKesehatanDB {
            if (INSTANCE == null) {
                synchronized(daftarKesehatanDB::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        daftarKesehatanDB::class.java, "daftarKesehatan_db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE as daftarKesehatanDB
        }
    }
}