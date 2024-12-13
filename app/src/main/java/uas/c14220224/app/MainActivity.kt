package uas.c14220224.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import uas.c14220224.app.database.daftarKesehatan
import uas.c14220224.app.database.daftarKesehatanDB

class MainActivity : AppCompatActivity() {
    private lateinit var DB: daftarKesehatanDB
    private lateinit var customAdapter: customAdapter
    private var daftarKesehatan: MutableList<daftarKesehatan> = mutableListOf()
    lateinit var fabAdd: FloatingActionButton
    lateinit var fabUpload: FloatingActionButton
    lateinit var rvdaftarKesehatan: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        DB = daftarKesehatanDB.getDatabase(this)
        ReadtoData()

        rvdaftarKesehatan = findViewById(R.id.rvdaftarKesehatan)
        fabUpload = findViewById(R.id.fabUpload)

        fabUpload.setOnClickListener {
            val intent = Intent(this, tambah_simpan::class.java)
            startActivity(intent)
        }

        customAdapter = customAdapter(daftarKesehatan)
        rvdaftarKesehatan.layoutManager = LinearLayoutManager(this)
        rvdaftarKesehatan.adapter = customAdapter
        fabAdd = findViewById(R.id.fabAdd)
        fabAdd.setOnClickListener {
            val intent = Intent(this, addData::class.java)
            startActivity(intent)
        }
    }

    fun ReadtoData() {
        CoroutineScope(Dispatchers.Main).async {
            val readData = DB.fundaftarKesehatanDB().selectAll()
            daftarKesehatan.clear()
            daftarKesehatan = readData
        }
    }
}